package com.hout.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hout.business.MeetupService;
import com.hout.business.NotificationService;
import com.hout.business.SuggestionService;
import com.hout.business.VenueService;
import com.hout.business.dao.MeetupDao;
import com.hout.business.dao.UserDao;
import com.hout.domain.entities.Meetup;
import com.hout.domain.entities.Suggestion;
import com.hout.domain.entities.User;
import com.hout.domain.entities.Venue;

@Stateless
public class MeetupServiceImpl implements MeetupService {
	@Inject
    private MeetupDao meetupDao;
    
	@Inject
    private SuggestionService suggestionService;

	@Inject
    private NotificationService notificationService;
    
	@Inject
    private UserDao userDao;
	
	@Inject
    private VenueService venueService;
    
	@Override
    public void removeInvitee(long meetupId, long userId) throws Exception {
        Meetup meetup = meetupDao.findById(meetupId);
        if(meetup == null) {
        	throw new Exception("Meetup not found. Please check the id");
        }
     
        if(!meetup.getInviteeIds().contains(userId)) {
        	throw new Exception("User not  invited to meetup");
        }
        meetup.getRejectedUserIds().add(userId);
        
        Set<Suggestion> suggestions = meetup.getSuggestions();
        for(Suggestion suggestion :  suggestions) {
        	suggestion.getAcceptedUserIds().remove(userId);
        	suggestion.getRejectedUserIds().remove(userId);
        	suggestion.getUndecidedUserIds().remove(userId);
        }
        checkAndFinalizeDetails(meetupId);
    }

	@Override
	public long createNew(User user, String description, 
			String suggestedLocation,  Date suggestedDate, 
			Set<Long> contactIds, boolean isFacebookSharing, 
			boolean isTwitterSharing, boolean isSuggestionsAllowed) {
		Meetup meetup = new Meetup();
		meetup.setDescription(description);
		meetup.setFacebookSharing(isFacebookSharing);
		meetup.setTwitterSharing(isTwitterSharing);
		meetup.setIsSuggestionsAllowed(isSuggestionsAllowed);
		meetup.getInviteeIds().addAll(contactIds);
		meetup.getInviteeIds().add(user.getId());
		
		Suggestion suggestion = suggestionService.createNew(user, suggestedLocation, suggestedDate);
		suggestion.getUndecidedUserIds().addAll(contactIds);
		suggestion.getAcceptedUserIds().add(user.getId());
		
		meetup.addSuggestions(suggestion);
		
		addNew(meetup);
		
		notificationService.notify(meetup, "New meetup has been created");
		return meetup.getId();
	}

	@Override
	public void checkAndFinalizeDetails(long meetupId) throws Exception {
	
		boolean hasBeenFinalized = false;
		Meetup meetup = meetupDao.findById(meetupId);
		if(meetup == null) {
		 	throw new Exception("Meetup not found. Please check the id");
		}
		
		for(Suggestion suggestion : meetup.getSuggestions()) {
			Set<Long> acceptedUserIds = suggestion.getAcceptedUserIds();
			Set<Long> rejectedUserIds = suggestion.getRejectedUserIds();
			Set<Long> KIVUserIds = suggestion.getUndecidedUserIds();
			if (acceptedUserIds.size() > rejectedUserIds.size() 
					+ KIVUserIds.size()) {
				meetup.setFinalizedDate(suggestion.getDate());
				meetup.setFinalizedLocation(suggestion.getVenue());
				
				hasBeenFinalized = true;
				
				notificationService.notify(meetup, "Details for meetup have been finalized");
				break;
			}
		}
		if(!hasBeenFinalized) {
			meetup.setFinalizedDate(null);
			meetup.setFinalizedLocation(null);
		}
	}
	
	public void removeUnnecessarySuggestions(long meetupId) throws Exception {
		
		Meetup meetup = meetupDao.findById(meetupId);
		if(meetup == null) {
		 	throw new Exception("Meetup not found. Please check the id");
		}
		
		Iterator<Suggestion> suggestionsIterator = meetup.getSuggestions().iterator();
		List<Suggestion> toBeRemoved = new ArrayList<Suggestion>();
		while(suggestionsIterator.hasNext()) {
			Suggestion suggestion = suggestionsIterator.next();
			if(suggestion.getAcceptedUserIds().isEmpty()) {
				suggestionsIterator.remove();
				toBeRemoved.add(suggestion);
			}
		}
		for(Suggestion suggestion : toBeRemoved) {
			suggestionService.remove(suggestion.getId());
		}
		
	}

	@Override
	public long addSuggestionToMeetup(long meetupId, long userId,
			String location, Date date) throws Exception {
		Meetup meetup = meetupDao.findById(meetupId);
		if(meetup == null) {
		 	throw new Exception("Meetup not found. Please check the id");
		}
		
		if(!meetup.getIsSuggestionsAllowed()) {
			throw new Exception("Suggestions not allowed for meetup");
		}
		
		User user = userDao.findById(userId);
		if(user == null) {
		 	throw new Exception("User not found. Please check the id");
		}
		
		Set<Suggestion> suggestions = meetup.getSuggestions();
		Set<Suggestion> toBeRemoved = new HashSet<Suggestion>();
		
		for(Suggestion suggestion : suggestions) {
			if(suggestion.getSuggestedUserId() == userId) {
				toBeRemoved.add(suggestion);
				break;
			}
		}
		
		for(Suggestion remove : toBeRemoved) {
			suggestions.remove(remove);
		}
		
		//create the actual  suggestion here
		
		Venue venue = venueService.createNew(location);
		
		Suggestion suggestion = new Suggestion(user.getId(), venue, date);
		suggestion.getUndecidedUserIds().addAll(meetup.getInviteeIds());
		suggestion.getUndecidedUserIds().remove(userId);
		suggestion.getAcceptedUserIds().add(userId);
		
		suggestions.add(suggestion);
		
		checkAndFinalizeDetails(meetupId);
		return suggestion.getId();
	}

	@Override
	public void addNew(Meetup meetup) {
		meetupDao.save(meetup);
	}

	@Override
	public Set<Long> addUsersToMeetup(long userId, long meetupId, Set<Long> inviteeIds) throws Exception {
		Meetup meetup = meetupDao.findById(meetupId);
        if(meetup == null) {
        	throw new Exception("Meetup not found. Please check the id");
        }
        if(!meetup.getInviteeIds().contains(userId)) {
		 	throw new Exception("User does not belong to meetup");
		}
        
        Set<Long> addedUsers = new HashSet<Long>();
        for(Long inviteeId : inviteeIds) {
        	if(meetup.getInviteeIds().contains(inviteeId)) {
        		//throw new Exception("Invitee already belongs to meetup");
        		continue;
        	}
        	User invitee = userDao.findById(inviteeId);
        	if(invitee == null) {
        		//throw new Exception("Invitee not found. Please check the id");
        		continue;
        	}
        	meetup.getInviteeIds().add(inviteeId);
        	addedUsers.add(inviteeId);
        }
        for(Suggestion suggestion : meetup.getSuggestions()) {
    		suggestion.getUndecidedUserIds().addAll(addedUsers);
    	}
		checkAndFinalizeDetails(meetupId);
		return addedUsers;
	}
}

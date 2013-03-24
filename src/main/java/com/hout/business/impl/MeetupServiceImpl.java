package com.hout.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hout.business.MeetupService;
import com.hout.business.NotificationService;
import com.hout.business.SuggestionService;
import com.hout.business.dao.MeetupDao;
import com.hout.business.dao.UserDao;
import com.hout.domain.entities.Meetup;
import com.hout.domain.entities.Suggestion;
import com.hout.domain.entities.User;

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
    
	@Override
    public void removeInvitee(long meetupId, long userId) {
        meetupDao.findById(meetupId).getInviteeStatus().remove(userId);
    }

	@Override
	public void createNew(User user, String description, 
			String suggestedLocation,  Date suggestedDate, 
			List<Long> contactIds, boolean isFacebookSharing, 
			boolean isTwitterSharing, boolean isSuggestionsAllowed) {
		Meetup meetup = new Meetup();
		meetup.setDescription(description);
		meetup.setFacebookSharing(isFacebookSharing);
		meetup.setTwitterSharing(isTwitterSharing);
		meetup.setIsSuggestionsAllowed(isSuggestionsAllowed);
		meetup.setInviteeIds(contactIds);
		Suggestion suggestion = suggestionService.createNew(user, suggestedLocation, suggestedDate);
		suggestion.setUndecidedUserIds(contactIds);
		if(suggestion != null) {
			meetup.addSuggestions(suggestion);
		}
		notificationService.notify(meetup, "New meetup has been created");
	}

	@Override
	public void checkAndFinalizeDetails(long meetupId) {
	
		Meetup meetup = meetupDao.findById(meetupId);
		for(Suggestion suggestion : meetup.getSuggestions()) {
			List<Long> acceptedUserIds = suggestion.getAcceptedUserIds();
			List<Long> rejectedUserIds = suggestion.getRejectedUserIds();
			List<Long> KIVUserIds = suggestion.getUndecidedUserIds();
			if (acceptedUserIds.size() > rejectedUserIds.size() + KIVUserIds.size()) {
				meetup.setFinalizedDate(suggestion.getDate());
				meetup.setFinalizedLocation(suggestion.getVenue());
				notificationService.notify(meetup, "Details for meetup have been finalized");
				break;
			}
		}
	}
	
	public void removeUnnecessarySuggestions(long meetupId) {
		Meetup meetup = meetupDao.findById(meetupId);
		ListIterator<Suggestion> suggestionsIterator = meetup.getSuggestions().listIterator();
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
	public void addSuggestionToMeetup(long meetupId, long userId,
			String location, Date date) {
		Meetup meetup = meetupDao.findById(meetupId);
		User user = userDao.findById(userId);
		List<Suggestion> suggestions = meetup.getSuggestions();
		Suggestion toBeRemoved = null;
		for(Suggestion suggestion : suggestions) {
			if(suggestion.getSuggestedUser().equals(user)) {
				toBeRemoved = suggestion;
			}
		}
		if(toBeRemoved != null) {
			suggestions.remove(toBeRemoved);
		}
		Suggestion suggestion = new Suggestion(user,location, date);
		suggestions.add(suggestion);
	}
	
}

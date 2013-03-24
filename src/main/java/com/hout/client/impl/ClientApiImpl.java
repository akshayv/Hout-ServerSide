package com.hout.client.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hout.business.MeetupService;
import com.hout.business.SuggestionService;
import com.hout.business.UserService;
import com.hout.business.VenueService;
import com.hout.business.dao.MeetupDao;
import com.hout.business.dao.SuggestionDao;
import com.hout.client.ClientApi;
import com.hout.domain.entities.Meetup;
import com.hout.domain.entities.Status;
import com.hout.domain.entities.Suggestion;
import com.hout.domain.entities.SuggestionStatus;
import com.hout.domain.entities.User;
import com.hout.domain.entities.Venue;

@Stateless
public class ClientApiImpl implements ClientApi {
	
	@Inject
	private MeetupService meetupService;

	@Inject
	private MeetupDao meetupDao;
	
	@Inject
	private UserService userService;
	
	@Inject
	private SuggestionService suggestionService;
	
	@Inject
	private SuggestionDao suggestionDao;
	
	@Inject
	private VenueService venueService;
	
	User currentUser = null;
		
	@Override
	public void createNewMeetup(String description, String suggestedLocation,
			Date suggestedDate, List<Long> contactIds,
			boolean isFacebookSharing, boolean isTwitterSharing, 
			boolean isSuggestionsAllowed) {
		meetupService.createNew(currentUser, description, suggestedLocation, 
				suggestedDate, contactIds, isFacebookSharing, isTwitterSharing, 
				isSuggestionsAllowed);
	}

	@Override
	public void createNewUser(String name, String profilePictureLocation,
			List<Long> contacts) {
			User user = userService.createNewUser(name, profilePictureLocation,contacts);
			userService.addNewUser(user);
		}

	@Override
	public void RSVPToSuggestion(long meetupId, long suggestionId, SuggestionStatus status) {
		suggestionService.RSVP(currentUser, suggestionId, status);
		meetupService.removeUnnecessarySuggestions(meetupId);
		meetupService.checkAndFinalizeDetails(meetupId);
	}

	@Override
	public void addNewSuggestion(long meetupId, String suggestedPlace, Date suggestedTime) {
		Meetup meetup = meetupDao.findById(meetupId);
		Venue venue = venueService.createNew(suggestedPlace); 
		Suggestion suggestion = new Suggestion(currentUser, venue, suggestedTime);
		meetup.addSuggestions(suggestion);
		RSVPToSuggestion(meetupId, suggestion.getId(), SuggestionStatus.YES);
	}

	@Override
	public List<Suggestion> getSuggestionsForMeetup(long meetupId) {
		Meetup meetup = meetupDao.findById(meetupId);
		return meetup.getSuggestions();
	}

	@Override
	public List<User> getAcceptedForSuggestion(long meetupId, long suggestionId) {
		Suggestion suggestion = suggestionDao.findById(suggestionId);
		List<Long> userIds =  suggestion.getAcceptedUserIds();
		List<User> users = new ArrayList<User>();
		for(Long userId : userIds) {
			users.add(userService.findById(userId));
		}
		return users;
	}

	@Override
	public List<User> getRejectedForSuggestion(long meetupId, long suggestionId) {
		Suggestion suggestion = suggestionDao.findById(suggestionId);
		List<Long> userIds =  suggestion.getRejectedUserIds();
		List<User> users = new ArrayList<User>();
		for(Long userId : userIds) {
			users.add(userService.findById(userId));
		}
		return users;
	}

	@Override
	public Map<User, Status> getStatusForMeetup(long meetupId) {
		Meetup meetup = meetupDao.findById(meetupId);
		Map<Long, Status> userIdStatus = meetup.getInviteeStatus();
		Map<User, Status> userStatus = new HashMap<User, Status>();
		for (Long userId : userIdStatus.keySet()) {
			userStatus.put(userService.findById(userId), userIdStatus.get(userId));
		}
		return userStatus;
	}

	@Override
	public Meetup findMeetupById(long meetupId) {
		return meetupDao.findById(meetupId);
	}
}

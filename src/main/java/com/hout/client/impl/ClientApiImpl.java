package com.hout.client.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hout.business.MeetupService;
import com.hout.business.NotificationService;
import com.hout.business.SuggestionService;
import com.hout.business.UserService;
import com.hout.business.VenueService;
import com.hout.business.dao.MeetupDao;
import com.hout.business.dao.SuggestionDao;
import com.hout.client.ClientApi;
import com.hout.domain.entities.Meetup;
import com.hout.domain.entities.Notification;
import com.hout.domain.entities.Suggestion;
import com.hout.domain.entities.SuggestionStatus;
import com.hout.domain.entities.User;

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

	@Inject
	private NotificationService notificationService;
	
	User currentUser = null;
		
	@Override
	public long createNewMeetup(long userId, String apiKey, String description,
			Set<Long> contactIds,
			boolean isFacebookSharing, boolean isTwitterSharing, 
			boolean isSuggestionsAllowed) throws Exception {
		checkApiKey(userId, apiKey);
		
		return meetupService.createNew(currentUser, description,
				contactIds, isFacebookSharing, isTwitterSharing, 
				isSuggestionsAllowed);
	}

	private void checkApiKey(long userId, String apiKey) throws Exception {
		String actualApiKey = userService.getApiKeyForUserId(userId);
		if(!apiKey.equals(actualApiKey)){
			throw new Exception("Authorization problem");
		}
		currentUser = userService.findById(userId);
	}

	@Override
	public long createNewUser(String name, String profilePictureLocation,
			String apiKey, Set<Long> contacts, long contactNumber) {
			User user = userService.createNewUser(name, profilePictureLocation,
					apiKey, contacts, contactNumber);
			return userService.addNewUser(user);
		}

	@Override
	public void RSVPToSuggestion(long userId, String apiKey, 
			long meetupId, long suggestionId, SuggestionStatus status) throws Exception {
		checkApiKey(userId, apiKey);
		suggestionService.RSVP(currentUser, suggestionId, status);
		meetupService.removeUnnecessarySuggestions(meetupId);
		meetupService.checkAndFinalizeDetails(meetupId);
	}

	@Override
	public long addNewSuggestion(long userId, String apiKey, long meetupId, String suggestedPlace, Date suggestedTime) throws Exception{
		checkApiKey(userId, apiKey);
		return meetupService.addSuggestionToMeetup(meetupId, userId, suggestedPlace, suggestedTime);
	}

	@Override
	public Set<Long> addInviteesToMeetup(long userId, String apiKey, Set<Long> inviteeId,
			long meetupId) throws Exception {
		checkApiKey(userId, apiKey);
		return meetupService.addUsersToMeetup(userId, meetupId, inviteeId);
	}

	@Override
	public void declineMeetup(long userId, String apiKey, long meetupId) throws Exception {
		checkApiKey(userId, apiKey);
		meetupService.removeInvitee(meetupId, userId);
	}

	@Override
	public List<Notification> getNotificationsForUser(long userId, String apiKey) throws Exception {
		checkApiKey(userId, apiKey);
		return notificationService.getNewNotificationsForUser(currentUser);
	}
	
	@Override
	public void deleteNotifications(List<Notification> notifications) {
		notificationService.deleteList(notifications);
	}

	@Override
	public List<Meetup> getMeetupsForDateRange(long userId, String apiKey,
			Date fromDate, Date toDate) throws Exception {
		if(fromDate.after(toDate)) {
			throw new Exception("From Date cannot be after ToDate");
		}
		checkApiKey(userId, apiKey);
		List<Meetup> userMeetups = new ArrayList<Meetup>();
		List<Meetup> meetups = meetupDao.findForDateRange(fromDate, toDate);
		for(Meetup meetup : meetups) {
			if(meetup.getInviteeIds().contains(userId)) {
				userMeetups.add(meetup);
			}
		}
		return userMeetups;
	}

	@Override
	public List<Suggestion> getSuggestionsForMeetup(long userId, String apiKey,
			long meetupId) throws Exception {
		checkApiKey(userId, apiKey);
		List<Suggestion> suggestions = new ArrayList<Suggestion>();
		suggestions.addAll(meetupDao.findById(meetupId).getSuggestions());
		return suggestions;
	}

	@Override
	public User getUserDetails(long userId) {
		return userService.findById(userId);
	}
}

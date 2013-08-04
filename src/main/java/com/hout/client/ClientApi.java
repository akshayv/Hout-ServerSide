package com.hout.client;


import java.util.Date;
import java.util.List;
import java.util.Set;

import com.hout.domain.entities.Meetup;
import com.hout.domain.entities.Notification;
import com.hout.domain.entities.Suggestion;
import com.hout.domain.entities.SuggestionStatus;
import com.hout.domain.entities.User;

public interface ClientApi {

    public long createNewMeetup(long userId, String apiKey, String description, Set<Long> contactIds,
                                boolean isFacebookSharing, boolean isTwitterSharing, boolean isSuggestionsAllowed) throws Exception;

    public long createNewUser(String name, String profilePictureLocation, String apiKey, String contactNumber) throws Exception;

    public void RSVPToSuggestion(long userId, String apiKey, long meetupId, long suggestionId, SuggestionStatus status) throws Exception;

    public long addNewSuggestion(long userId, String apiKey, long meetupId, String suggestedPlace, Date suggestedTime, boolean suggestionOverride) throws Exception;
    
    public Set<Long> addInviteesToMeetup(long userId, String apiKey, Set<Long> inviteeIds, long meetupId) throws Exception;
    
    public void declineMeetup(long userId, String apiKey, long meetupId) throws Exception;

	public List<Notification> getNotificationsForUser(long userId, String apiKey) throws Exception;

	public void deleteNotifications(List<Notification> notifications);

	public List<Meetup> getMeetupsForDateRange(long userId, String apiKey,
			Date fromDate, Date toDate) throws Exception;

	public List<Suggestion> getSuggestionsForMeetup(long userId, String apiKey, long meetupId) throws Exception;

	public User getUserDetails(long userId);

	public Meetup getMeetupDetails(long userId,	String apiKey, Long meetupId) throws Exception;

	public List<User> getRegisteredUsers(long userId, String apiKey,
			Set<Long> contactNumbers) throws Exception;
}

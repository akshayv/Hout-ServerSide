package com.hout.web.api.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import com.hout.client.ClientApi;
import com.hout.domain.entities.SuggestionStatus;
import com.hout.domain.entities.User;
import com.hout.web.api.HoutRESTService;
import com.hout.web.api.marshaller.format.HoutAdditionalInviteesResponse;
import com.hout.web.api.marshaller.format.HoutException;
import com.hout.web.api.marshaller.format.ListUserResponse;
import com.hout.web.api.marshaller.format.HoutMeetupResponse;
import com.hout.web.api.marshaller.format.HoutSuggestionResponse;
import com.hout.web.api.marshaller.format.HoutUserCreationResponse;
import com.hout.web.api.marshaller.format.HoutUserResponse;
import com.hout.web.api.marshaller.format.Meetup;
import com.hout.web.api.marshaller.format.MeetupRetrievalResponse;
import com.hout.web.api.marshaller.format.MeetupSuggestionResponse;
import com.hout.web.api.marshaller.format.Notification;
import com.hout.web.api.marshaller.format.NotificationResponse;
import com.hout.web.api.marshaller.format.Status;
import com.hout.web.api.marshaller.format.Suggestion;
import com.hout.web.api.marshaller.format.Venue;

public class HoutRESTServiceImpl implements HoutRESTService {
	@Inject
	private ClientApi clientApi;

	public HoutUserCreationResponse createUser(String name,
			String profilePictureLocation, 
			String apiKey, String contactNumber) throws HoutException {

		if(name == null) {
			throw new HoutException("Name not specified");
		}
		if(profilePictureLocation == null) {
			throw new HoutException("Profile Picture location not specified");
		}
		if(apiKey == null) {
			throw new HoutException("Api Key not specified");
		}
		
		if(contactNumber == null) {
			throw new HoutException("Contact Number Not specified");
		}

		HoutUserCreationResponse houtUserResponse = new HoutUserCreationResponse();
		try {
			long userId = clientApi.createNewUser(name, profilePictureLocation, apiKey, contactNumber);
			houtUserResponse.setUserId(userId);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		}
		houtUserResponse.setStatus(Status.SUCCESS);
		return houtUserResponse;
	}
  
	public HoutMeetupResponse createMeetup(long userId, 
			String apiKey, 
			String description, 
			String suggestedLocation,
			String suggestedDate, 
			boolean isFacebookSharing,
			boolean isTwitterSharing, 
			boolean isSuggestionsAllowed, 
			Set<Long> inviteeIds) throws HoutException {
		if(userId == 0) {
			throw new HoutException("userId not specified");
		}
		if(apiKey == null) {
			throw new HoutException("Api Key not specified");
		}
		if(inviteeIds.isEmpty()) {
			throw new HoutException("No Invitees specified");
		}

		Date date = null;
		if(suggestedDate !=null && !suggestedDate.trim().isEmpty())
			try {	
				DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
				date = df.parse(suggestedDate);
			} catch (Exception e) {
				throw new HoutException("Unparsable Date");
			}

		HoutMeetupResponse houtMeetupResponse = new HoutMeetupResponse();
		try {
			long meetupId = clientApi.createNewMeetup(userId, apiKey, description
					, inviteeIds, isFacebookSharing,
					isTwitterSharing, isSuggestionsAllowed);
			houtMeetupResponse.setMeetupId(meetupId);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		}

		if(date != null || (suggestedLocation != null && !suggestedLocation.isEmpty())) {
			try {
				houtMeetupResponse.setSuggestionId(clientApi.
						addNewSuggestion(userId, apiKey, 
								houtMeetupResponse.getMeetupId(), 
								suggestedLocation, date, true));
			} catch (Exception e) {
				throw new HoutException(e.getMessage());
			}
		}
		houtMeetupResponse.setStatus(Status.SUCCESS);
		return houtMeetupResponse;
	}

	public HoutSuggestionResponse addSuggestion(long userId, 
			String apiKey,
			long meetupId, 
			String suggestedLocation,
			String suggestedDate) throws HoutException {

		if(userId == 0) {
			throw new HoutException("userId not specified");
		}
		if(apiKey == null) {
			throw new HoutException("Api Key not specified");
		}
		Date date = null;
		try {	
			DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			date = df.parse(suggestedDate);
		} catch (Exception e) {
			throw new HoutException("Unparsable Date");
		}

		HoutSuggestionResponse houtSuggestionResponse = new HoutSuggestionResponse();

		try {
			long suggestionId = clientApi.addNewSuggestion(userId, apiKey, 
					meetupId, suggestedLocation, date, false);
			houtSuggestionResponse.setSuggestionId(suggestionId);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		}
		houtSuggestionResponse.setStatus(Status.SUCCESS);
		return houtSuggestionResponse;
	}

	public Status RSVPToSuggestion(long userId, 
			String apiKey,
			long meetupId,
			long suggestionId,
			SuggestionStatus status) throws HoutException {

		if(userId == 0) {
			throw new HoutException("userId not specified");
		}
		if(apiKey == null) {
			throw new HoutException("Api Key not specified");
		}
		if(meetupId == 0) {
			throw new HoutException("meetupId not specified");
		}
		if(suggestionId == 0) {
			throw new HoutException("suggestionId not specified");
		}
		if(userId == 0) {
			throw new HoutException("userId not specified");
		}
		if(status == null) {
			throw new HoutException("Status not specified");
		}

		try {
			clientApi.RSVPToSuggestion(userId, apiKey, 
					meetupId, suggestionId, status);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		}
		return Status.SUCCESS;
	}

	public HoutAdditionalInviteesResponse addInviteesToMeetup(long userId,
			String apiKey,
			Set<Long> inviteeIds,
			long meetupId) throws HoutException {
		if(userId == 0) {
			throw new HoutException("userId not specified");
		}
		if(apiKey == null) {
			throw new HoutException("Api Key not specified");
		}
		if(meetupId == 0) {
			throw new HoutException("meetupId not specified");
		}
		if(inviteeIds.size() == 0) {
			throw new HoutException("No invitee ids specified");
		}

		Set<Long> acceptedInvitees = new HashSet<Long>();
		try { 
			acceptedInvitees = clientApi.addInviteesToMeetup(userId, apiKey, 
					inviteeIds, meetupId);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		}
		HoutAdditionalInviteesResponse response = new HoutAdditionalInviteesResponse();
		response.setStatus(Status.SUCCESS);
		response.setAcceptedInvitees(acceptedInvitees);
		return response;
	}

	public Status declineMeetup(long userId, String apiKey, 
			long meetupId) throws HoutException {
		if(userId == 0) {
			throw new HoutException("userId not specified");
		}
		if(apiKey == null) {
			throw new HoutException("Api Key not specified");
		}
		if(meetupId == 0) {
			throw new HoutException("meetupId not specified");
		}
		try {
			clientApi.declineMeetup(userId, apiKey, meetupId);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		}
		return Status.SUCCESS;
	}

	public NotificationResponse getNotifications(long userId,
			String apiKey) throws HoutException {
		List<com.hout.domain.entities.Notification> notifications;
		try { 
			notifications = clientApi.getNotificationsForUser(userId, apiKey);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		} 
		NotificationResponse response = new NotificationResponse();
		for(com.hout.domain.entities.Notification  notification : notifications) {
			response.getNotifications().add(new Notification(notification.getMessage(), notification.getMeetupId()));
		}
		response.setUserId(userId);

		clientApi.deleteNotifications(notifications);

		return response;
	}

	public MeetupRetrievalResponse getMeetupsForDateRange(
			long userId, String apiKey,
			String fromDate, String toDate) throws Exception {
		List<com.hout.domain.entities.Meetup> meetups;
		
		Date parsedFromDate;
		Date parsedToDate;
		try {	
			DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			parsedFromDate = df.parse(fromDate);
			parsedToDate = df.parse(toDate);
		} catch (Exception e) {
			throw new HoutException("Unparsable Date");
		}
		
		try { 
			meetups = clientApi.getMeetupsForDateRange(userId, apiKey, parsedFromDate, parsedToDate);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		} 
		MeetupRetrievalResponse response = new MeetupRetrievalResponse();
		for(com.hout.domain.entities.Meetup  meetup : meetups) {
			response.getMeetups().add(new Meetup(meetup.getId(), meetup.getCreatedDate(),
					meetup.getFinalizedDate(), new Venue(meetup.getFinalizedLocation()),
					meetup.getDescription(), meetup.getIsSuggestionsAllowed(),
					meetup.isTwitterSharing(), meetup.isFacebookSharing()));
		}
		response.setFromDate(parsedFromDate);
		response.setToDate(parsedToDate);

		return response;
	}

	public MeetupSuggestionResponse getSuggestionsForMeetup(
			long userId, String apiKey,	long meetupId) throws Exception {
		List<com.hout.domain.entities.Suggestion> suggestions;
		
		try { 
			suggestions = clientApi.getSuggestionsForMeetup(userId, apiKey, meetupId);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		} 
		MeetupSuggestionResponse response = new MeetupSuggestionResponse();
		for(com.hout.domain.entities.Suggestion suggestion : suggestions) {
			response.getSuggestions().add(new Suggestion(suggestion));
		}

		return response;
	}

	public HoutUserResponse getUserDetails(long userId)
			throws Exception {
		if (userId == 0L) {
			throw new HoutException("No id specified");
		}
		User user = clientApi.getUserDetails(userId);
		if(user == null) {
			throw new HoutException("Incorrect id specified");
		}
		
		return new HoutUserResponse(user);
	}

	@Override
	@GET
	@Path("/getMeetup")
	@Produces("application/json")
	public Meetup getMeetupDetails(
			@QueryParam("userId") long userId,
			@QueryParam("apiKey") String apiKey,
			@QueryParam("meetupId") Long meetupId) throws Exception {
		com.hout.domain.entities.Meetup meetup;
		try { 
			meetup = clientApi.getMeetupDetails(userId, apiKey, meetupId);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		} 
		Meetup returnMeetup = new Meetup(meetup.getId(), meetup.getCreatedDate(),
		meetup.getFinalizedDate(), new Venue(meetup.getFinalizedLocation()),
		meetup.getDescription(), meetup.getIsSuggestionsAllowed(),
		meetup.isTwitterSharing(), meetup.isFacebookSharing());

		return returnMeetup;
	}

	@Override
	@GET
	@Path("/getUsers")
	@Produces("application/json")
	public ListUserResponse getUsers(@QueryParam("userId") long userId,
			@QueryParam("apiKey") String apiKey,
			@QueryParam("contactNumbers") Set<String> contactNumbers)
			throws HoutException {
		List<com.hout.domain.entities.User> users;
		try { 
			users = clientApi.getRegisteredUsers(userId, apiKey, contactNumbers);
		} catch(Exception e) {
			throw new HoutException(e.getMessage());
		} 
		
		ListUserResponse response = new ListUserResponse();
		for(com.hout.domain.entities.User  user : users) {
			response.getUsers().add(new HoutUserResponse(user.getId(), user.getName(), user.getContactNumber()));
		}
		return response;
	}
}
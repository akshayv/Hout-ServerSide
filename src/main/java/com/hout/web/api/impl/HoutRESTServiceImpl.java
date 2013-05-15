package com.hout.web.api.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.QueryParam;


import com.hout.client.ClientApi;
import com.hout.domain.entities.SuggestionStatus;
import com.hout.web.api.HoutRESTService;
import com.hout.web.api.marshaller.format.HoutAdditionalInviteesResponse;
import com.hout.web.api.marshaller.format.HoutException;
import com.hout.web.api.marshaller.format.HoutMeetupResponse;
import com.hout.web.api.marshaller.format.HoutSuggestionResponse;
import com.hout.web.api.marshaller.format.HoutUserResponse;
import com.hout.web.api.marshaller.format.Notification;
import com.hout.web.api.marshaller.format.NotificationResponse;
import com.hout.web.api.marshaller.format.Status;

public class HoutRESTServiceImpl implements HoutRESTService {
   @Inject
   private ClientApi clientApi;

   public HoutUserResponse createUser(String name,
		   String profilePictureLocation, 
		   String apiKey) throws HoutException {
	   
	   if(name == null) {
		   throw new HoutException("Name not specified");
	   }
	   if(profilePictureLocation == null) {
		   throw new HoutException("Profile Picture location not specified");
	   }
	   if(apiKey == null) {
		   throw new HoutException("Api Key not specified");
	   }
	   
	   HoutUserResponse houtUserResponse = new HoutUserResponse();
	   Set<Long> contacts = new HashSet<Long>();
	   try {
		   long userId = clientApi.createNewUser(name, profilePictureLocation, apiKey, contacts);
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
							   suggestedLocation, date));
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
			   meetupId, suggestedLocation, date);
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
   
   public Status declineMeetup(@QueryParam("userId")long userId,
		   @QueryParam("apiKey") String apiKey, 
		   @QueryParam("meetupId")long meetupId) throws HoutException {
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
		    response.getNotifications().add(new Notification(notification.getMessage()));
	    }
	    response.setUserId(userId);
	    
	    clientApi.deleteNotifications(notifications);
	    
	    return response;
    }
}
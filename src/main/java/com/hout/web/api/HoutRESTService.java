package com.hout.web.api;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import com.hout.client.ClientApi;
import com.hout.domain.entities.SuggestionStatus;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@RequestScoped
@Path("/hout")
public class HoutRESTService {
   @Inject
   private ClientApi clientApi;

   @GET
   @Path("/createUser")
   @Produces("text/plain")
   public String createUser(@QueryParam("name") String name,
		   @QueryParam("profilePictureLocation") String profilePictureLocation, 
		   @QueryParam("apiKey") String apiKey) throws Exception {
	   
	   if(name == null) {
		   return "Name not specified";
	   }
	   if(profilePictureLocation == null) {
		   return "Profile Picture location not specified";
	   }
	   if(apiKey == null) {
		   return "Api Key not specified";
	   }
	   
	   List<Long> contacts = new ArrayList<Long>();
	   try {
		   clientApi.createNewUser(name, profilePictureLocation, apiKey, contacts);
	   } catch(Exception e) {
		   return e.getMessage();
	   }
	   return "success";
   }
    
   @GET
   @Path("/createMeetup")
   @Produces("text/plain")
   public String createMeetup(@QueryParam("userId") long userId, 
		   @QueryParam("apiKey") String apiKey, 
		   @QueryParam("description") String description, 
		   @QueryParam("suggestedLocation") String suggestedLocation,
		   @QueryParam("suggestedDate")  String suggestedDate, 
		   @QueryParam("isFacebookSharing") boolean isFacebookSharing,
		   @QueryParam("isTwitterSharing") boolean isTwitterSharing, 
		   @QueryParam("isSuggestionsAllowed") boolean isSuggestionsAllowed, 
		   @QueryParam("inviteeIds") Set<Long> inviteeIds) throws Exception {
	   if(userId == 0) {
		   return "userId not specified";
	   }
	   if(apiKey == null) {
		   return "Api Key not specified";
	   }
	   if(inviteeIds.isEmpty()) {
		   return "No Invitees specified";
	   }
	   
	   Date date;
	   try {	
		   DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		   date = df.parse(suggestedDate);
	   } catch (Exception e) {
		   return "Unparsable Date";
	   }
	   try {
		   clientApi.createNewMeetup(userId, apiKey, description, suggestedLocation,
				   date, inviteeIds, isFacebookSharing,
				   isTwitterSharing, isSuggestionsAllowed);
	   } catch(Exception e) {
		   return e.getMessage();
	   }
	   return "success";
   }
   
   @GET
   @Path("/addSuggestion")
   @Produces("text/plain")
   public String addSuggestion(@QueryParam("userId") long userId, 
		   @QueryParam("apiKey") String apiKey,
		   @QueryParam("meetupId")long meetupId, 
		   @QueryParam("suggestedLocation") String suggestedLocation,
		   @QueryParam("suggestedDate")  String suggestedDate) throws Exception {
		
	   if(userId == 0) {
		   return "userId not specified";
	   }
	   if(apiKey == null) {
		   return "Api Key not specified";
	   }
	   Date date = null;
	   try {	
		   DateFormat df= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		   date = df.parse(suggestedDate);
	   } catch (Exception e) {
		   return "Unparsable Date";
	   }
	   try {
		   clientApi.addNewSuggestion(userId, apiKey, 
			   meetupId, suggestedLocation, date);
		   } catch(Exception e) {
			   return e.getMessage();
	   }
	   return "success";
   }
   
   @GET
   @Path("/RSVP")
   @Produces("text/plain")
   public String RSVPToSuggestion(@QueryParam("userId") long userId, 
		   @QueryParam("apiKey") String apiKey,
		   @QueryParam("meetupId")long meetupId,
		   @QueryParam("suggestionId")long suggestionId,
		   @QueryParam("status")SuggestionStatus status) {
		
	   if(userId == 0) {
		   return "userId not specified";
	   }
	   if(apiKey == null) {
		   return "Api Key not specified";
	   }
	   if(meetupId == 0) {
		   return "meetupId not specified";
	   }
	   if(suggestionId == 0) {
		   return "suggestionId not specified";
	   }
	   if(userId == 0) {
		   return "userId not specified";
	   }
	   if(status == null) {
		   return "Status not specified";
	   }
	   
	   try {
		   clientApi.RSVPToSuggestion(userId, apiKey, 
	  	   meetupId, suggestionId, status);
	   } catch(Exception e) {
		   return e.getMessage();
	   }
	   return "success";
   }
}
package com.hout.web.api;


import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


import com.hout.client.ClientApi;
import com.hout.web.api.marshaller.DateFormat;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@RequestScoped
@Path("/members")
public class HoutRESTService {
   @Inject
   private ClientApi clientApi;

   @GET
   @Path("/createUser")
   @Produces("text/plain")
   public String createUser(@QueryParam("name") String name, @QueryParam("profilePictureLocation") String profilePictureLocation) {
	   clientApi.createNewUser(name, profilePictureLocation, null);
	   return "success";
   }
    
   @GET
   @Path("/createMeetup")
   @Produces("text/plain")
   public String createMeetup(@QueryParam("description") String description, 
		   @QueryParam("suggestedLocation") String suggestedLocation,
		   @QueryParam("suggestedDate") @DateFormat("yyyy-MM-dd HH:mm:ss z") Date suggestedDate, 
		   @QueryParam("isFacebookSharing") boolean isFacebookSharing,
		   @QueryParam("isTwitterSharing") boolean isTwitterSharing, 
		   @QueryParam("isSuggestionsAllowed") boolean isSuggestionsAllowed) {
		
	   clientApi.createNewMeetup(description, suggestedLocation,
			   suggestedDate, null, isFacebookSharing,
			   isTwitterSharing, isSuggestionsAllowed);
	   return "success";
   }
}
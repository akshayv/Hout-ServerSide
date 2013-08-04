package com.hout.web.api;

import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.hout.domain.entities.SuggestionStatus;
import com.hout.web.api.marshaller.format.HoutAdditionalInviteesResponse;
import com.hout.web.api.marshaller.format.HoutException;
import com.hout.web.api.marshaller.format.HoutMeetupResponse;
import com.hout.web.api.marshaller.format.HoutSuggestionResponse;
import com.hout.web.api.marshaller.format.HoutUserCreationResponse;
import com.hout.web.api.marshaller.format.HoutUserResponse;
import com.hout.web.api.marshaller.format.ListUserResponse;
import com.hout.web.api.marshaller.format.Meetup;
import com.hout.web.api.marshaller.format.MeetupRetrievalResponse;
import com.hout.web.api.marshaller.format.MeetupSuggestionResponse;
import com.hout.web.api.marshaller.format.NotificationResponse;
import com.hout.web.api.marshaller.format.Status;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members
 * table.
 */
@RequestScoped
@Path("/hout")
public interface HoutRESTService {

	@GET
	@Path("/createUser")
	@Produces("application/json")
	public HoutUserCreationResponse createUser(
			@QueryParam("name") String name,
			@QueryParam("profilePictureLocation") String profilePictureLocation,
			@QueryParam("apiKey") String apiKey, 
			@QueryParam("contactNumber") String contactNumber) throws HoutException;

	@GET
	@Path("/getUsers")
	@Produces("application/json")
	public ListUserResponse getUsers(
			@QueryParam("userId") long userId,
			@QueryParam("apiKey") String apiKey,
			@QueryParam("contactNumbers") Set<String> contactNumbers) 
					throws HoutException;

	@GET
	@Path("/createMeetup")
	@Produces("application/json") 
	public HoutMeetupResponse createMeetup(@QueryParam("userId") long userId,
			@QueryParam("apiKey") String apiKey,
			@QueryParam("description") String description,
			@QueryParam("suggestedLocation") String suggestedLocation,
			@QueryParam("suggestedDate") String suggestedDate,
			@QueryParam("isFacebookSharing") boolean isFacebookSharing,
			@QueryParam("isTwitterSharing") boolean isTwitterSharing,
			@QueryParam("isSuggestionsAllowed") boolean isSuggestionsAllowed,
			@QueryParam("inviteeIds") Set<Long> inviteeIds)
					throws HoutException;

	@GET
	@Path("/addSuggestion")
	@Produces("application/json")
	public HoutSuggestionResponse addSuggestion(
			@QueryParam("userId") long userId,
			@QueryParam("apiKey") String apiKey,
			@QueryParam("meetupId") long meetupId,
			@QueryParam("suggestedLocation") String suggestedLocation,
			@QueryParam("suggestedDate") String suggestedDate)
					throws HoutException;

	@GET
	@Path("/RSVP")
	@Produces("application/json")
	public Status RSVPToSuggestion(@QueryParam("userId") long userId,
			@QueryParam("apiKey") String apiKey,
			@QueryParam("meetupId") long meetupId,
			@QueryParam("suggestionId") long suggestionId,
			@QueryParam("status") SuggestionStatus status) throws HoutException;

	@GET
	@Path("/addInvitee")
	@Produces("application/json")
	public HoutAdditionalInviteesResponse addInviteesToMeetup(
			@QueryParam("userId") long userId,
			@QueryParam("apiKey") String apiKey,
			@QueryParam("inviteeIds") Set<Long> inviteeIds,
			@QueryParam("meetupId") long meetupId) throws HoutException;

	@GET
	@Path("/declineMeetup")
	@Produces("application/json")
	public Status declineMeetup(@QueryParam("userId") long userId,
			@QueryParam("apiKey") String apiKey,
			@QueryParam("meetupId") long meetupId) throws HoutException;

	@GET
	@Path("/getNotifications")
	@Produces("application/json")
	public NotificationResponse getNotifications(@QueryParam("userId") long userId, 
			@QueryParam("apiKey") String apiKey) throws HoutException;

	@GET
	@Path("/getMeetups")
	@Produces("application/json")
	public MeetupRetrievalResponse getMeetupsForDateRange(@QueryParam("userId") long userId, 
			@QueryParam("apiKey") String apiKey, @QueryParam("fromDate") String fromDate, 
			@QueryParam("toDate")String toDate) throws Exception;
	
	@GET
	@Path("/getMeetup")
	@Produces("application/json")
	public Meetup getMeetupDetails(@QueryParam("userId") long userId, 
			@QueryParam("apiKey") String apiKey, @QueryParam("meetupId") Long meetupId
			) throws Exception;

	@GET
	@Path("/findSuggestions")
	@Produces("application/json")
	public MeetupSuggestionResponse getSuggestionsForMeetup(
			@QueryParam("userId") long userId,
			@QueryParam("apiKey") String apiKey,
			@QueryParam("meetupId") long meetupId) throws Exception;
	
	@GET
	@Path("/getUserDetails")
	@Produces("application/json")
	public HoutUserResponse getUserDetails(@QueryParam("userId") long userId) throws Exception;
	
}
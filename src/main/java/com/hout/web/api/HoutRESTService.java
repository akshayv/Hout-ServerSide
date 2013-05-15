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
import com.hout.web.api.marshaller.format.HoutUserResponse;
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
	public HoutUserResponse createUser(
			@QueryParam("name") String name,
			@QueryParam("profilePictureLocation") String profilePictureLocation,
			@QueryParam("apiKey") String apiKey) throws HoutException;

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
}
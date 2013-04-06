package com.hout.client;


import java.util.Date;
import java.util.Set;

import com.hout.domain.entities.SuggestionStatus;

public interface ClientApi {

    public long createNewMeetup(long userId, String apiKey, String description, String suggestedLocation, Date suggestedDate, Set<Long> contactIds,
                                boolean isFacebookSharing, boolean isTwitterSharing, boolean isSuggestionsAllowed) throws Exception;

    public long createNewUser(String name, String profilePictureLocation, String apiKey, Set<Long> contacts) throws Exception;

    public void RSVPToSuggestion(long userId, String apiKey, long meetupId, long suggestionId, SuggestionStatus status) throws Exception;

    public long addNewSuggestion(long userId, String apiKey, long meetupId, String suggestedPlace, Date suggestedTime) throws Exception;
    
    public Set<Long> addInviteesToMeetup(long userId, String apiKey, Set<Long> inviteeIds, long meetupId) throws Exception;
    
    public void declineMeetup(long userId, String apiKey, long meetupId) throws Exception;
}

package com.hout.client;


import java.util.Date;
import java.util.List;

import com.hout.domain.entities.Meetup;
import com.hout.domain.entities.Suggestion;
import com.hout.domain.entities.SuggestionStatus;

public interface ClientApi {

    public void createNewMeetup(long userId, String apiKey, String description, String suggestedLocation, Date suggestedDate, List<Long> contactIds,
                                boolean isFacebookSharing, boolean isTwitterSharing, boolean isSuggestionsAllowed) throws Exception;

    public void createNewUser(String name, String profilePictureLocation, String apiKey, List<Long> contacts) throws Exception;

    public void RSVPToSuggestion(long userId, String apiKey, long meetupId, long suggestionId, SuggestionStatus status) throws Exception;

    public void addNewSuggestion(long userId, String apiKey, long meetupId, String suggestedPlace, Date suggestedTime) throws Exception;
    
    public List<Suggestion> getSuggestionsForMeetup(long userId, String apiKey, long meetupId) throws Exception;
    
    public Meetup findMeetupById(long userId, String apiKey, long meetupId) throws Exception;
    
}

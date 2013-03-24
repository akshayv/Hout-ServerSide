package com.hout.client;


import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hout.domain.entities.Meetup;
import com.hout.domain.entities.Status;
import com.hout.domain.entities.Suggestion;
import com.hout.domain.entities.SuggestionStatus;
import com.hout.domain.entities.User;

public interface ClientApi {

    public void createNewMeetup(String description, String suggestedLocation, Date suggestedDate, List<Long> contactIds,
                                boolean isFacebookSharing, boolean isTwitterSharing, boolean isSuggestionsAllowed);

    public void createNewUser(String name, String profilePictureLocation, List<Long> contacts);

    public void RSVPToSuggestion(long meetupId, long suggestionId, SuggestionStatus status);

    public void addNewSuggestion(long meetupId, String suggestedPlace, Date suggestedTime );
    
    public List<Suggestion> getSuggestionsForMeetup(long meetupId);
    
    public List<User> getAcceptedForSuggestion(long meetupId, long suggestionId);
    
    public List<User> getRejectedForSuggestion(long meetupId, long suggestionId);
        
    public Map<User, Status> getStatusForMeetup(long meetupId);
    
    public Meetup findMeetupById(long meetupId);
    
}

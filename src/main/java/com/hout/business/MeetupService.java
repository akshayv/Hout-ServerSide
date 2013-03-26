package com.hout.business;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.hout.domain.entities.Meetup;
import com.hout.domain.entities.User;

public interface MeetupService {

	public void addNew(Meetup meetup);
	
    public void removeInvitee(long meetupId, long userId) throws Exception;
    
    public void createNew(User user, String description, 
    		String suggestedLocation, Date suggestedDate, 
    		Set<Long> contactIds, boolean isFacebookSharing, 
    		boolean isTwitterSharing, boolean isSuggestionsAllowed);
    
    public void checkAndFinalizeDetails(long meetupId) throws Exception;
    
    public void addSuggestionToMeetup(long meetupId, long userId, String location, Date date) throws Exception;
    
    public void removeUnnecessarySuggestions(long meetupId) throws Exception;
}

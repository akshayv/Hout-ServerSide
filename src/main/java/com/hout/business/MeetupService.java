package com.hout.business;

import java.util.Date;
import java.util.List;

import com.hout.domain.entities.Meetup;
import com.hout.domain.entities.User;

public interface MeetupService {

	public void addNew(Meetup meetup);
	
    public void removeInvitee(long meetupId, long userId);
    
    public void createNew(User user, String description, 
    		String suggestedLocation, Date suggestedDate, 
    		List<Long> contactIds, boolean isFacebookSharing, 
    		boolean isTwitterSharing, boolean isSuggestionsAllowed);
    
    public void checkAndFinalizeDetails(long meetupId);
    
    public void addSuggestionToMeetup(long meetupId, long userId, String location, Date date);
    
    public void removeUnnecessarySuggestions(long meetupId);
}

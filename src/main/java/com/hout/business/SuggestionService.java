package com.hout.business;

import java.util.Date;

import com.hout.domain.entities.Suggestion;
import com.hout.domain.entities.SuggestionStatus;
import com.hout.domain.entities.User;

public interface SuggestionService {
	
	public Suggestion createNew(User user, String location, Date date);
	
	public void RSVP(User user, long suggestionId, SuggestionStatus status) throws Exception;
	
	public void remove(long id);

}

package com.hout.business.impl;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hout.business.SuggestionService;
import com.hout.business.dao.SuggestionDao;
import com.hout.domain.entities.Suggestion;
import com.hout.domain.entities.SuggestionStatus;
import com.hout.domain.entities.User;

@Stateless
public class SuggestionServiceImpl implements SuggestionService {
	
	@Inject
	SuggestionDao suggestionDao;

	@Override
	public Suggestion createNew(User user, String location, Date date) {
		if(!location.trim().equals("") && date!=null)
			return new Suggestion(user, location, date);
		return null;
	}

	@Override
	public void RSVP(User user, long suggestionId, SuggestionStatus status) {
		Suggestion suggestion = suggestionDao.findById(suggestionId);
		Long userId = user.getId();
		if(suggestion.getUndecidedUserIds().contains(userId)) {
			suggestion.getUndecidedUserIds().remove(userId);
		} else if(suggestion.getAcceptedUserIds().contains(userId)) {
			suggestion.getAcceptedUserIds().remove(userId);
		} else if(suggestion.getRejectedUserIds().contains(userId)) {
			suggestion.getRejectedUserIds().remove(userId);
		}
		setRSVPStatus(userId, suggestion, status);
	}

	private void setRSVPStatus(long userId, Suggestion suggestion, SuggestionStatus status) {
		if(status == SuggestionStatus.YES) {
			suggestion.getAcceptedUserIds().add(userId);
		} else if(status == SuggestionStatus.NO) {
			suggestion.getRejectedUserIds().add(userId);
		}
	}

	@Override
	public void remove(long id) {
		suggestionDao.removeById(id);
	}

}

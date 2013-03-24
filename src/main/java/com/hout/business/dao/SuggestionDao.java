package com.hout.business.dao;

import com.hout.domain.entities.Suggestion;

public interface SuggestionDao extends GenericDao<Suggestion, Integer>{

	public Suggestion findById(long id);
	
	public boolean removeById(long id);
	
	public Suggestion save(Suggestion suggestion);
}

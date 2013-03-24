package com.hout.business.dao;

import com.hout.domain.entities.Venue;

public interface VenueDao extends GenericDao<Venue, Integer>{

	public Venue findById(Long id);

	public Venue save(Venue venue);
	
	public void remove(Venue venue);
	
	public boolean removeById(long id);
}

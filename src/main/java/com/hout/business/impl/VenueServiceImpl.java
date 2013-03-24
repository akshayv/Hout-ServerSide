package com.hout.business.impl;

import javax.inject.Inject;

import com.hout.domain.entities.Venue;
import com.hout.business.VenueService;
import com.hout.business.dao.VenueDao;

public class VenueServiceImpl implements VenueService {

	@Inject
    private VenueDao venueDao;
	
	@Override
	public Venue createNew(String location) {
		Venue venue = new Venue(location);
		Venue existingVenue = venueDao.findByLocation(location);
		if(existingVenue  != null) {
			return existingVenue;
		}
		venueDao.save(venue);
		return venue;
	}

	@Override
	public void removeVenueById(long id) {
		venueDao.removeById(id);
	}

	@Override
	public void removeVenue(Venue venue) {
		venueDao.remove(venue);
	}

	@Override
	public Venue findById(Long id) {
		return venueDao.findById(id);
	}
}

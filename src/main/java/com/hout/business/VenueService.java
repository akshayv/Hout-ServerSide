package com.hout.business;

import com.hout.domain.entities.Venue;

public interface VenueService {

    public void removeVenue(Venue venue);

    public void removeVenueById(long venueId);

    public Venue findById(Long id);
    
    public Venue createNew(String location);
}

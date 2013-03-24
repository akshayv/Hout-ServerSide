package com.hout.business.dao;

import com.hout.domain.entities.Meetup;

public interface MeetupDao extends GenericDao<Meetup, Integer>{

    public boolean removeById(long id);
    
    public Meetup findById(long id);

    public Meetup save(Meetup meetup);
}

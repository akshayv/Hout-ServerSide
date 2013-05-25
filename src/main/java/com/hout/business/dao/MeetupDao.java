package com.hout.business.dao;

import java.util.Date;
import java.util.List;

import com.hout.domain.entities.Meetup;

public interface MeetupDao extends GenericDao<Meetup, Integer>{

    public boolean removeById(long id);
    
    public Meetup findById(long id);

    public Meetup save(Meetup meetup);

	public List<Meetup> findForDateRange(Date fromDate, Date toDate);
}

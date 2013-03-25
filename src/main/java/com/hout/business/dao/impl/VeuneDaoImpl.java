package com.hout.business.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;


import com.hout.business.dao.VenueDao;
import com.hout.domain.entities.Venue;

@Stateless
public class VeuneDaoImpl extends GenericDaoImpl<Venue, Integer> implements VenueDao{
	@Inject
	private EntityManager em;
	
    public VeuneDaoImpl() {
        super(Venue.class);
    }
    
	@Override
	public void remove(Venue venue) {
		super.remove(venue);
	}
	
	@Override
	public boolean removeById(long id) {
	        return super.removeById((int) id);
	}

	@Override
	public Venue findById(Long id) { 
		Query q = em.createQuery("select m from Venue m where m.id=:id");
		q.setParameter("id", id);
		return (Venue) q.getResultList().get(0);
	}
	

	@Override
	public Venue findByLocation(String location) {
		Query q = em.createQuery("select m from Venue m where m.location=:location");
		q.setParameter("location", location);
		@SuppressWarnings("unchecked")
		List<Venue> object = q.getResultList();
		if(object == null || object.size() == 0) {
			return null;
		}
		return (Venue) object.get(0);
	}

	@Override
    public Venue save(Venue venue) {
        if (venue.getId() == 0) {
                persist(venue);
        } else {
                merge(venue);
        }
        return venue;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void setEntityManager(EntityManager entityManager) {
        em = entityManager;
    }
}

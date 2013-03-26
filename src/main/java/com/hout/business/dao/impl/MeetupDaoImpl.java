package com.hout.business.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hout.business.dao.MeetupDao;
import com.hout.domain.entities.Meetup;

@Stateless
public class MeetupDaoImpl extends GenericDaoImpl<Meetup, Integer> implements MeetupDao{
	@Inject
	private EntityManager em;
		
	@Override
	public boolean removeById(long id) {
		return super.removeById((int) id);
	}

	@Override
	public Meetup findById(long id) { 
		Query q = em.createQuery("select m from Meetup m where m.id=:id");
		q.setParameter("id", id);
		try {
			return (Meetup) q.getResultList().get(0);
		} catch (Exception e) {
			return null;
		}
	}

    public MeetupDaoImpl() {
        super(Meetup.class);
    }

    public Meetup save(Meetup meetup) {
        if (meetup.getId() == 0) {
                persist(meetup);
        } else {
                merge(meetup);
        }
        return meetup;
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

package com.hout.business.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;


import com.hout.business.dao.SuggestionDao;
import com.hout.domain.entities.Suggestion;

@Stateless
public class SuggestionDaoImpl extends GenericDaoImpl<Suggestion, Integer> implements SuggestionDao {
	@Inject
	private EntityManager em;
	
    public SuggestionDaoImpl() {
        super(Suggestion.class);
    }
    
	@Override
	public boolean removeById(long id) {
	        return super.removeById((int) id);
	}

	@Override
	public Suggestion findById(long id) { 
		Query q = em.createQuery("select m from Suggestion m where m.id=:id");
		q.setParameter("id", id);
		return (Suggestion) q.getResultList().get(0);
	}

    public Suggestion save(Suggestion suggestion) {
        if (suggestion.getId() == 0) {
                persist(suggestion);
        } else {
                merge(suggestion);
        }
        return suggestion;
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

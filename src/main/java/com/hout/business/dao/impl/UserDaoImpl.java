package com.hout.business.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;


import com.hout.business.dao.UserDao;
import com.hout.domain.entities.User;

@Stateless
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao{
	@Inject
	private EntityManager em;
	
    public UserDaoImpl() {
        super(User.class);
    }
    
	@Override
	public boolean removeById(long id) {
	        return super.removeById((int) id);
	}

	@Override
	public User findById(long id) { 
		Query q = em.createQuery("select m from User m where m.id=:id");
		q.setParameter("id", id);
		return (User) q.getResultList();
	}

    public User save(User user) {
        if (user.getId() == 0) {
                persist(user);
        } else {
                merge(user);
        }
        return user;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    protected void setEntityManager(EntityManager entityManager) {
        em = entityManager;
    }

	@Override
	public void remove(User user) {
		super.remove(user);
	}
}

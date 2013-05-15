package com.hout.business.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.hout.business.dao.NotificationDao;
import com.hout.domain.entities.Notification;
import com.hout.domain.entities.User;

@Stateless
public class NotificationDaoImpl extends  GenericDaoImpl<Notification, Integer> implements NotificationDao{
	@Inject
	private EntityManager em;


	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected void setEntityManager(EntityManager entityManager) {
		em = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNewNotificationsForUser(User user) {
		Query q = em.createQuery("select n from Notification n where n.userId=:userId");
		q.setParameter("userId", user.getId());
		try {
			return (List<Notification>) q.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteList(List<Notification> notifications) {
		for(Notification n : notifications) {
			remove(n);
		}
	}
}

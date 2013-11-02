package com.hout.business.impl;

import java.util.List; 

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hout.business.NotificationService;
import com.hout.business.dao.NotificationDao;
import com.hout.domain.entities.Notification;
import com.hout.domain.entities.User;

@Stateless
public class NotificationServiceImpl implements NotificationService {

	@Inject
	NotificationDao notificationDao;
	

	@Override
	public void notify(long meetupId, String message, Long userId) {
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setUserId(userId);
		notification.setMeetupId(meetupId);
		notificationDao.persist(notification);

	}

	@Override
	public List<Notification> getNewNotificationsForUser(User currentUser) {
		return notificationDao.getNewNotificationsForUser(currentUser);
	}
	
	@Override
	public void deleteList(List<Notification> notifications) {
		notificationDao.deleteList(notifications);
	}

}

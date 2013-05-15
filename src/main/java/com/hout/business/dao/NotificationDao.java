package com.hout.business.dao;

import java.util.List;

import com.hout.domain.entities.Notification;
import com.hout.domain.entities.User;

public interface NotificationDao extends GenericDao<Notification, Integer>{
	
	public List<Notification> getNewNotificationsForUser(User user);

	public void deleteList(List<Notification> notification);
}

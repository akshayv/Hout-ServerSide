package com.hout.business;

import java.util.List;

import com.hout.domain.entities.Notification;
import com.hout.domain.entities.User;

public interface NotificationService {
	
	public void notify(Object o, String message);

	public List<Notification> getNewNotificationsForUser(User currentUser);

	public void deleteList(List<Notification> notifications);
}

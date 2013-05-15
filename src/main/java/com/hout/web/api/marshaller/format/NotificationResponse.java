package com.hout.web.api.marshaller.format;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NotificationResponse {
	
	@XmlElement
	private List<Notification> notifications = new ArrayList<Notification>();
	
	@XmlElement
	private long userId;
	
	public NotificationResponse(List<Notification> notifications, long userId) {
		this.notifications = notifications;
		this.userId = userId;
	}
	
	public NotificationResponse() {
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public List<Notification> getNotifications()  {
		return notifications;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getUserId() {
		return userId;
	}

}

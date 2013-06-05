package com.hout.web.api.marshaller.format;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Notification {

	@XmlElement
	String notification;
	
	@XmlElement
	Long meetupId;
	
	public Notification(String notification, Long meetupId) {
		this.notification = notification;
		this.meetupId = meetupId;
	}
	
	public Long getMeetupId() {
		return meetupId;
	}

	public void setMeetupId(Long meetupId) {
		this.meetupId = meetupId;
	}



	public void setNotification(String notification) {
		this.notification = notification;
	}
	
	public String getNotification() {
		return notification;
	}
}

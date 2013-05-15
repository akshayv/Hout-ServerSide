package com.hout.web.api.marshaller.format;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Notification {

	@XmlElement
	String notification;
	
	public Notification(String notification) {
		this.notification = notification;
	}
	
	public void setNotification(String notification) {
		this.notification = notification;
	}
	
	public String getNotification() {
		return notification;
	}
}

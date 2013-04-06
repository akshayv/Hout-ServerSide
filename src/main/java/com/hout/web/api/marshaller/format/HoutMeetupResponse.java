package com.hout.web.api.marshaller.format;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HoutMeetupResponse {
	
	@XmlElement
	Status status;
	
	@XmlElement
	Long meetupId;
	
	public HoutMeetupResponse() {
	}
	
	public long getMeetupId() {
		return meetupId;
	}
	
	public void setMeetupId(long meetupId) {
		this.meetupId = meetupId;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}

package com.hout.web.api.marshaller.format;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HoutUserCreationResponse {

	@XmlElement
	Status status;
	
	@XmlElement
	Long userId;
	
	public HoutUserCreationResponse() {
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}

package com.hout.web.api.marshaller.format;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HoutAdditionalInviteesResponse {

	@XmlElement
	public Status status;
	
	@XmlElement
	public Set<Long> acceptedInvitees = new HashSet<Long>();
	
	public HoutAdditionalInviteesResponse() {
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Set<Long> getAcceptedInvitees() {
		return acceptedInvitees;
	}
	
	public void setAcceptedInvitees(Set<Long> acceptedInvitees) {
		this.acceptedInvitees = acceptedInvitees;
	}
}

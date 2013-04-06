package com.hout.web.api.marshaller.format;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HoutSuggestionResponse {

	@XmlElement
	Status status;
	
	@XmlElement
	Long suggestionId;
	
	public HoutSuggestionResponse() {
	}
	
	public long getSuggestionId() {
		return suggestionId;
	}
	
	public void setSuggestionId(long suggestionId) {
		this.suggestionId = suggestionId;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
}

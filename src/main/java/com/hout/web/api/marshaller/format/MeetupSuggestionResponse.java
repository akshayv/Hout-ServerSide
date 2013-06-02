package com.hout.web.api.marshaller.format;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MeetupSuggestionResponse {
	
	@XmlElement
	Set<Suggestion> suggestions = new HashSet<Suggestion>();

	public MeetupSuggestionResponse() {
	}

	public Set<Suggestion> getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(Set<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}
	
}

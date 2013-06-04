package com.hout.web.api.marshaller.format;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hout.domain.entities.Venue;

@XmlRootElement
public class Suggestion {

	@XmlElement
	long id;
	
	@XmlElement
	long suggestedUserId;

	@XmlElement
	Venue venue;

	@XmlJavaTypeAdapter(value=JaxbDateAdapter.class, type=Date.class)
	@XmlElement
	Date date;

	@XmlElement
	Set<Long> acceptedUserIds = new HashSet<Long>();

	@XmlElement
	Set<Long> undecidedUserIds = new HashSet<Long>();

	@XmlElement
	Set<Long> rejectedUserIds = new HashSet<Long>();
    
	public Suggestion(com.hout.domain.entities.Suggestion suggestion) {
		this.id = suggestion.getId();
		this.suggestedUserId = suggestion.getSuggestedUserId();
		this.venue = suggestion.getVenue();
		this.date = suggestion.getDate();
		this.acceptedUserIds = suggestion.getAcceptedUserIds();
		this.rejectedUserIds = suggestion.getRejectedUserIds();
		this.undecidedUserIds = suggestion.getUndecidedUserIds();
	}

    
    public Suggestion(long suggestedUserId, Venue venue, Date date) {
    	this.suggestedUserId = suggestedUserId;
    	this.venue = venue;
    	this.date = date;
    	}

	public Suggestion() {
	}

	public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Long> getAcceptedUserIds() {
        return acceptedUserIds;
    }

    public void setAcceptedUserIds(Set<Long> acceptedUserIds) {
        this.acceptedUserIds = acceptedUserIds;
    }

    public Set<Long> getRejectedUserIds() {
        return rejectedUserIds;
    }

    public void setRejectedUserIds(Set<Long> rejectedUserIds) {
        this.rejectedUserIds = rejectedUserIds;
    }
    
    public Set<Long> getUndecidedUserIds() {
        return undecidedUserIds;
    }

    public void setUndecidedUserIds(Set<Long> undecidedUserIds) {
        this.undecidedUserIds = undecidedUserIds;
    }
    
    public long getSuggestedUserId() {
        return suggestedUserId;
    }

    public void setSuggestedUserId(long suggestedUserId) {
        this.suggestedUserId = suggestedUserId;
    }
    
    public long getId() {
    	return id;
    }
    
    public void setId(long id) {
    	this.id = id;
    }
	

}

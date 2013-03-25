package com.hout.domain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Suggestion implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	long suggestedUserId;

    Venue venue;

    Date date;

    @ElementCollection
    List<Long> acceptedUserIds;

    @ElementCollection
    List<Long> undecidedUserIds;

    @ElementCollection
    List<Long> rejectedUserIds;

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

    public List<Long> getAcceptedUserIds() {
        return acceptedUserIds;
    }

    public void setAcceptedUserIds(List<Long> acceptedUserIds) {
        this.acceptedUserIds = acceptedUserIds;
    }

    public List<Long> getRejectedUserIds() {
        return rejectedUserIds;
    }

    public void setRejectedUserIds(List<Long> rejectedUserIds) {
        this.rejectedUserIds = rejectedUserIds;
    }
    
    public List<Long> getUndecidedUserIds() {
        return undecidedUserIds;
    }

    public void setUndecidedUserIds(List<Long> undecidedUserIds) {
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

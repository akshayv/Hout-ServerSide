package com.hout.domain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	
	User suggestedUser;

    Venue venue;

    Date date;

    List<Long> acceptedUserIds;
    
    List<Long> undecidedUserIds;

    List<Long> rejectedUserIds;

    public Suggestion(User suggestedUser, String location, Date date) {
    	this.suggestedUser = suggestedUser;
    	this.venue = new Venue(location);
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
    
    public User getSuggestedUser() {
        return suggestedUser;
    }

    public void setSuggestedUser(User suggestedUser) {
        this.suggestedUser = suggestedUser;
    }
    
    public long getId() {
    	return id;
    }
    
    public void setId(long id) {
    	this.id = id;
    }
}

package com.hout.domain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Meetup implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;

    String description;

    Venue finalizedLocation;

    Date finalizedDate;

    @ElementCollection
    Set<Suggestion> suggestions = new HashSet<Suggestion>();
    
    @ElementCollection
    Set<Long> inviteeIds = new HashSet<Long>();

    @ElementCollection
    Set<Long> rejectedUserIds = new HashSet<Long>();

    @ElementCollection
    Set<String> pictureLocations = new HashSet<String>();

    @NotNull
    boolean isFacebookSharing;

    @NotNull
    boolean isTwitterSharing;
    
    @NotNull
    boolean isSuggestionsAllowed;

    Date createdDate = new Date();
    
    public Meetup()  {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Venue getFinalizedLocation() {
        return finalizedLocation;
    }

    public void setFinalizedLocation(Venue finalizedLocation) {
        this.finalizedLocation = finalizedLocation;
    }

    public Date getFinalizedDate() {
        return finalizedDate;
    }

    public void setFinalizedDate(Date finalizedDate) {
        this.finalizedDate = finalizedDate;
    }

    public Set<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(Set<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public Set<Long> getRejectedUserIds() {
        return rejectedUserIds;
    }

    public void setRejectedUserIds(Set<Long> rejetctedUserIds) {
        this.rejectedUserIds = rejetctedUserIds;
    }

    public Set<String> getPictureLocations() {
        return pictureLocations;
    }

    public void setPictureLocations(Set<String> pictureLocations) {
        this.pictureLocations = pictureLocations;
    }

    public boolean isFacebookSharing() {
        return isFacebookSharing;
    }

    public void setFacebookSharing(boolean facebookSharing) {
        isFacebookSharing = facebookSharing;
    }

    public boolean isTwitterSharing() {
        return isTwitterSharing;
    }

    public void setTwitterSharing(boolean twitterSharing) {
        isTwitterSharing = twitterSharing;
    }
    
    public boolean getIsSuggestionsAllowed() {
        return isSuggestionsAllowed;
    }

    public void setIsSuggestionsAllowed(boolean isSuggestionsAllowed) {
        this.isSuggestionsAllowed = isSuggestionsAllowed;
    }

    public void addSuggestions(Suggestion suggestion) {
    	suggestions.add(suggestion);
    }

    public Set<Long> getInviteeIds() {
        return inviteeIds;
    }

    public void setInviteeIds(Set<Long> inviteeIds) {
        this.inviteeIds = inviteeIds;
    }

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}

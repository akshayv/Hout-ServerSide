package com.hout.domain.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

	@NotNull
    String description;

    Venue finalizedLocation;

    Date finalizedDate;

    @ElementCollection
    List<Suggestion> suggestions;
    
    @ElementCollection
    List<Long> inviteeIds;

    @ElementCollection
    Map<Long, Status> inviteeStatus;

    @ElementCollection
    List<String> pictureLocations;

    @NotNull
    boolean isFacebookSharing;

    @NotNull
    boolean isTwitterSharing;
    
    @NotNull
    boolean isSuggestionsAllowed;

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

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public Map<java.lang.Long, Status> getInviteeStatus() {
        return inviteeStatus;
    }

    public void setInviteeStatus(Map<java.lang.Long, Status> inviteeStatus) {
        this.inviteeStatus = inviteeStatus;
    }

    public List<String> getPictureLocations() {
        return pictureLocations;
    }

    public void setPictureLocations(List<String> pictureLocations) {
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

    public List<Long> getInviteeIds() {
        return inviteeIds;
    }

    public void setInviteeIds(List<Long> inviteeIds) {
        this.inviteeIds = inviteeIds;
    }

}

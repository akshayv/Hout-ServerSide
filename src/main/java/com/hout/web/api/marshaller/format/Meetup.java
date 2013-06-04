package com.hout.web.api.marshaller.format;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(value=JaxbDateAdapter.class, type=Date.class)
@XmlRootElement
public class Meetup {
	
	@XmlElement
    long id;
	
	@XmlElement
    String description;

	@XmlElement
	Date createdDate;

	@XmlElement
	Venue finalizedLocation;

	@XmlElement
	Date finalizedDate;

	@XmlElement
	boolean suggestionsAllowed;

	@XmlElement
	boolean twitterSharing;

	@XmlElement
	boolean facebookSharing;
    
    public Meetup() {
	}

	public Meetup(long id, Date createdDate, 
			Date finalizedDate, Venue venue,
			String description, boolean isSuggestionsAllowed, 
			boolean isTwitterSharing, boolean isFacebookSharing) {
	
		this.id = id;
		this.description = description;
		this.createdDate = createdDate;
		this.finalizedDate = finalizedDate;
		this.finalizedLocation = venue;
		this.suggestionsAllowed = isSuggestionsAllowed;
		this.facebookSharing = isFacebookSharing;
		this.twitterSharing = isTwitterSharing;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public boolean isSuggestionsAllowed() {
		return suggestionsAllowed;
	}

	public void setSuggestionsAllowed(boolean suggestionsAllowed) {
		this.suggestionsAllowed = suggestionsAllowed;
	}

	public boolean isTwitterSharing() {
		return twitterSharing;
	}

	public void setTwitterSharing(boolean twitterSharing) {
		this.twitterSharing = twitterSharing;
	}

	public boolean isFacebookSharing() {
		return facebookSharing;
	}

	public void setFacebookSharing(boolean facebookSharing) {
		this.facebookSharing = facebookSharing;
	}

}

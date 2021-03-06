package com.hout.domain.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;

    String name;

    String profilePictureLocation;
    
    String apiKey;

    @ElementCollection
    Set<Long> contactIds = new HashSet<Long>();

    @ElementCollection
    Set<Meetup> currentMeetupIds = new HashSet<Meetup>();

    @ElementCollection
    Set<Meetup> pastMeetupIds = new HashSet<Meetup>();

    String contactNumber;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProfilePictureLocation() {
        return profilePictureLocation;
    }

    public void setProfilePictureLocation(String profilePictureLocation) {
        this.profilePictureLocation = profilePictureLocation;
    }
    
    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Set<java.lang.Long> getContactIds() {
        return contactIds;
    }

    public void setContactIds(Set<java.lang.Long> contactIds) {
        this.contactIds = contactIds;
    }

    public Set<Meetup> getCurrentMeetupIds() {
        return currentMeetupIds;
    }

    public void setCurrentMeetupIds(Set<Meetup> currentMeetupIds) {
        this.currentMeetupIds = currentMeetupIds;
    }

    public Set<Meetup> getPastMeetupIds() {
        return pastMeetupIds;
    }

    public void setPastMeetupIds(Set<Meetup> pastMeetupIds) {
        this.pastMeetupIds = pastMeetupIds;
    }


    public void addContact(long contactId) {
        contactIds.add(contactId);
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


}

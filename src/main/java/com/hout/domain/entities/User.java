package com.hout.domain.entities;

import java.io.Serializable;
import java.util.List;

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

    @ElementCollection
    List<Long> contactIds;

    @ElementCollection
    List<Meetup> currentMeetupIds;

    @ElementCollection
    List<Meetup> pastMeetupIds;

    long contactNumber;


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

    public List<java.lang.Long> getContactIds() {
        return contactIds;
    }

    public void setContactIds(List<java.lang.Long> contactIds) {
        this.contactIds = contactIds;
    }

    public List<Meetup> getCurrentMeetupIds() {
        return currentMeetupIds;
    }

    public void setCurrentMeetupIds(List<Meetup> currentMeetupIds) {
        this.currentMeetupIds = currentMeetupIds;
    }

    public List<Meetup> getPastMeetupIds() {
        return pastMeetupIds;
    }

    public void setPastMeetupIds(List<Meetup> pastMeetupIds) {
        this.pastMeetupIds = pastMeetupIds;
    }


    public void addContact(long contactId) {
        contactIds.add(contactId);
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }


}

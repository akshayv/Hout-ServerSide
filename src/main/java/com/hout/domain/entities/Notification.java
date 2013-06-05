package com.hout.domain.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;

    @NotBlank
    @NotNull
	String message;

    @NotNull
    long userId;
    
    @NotNull
    long meetupId;
    
    public Notification()  {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getUserId() {
        return userId;
    }
    
    public long getMeetupId() {
		return meetupId;
	}

	public void setMeetupId(long meetupId) {
		this.meetupId = meetupId;
	}

	public void setUserId(long userId) {
        this.userId =  userId;
    }
    
}

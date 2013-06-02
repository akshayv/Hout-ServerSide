package com.hout.web.api.marshaller.format;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.hout.domain.entities.User;

@XmlRootElement
public class HoutUserResponse {
	@XmlElement
	long id;

	@XmlElement
	String name;
    
	@XmlElement
	String apiKey;

	@XmlElement
	long contactNumber;

    public HoutUserResponse() {
    }
    
    public HoutUserResponse(User user) {
    	this.id = user.getId();
    	this.name = user.getName();
    	this.apiKey = user.getApiKey();
    	this.contactNumber = user.getContactNumber();
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

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }



}

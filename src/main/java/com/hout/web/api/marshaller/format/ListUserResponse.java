package com.hout.web.api.marshaller.format;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class ListUserResponse {
	
	@XmlElement(name = "registeredUsers")
	private List<HoutUserResponse> users = new ArrayList<HoutUserResponse>();

	public ListUserResponse(List<HoutUserResponse> users) {
		this.users = users;
	}
	
	public ListUserResponse() {
	}

	public void setUsers(List<HoutUserResponse> users) {
		this.users = users;
	}
	
	public List<HoutUserResponse> getUsers()  {
		return users;
	}

}

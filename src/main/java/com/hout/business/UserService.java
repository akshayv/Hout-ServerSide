package com.hout.business;

import java.util.List;

import com.hout.domain.entities.User;

public interface UserService {

    public void addNewUser(User user);

    public void removeUser(User user);

    public void removeUserById(long userId);

    public User findById(Long id);
    
    public User createNewUser(String name, String profilePictureLocation,
			List<Long> contacts);

	public User findCurrentUser(); 
	
	public String getApiKeyForUserId(long userId);
}

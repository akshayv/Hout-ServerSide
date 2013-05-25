package com.hout.business;

import java.util.Set;

import com.hout.domain.entities.User;

public interface UserService {

    public long addNewUser(User user);

    public void removeUser(User user);

    public void removeUserById(long userId);

    public User findById(Long id);
    
    public User createNewUser(String name, String profilePictureLocation,
			String apiKey, Set<Long> contacts, long contactNumber);

	public User findCurrentUser(); 
	
	public String getApiKeyForUserId(long userId) throws Exception;
}

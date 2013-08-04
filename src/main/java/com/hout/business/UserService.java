package com.hout.business;

import java.util.List;
import java.util.Set;

import com.hout.domain.entities.User;

public interface UserService {

    public long addNewUser(User user);

    public void removeUser(User user);

    public void removeUserById(long userId);

    public User findById(Long id);
    
    public User createNewUser(String name, String profilePictureLocation,
			String apiKey, String contactNumber);

	public User findCurrentUser(); 
	
	public String getApiKeyForUserId(long userId) throws Exception;

	public List<User> getRegisteredUsers(Set<String> contactNumbers);
}

package com.hout.business.impl;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.hout.business.UserService;
import com.hout.business.dao.UserDao;
import com.hout.domain.entities.User;

@Stateless
public class UserServiceImpl implements UserService{

	@Inject
    UserDao userDao;

	@Override
    public long addNewUser(User user) {
        userDao.save(user);
        return user.getId();
    }

    @Override
    public void removeUser(User user) {
        userDao.remove(user);
    }

    @Override
    public void removeUserById(long userId) {
        userDao.removeById(userId);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }
    
    @Override
    public User createNewUser(String name, 
    		String profilePictureLocation, String apiKey,
			String contactNumber) {
    	User user = new User();
		user.setName(name);
		user.setProfilePictureLocation(profilePictureLocation);
		user.setApiKey(apiKey);
		user.setContactNumber(contactNumber);
		return user;	
    }

	@Override
	public User findCurrentUser() {
		return findById((long) 1);
	}

	@Override
	public String getApiKeyForUserId(long userId) throws Exception {
		String apiKey = userDao.getApiKeyForUserId(userId);
		if(apiKey == null) {
			throw new Exception("Cannot find user. Please checck Id");
		}
		return apiKey;
	}

	@Override
	public List<User> getRegisteredUsers(Set<Long> contactNumbers) {
		return userDao.getUsersWithContactNumbers(contactNumbers);
	}
}

package com.hout.business.dao;

import java.util.List;
import java.util.Set;

import com.hout.domain.entities.User;

public interface UserDao extends GenericDao<User, Integer>{

   public boolean removeById(long id);

    public void remove(User user);

    public User findById(long id);

    public User save(User user);

	public String getApiKeyForUserId(long userId);

	public List<User> getUsersWithContactNumbers(Set<String> contactNumbers);
}

package com.matt.business.dao;

import com.matt.business.model.User;

public interface UserDao {
	User getUser(User user);
	
	void addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(int userId);

}

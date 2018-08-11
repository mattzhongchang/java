package com.matt.business.dao;

import java.util.List;

import com.matt.business.model.User;

public interface UserDao {
	User getUser(User user);
	
	int addUser(User user);
	
	void updateUser(User user);
	
	void deleteUser(int userId);
	
	List<User> selectUser();

}

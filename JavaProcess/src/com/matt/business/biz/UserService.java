package com.matt.business.biz;

import java.util.List;

import com.matt.business.model.User;

public interface UserService {

	void addUser(final User user);
	
	int saveUser(User user);
	
	public void addUsetTest();
	
	List<User> selectUser();
}

package com.matt.business.biz;

import com.matt.business.model.User;

public interface UserService {

	void addUser(final User user);
	
	int saveUser(User user);
}

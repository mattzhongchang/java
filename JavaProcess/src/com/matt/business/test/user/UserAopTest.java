package com.matt.business.test.user;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matt.business.biz.UserService;
import com.matt.business.model.User;

public class UserAopTest {

	private static final String log4j = "config/log4j.properties";
	
	private static final String[] spring_config = new String[]{"user.xml", "yyadb.xml", "srpdb.xml"};
	
	public static Logger logger = null;
	
	public static void main(String[] args) {
		
		PropertyConfigurator.configure(log4j);
		logger = Logger.getLogger(UserAopTest.class);
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(spring_config);
		UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
		
		User user = new User();
		user.setId(1);
		user.setUsername("Jessica");
		user.setPassword("123");
		userService.addUser(user);
		
	}
}

package com.matt.business.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matt.business.biz.HelloWorldService;
import com.matt.business.dao.SubscriberDao;
import com.matt.business.dao.UserDao;
import com.matt.business.model.Subscriber;
import com.matt.business.model.User;
import com.matt.common.db.MultipleDataSource;

public class UserController {

	private static final String log4j = "config/log4j.properties";
	
	private static final String[] spring_config = new String[]{"ApplicationContext.xml", "yyadb.xml", "srpdb.xml"};
	
	public static void main(String[] args)
	{
		PropertyConfigurator.configure(log4j);
		Logger log = Logger.getLogger(UserController.class);
		ApplicationContext ctx = null;
		
		ctx = new ClassPathXmlApplicationContext(spring_config);
		log.debug("Spring loaded");
		UserDao userDao = (UserDao) ctx.getBean("userDao");
		
		User user = new User();
		user.setId(1);
		user.setUsername("Jessica");
		user.setPassword("123");
		userDao.addUser(user);
		
		user.setId(2);
		user.setUsername("Jessica2");
		user.setPassword("123");
		userDao.addUser(user);
		
		user.setPassword("Jessica");
		user.setPassword("123");
		System.out.println(userDao.getUser(user));
		
		user.setId(2);
		user.setPassword("45678994");
		userDao.updateUser(user);
		
		
		System.out.println("123");
		
//		userDao.deleteUser(10001);
//		UserService userServiceImpl = (UserService) ctx.getBean("userServiceImpl");
//		
//		user.setId(3333);
//		userServiceImpl.addUser(user);
//		
//		UserService userService = (UserService) ctx.getBean("userService");
//		user.setId(2222333);
//		userService.saveUser(user);
		
		HelloWorldService helloWorldService = (HelloWorldService) ctx.getBean("helloWorldService");
		helloWorldService.sayHello();
		
		MultipleDataSource.setDataSourceKey("srpDataSource");
		userDao.addUser(user);
		
		
		MultipleDataSource.setDataSourceKey("jdbcDataSource");
		SubscriberDao subsDao = (SubscriberDao) ctx.getBean("subsDao");
		
		for (int i=0; i<100; i++)
		{
			Subscriber subs = new Subscriber();
			long currDate = System.currentTimeMillis() - 1500000000000L + i;
			subs.setSubsid(currDate);
			subs.setCustid(currDate);
			subs.setAccountid(currDate);
			subs.setCustid(currDate);
			subs.setSubsType("GMS");
			
			subsDao.insertSubscriber(subs);
		}
		
	}
}

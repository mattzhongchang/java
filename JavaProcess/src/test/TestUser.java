package test;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.spring.bean.ConfigBean;

import com.matt.business.biz.UserService;
import com.matt.business.model.User;
import com.matt.business.test.UserController;

public class TestUser {

	private static final String log4j = "config/log4j.properties";
	
	private static final String[] spring_config = new String[]{"ApplicationContext.xml", "yyadb.xml", "srpdb.xml"};
	
	public static void main(String[] args)
	{
		PropertyConfigurator.configure(log4j);
		Logger log = Logger.getLogger(UserController.class);
		
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
		
		ApplicationContext ctx = null;
		
		ctx = new ClassPathXmlApplicationContext(spring_config);
		log.debug("Spring loaded");
		
		UserService userService = (UserService) ctx.getBean("userServiceImpl");
		
		ConfigBean configBean = (ConfigBean) ctx.getBean("configBean");
		System.out.println(configBean.getName());
		System.out.println(configBean.getPassword());
		
		userService.addUsetTest();
		
		System.out.println("123");
	
		User user = new User();
		user.setId(1);
		user.setUsername("Jessica");
		user.setPassword("123");
		userService.saveUser(user);
		userService.saveUser(user);
		
		userService.selectUser();
		
		
		

		
	}
}

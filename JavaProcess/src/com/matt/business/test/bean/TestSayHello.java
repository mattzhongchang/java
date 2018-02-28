package com.matt.business.test.bean;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class TestSayHello {


	private static final String log4j = "config/log4j.properties";
	
	private static final String[] spring_config = new String[]{"classpath:config/resource.xml"};
	
	public static void main(String[] args)
	{
		PropertyConfigurator.configure(log4j);
		Logger log = Logger.getLogger(TestSayHello.class);
		ApplicationContext ctx = null;
		
		ctx = new ClassPathXmlApplicationContext(spring_config);
		log.debug("Spring loaded");
		SayHelloService sayHelloService = (SayHelloService) ctx.getBean("sayHelloService");
		sayHelloService.say();
		
		
		
	}
}

package com.matt.mattspring.test.context;

import org.junit.Test;

import com.matt.mattspring.context.ApplicationContext;
import com.matt.mattspring.context.ClassPathXmlApplicationContext;
import com.matt.mattspring.test.HelloWorldService;
import com.matt.mattspring.test.HelloWorldServiceImpl;

public class ApplicationContextTest {

	@Test
	public void test() throws Exception
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resource.xml");
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		helloWorldService.helloWorld();
		
	}
	
	@Test
	public void testPostBeanProcessor() throws Exception
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("postbeanprocessor.xml");
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		helloWorldService.helloWorld();
		
	}
}

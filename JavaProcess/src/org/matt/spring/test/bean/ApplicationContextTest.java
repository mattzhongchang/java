package org.matt.spring.test.bean;

import junit.framework.Assert;

import org.junit.Test;
import org.matt.spring.beans.factory.BeanFactory;
import org.matt.spring.context.support.ClassPathXmlApplicationContext;

import com.matt.business.test.bean.SayHelloService;

public class ApplicationContextTest {

	@Test
	public void testApplicationContext() throws Exception
	{
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/resource.xml");
		BeanFactory beanFactory = applicationContext.getBeanFactory();
		Object obj = beanFactory.getBean("sayHelloService");
		SayHelloService service = (SayHelloService) obj;
		service.say();
		
		String text = service.getText();
		Assert.assertEquals(text, "textValue");
		
	}
}

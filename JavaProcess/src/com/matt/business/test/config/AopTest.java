package com.matt.business.test.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matt.business.biz.HelloWorldService;
import com.matt.business.test.bean.TestSayHello;

public class AopTest {

	private static final String log4j = "config/log4j.properties";
	
	private static final String[] spring_config = new String[]{"classpath:config/aop-config.xml"};
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");  
		PropertyConfigurator.configure(log4j);
		Logger log = Logger.getLogger(TestSayHello.class);
		ApplicationContext ctx = null;
		
		ctx = new ClassPathXmlApplicationContext(spring_config);
		log.debug("Spring loaded");
		HelloWorldService helloWorldService = (HelloWorldService) ctx.getBean("helloWorldService");
		helloWorldService.sayHello();
		
//		Field field = System.class.getDeclaredField("props");    
//		field.setAccessible(true);    
//		Properties props = (Properties) field.get(null);    
		
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("com/matt/business/test/config/AopTest.class");
//		InputStream inputStream = classLoader.getResourceAsStream("com/matt/business/biz/$Proxy0.class");
		
		
		try {
			FileOutputStream fileOut = new FileOutputStream("config/abc.class");
			
			 byte[] bytes = new byte[1024];
			 try {
				while (inputStream.read(bytes) > -1)
				{
					fileOut.write(bytes);
					bytes = new byte[1024];
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}

package com.matt;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matt.spring.factorybean.Animal;



public class App {

	private static final String log4j = "config/log4j.properties";
	
	private static final String[] spring_config = new String[]{"ApplicationContext.xml", "yyadb.xml", "srpdb.xml"};
	
	public static void main(String[] args)
	{
		PropertyConfigurator.configure(log4j);
		Logger log = Logger.getLogger(App.class);
		ApplicationContext ctx = null;
		
		ctx = new ClassPathXmlApplicationContext(spring_config);
		log.debug("Spring loaded");
		
		Object object = (Object) ctx.getBean("animalBean");
		if (object instanceof Animal)
		{
			Animal animal = (Animal) object;
			animal.move();
		}

		ClassLoader classLoader = ctx.getClassLoader();
		String className = classLoader.getClass().getName();
		System.out.println(className);
		String simpleName = classLoader.getClass().getSimpleName();
		System.out.println(simpleName);
		Field[] fields = classLoader.getClass().getDeclaredFields();
		for (Field field : fields)
		{
			System.out.println(field);
		}
		
		
		
//		Animal animal = null;
//		try {
//			animal = (Animal) animalFactoryBean.getObject();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		animal.move();
		
	}
}

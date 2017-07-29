package com.matt;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matt.spring.factorybean.Animal;
import com.matt.spring.factorybean.AnimalFactoryBean;



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

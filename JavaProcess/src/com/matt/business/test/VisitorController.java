package com.matt.business.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matt.business.biz.InterestedVisitorService;
import com.matt.business.model.InterestedVisitor;


public class VisitorController {


	private static final String log4j = "config/log4j.properties";
	
	private static final String[] spring_config = new String[]{"ApplicationContext.xml", "yyadb.xml", "srpdb.xml"};
	
	public static void main(String[] args)
	{
		PropertyConfigurator.configure(log4j);
		Logger log = Logger.getLogger(UserController.class);
		ApplicationContext ctx = null;
		
		ctx = new ClassPathXmlApplicationContext(spring_config);
		log.debug("Spring loaded");
		InterestedVisitorService interestedVisitorService = (InterestedVisitorService) ctx.getBean("interestedVisitorServiceImpl");
//		interestedVisitorService.addVisitor();
	    long startTime = System.currentTimeMillis();
		List<InterestedVisitor> list = interestedVisitorService.selectInterestedVisitor();
		log.info(list.size() + " ʱ�䣺" + (System.currentTimeMillis() - startTime));
		
		
	}
}

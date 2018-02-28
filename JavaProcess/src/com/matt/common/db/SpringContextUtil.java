package com.matt.common.db;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service("springContextUtil")
public class SpringContextUtil implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
	}

	public static Object getBean(String name)
	{
		return SpringContextUtil.applicationContext.getBean(name);
	}
	
}

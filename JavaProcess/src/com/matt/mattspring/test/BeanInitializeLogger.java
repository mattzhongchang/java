package com.matt.mattspring.test;

import com.matt.mattspring.bean.BeanPostProcessor;

public class BeanInitializeLogger implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws Exception {
		System.out.println("Initialize bean " + beanName + " before");
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws Exception {
		System.out.println("Initialize bean " + beanName + " after");
		return bean;
	}

	
	

	
}

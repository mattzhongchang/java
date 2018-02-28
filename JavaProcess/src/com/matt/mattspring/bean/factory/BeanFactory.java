package com.matt.mattspring.bean.factory;

import com.matt.mattspring.bean.BeanDefinition;

public interface BeanFactory {

	Object getBean(String name) throws Exception;
	
	
}

package org.matt.spring.beans.factory.support;

import org.matt.spring.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
	
	void removeBeanDefinition(String beanName);
	
	BeanDefinition getBeanDefinition(String beanName);
	
	boolean containsBeanDefinition(String beanName);
	
	void registerAlias(String beanName, String aliase);
	
	
}

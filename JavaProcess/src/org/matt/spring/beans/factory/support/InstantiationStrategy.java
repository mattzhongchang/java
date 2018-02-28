package org.matt.spring.beans.factory.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.matt.spring.beans.factory.BeanFactory;

public interface InstantiationStrategy {

	Object instantiate(RootBeanDefinition beanDefinition, String beanName, BeanFactory owner) throws Exception;
	
	Object instantiate(RootBeanDefinition beanDefinition, String beanName, BeanFactory owner, 
			Constructor<?> ctor, Object[] args) throws Exception;
	
	Object instantiate(RootBeanDefinition beanDefinition, String beanName, BeanFactory owner, 
			Object factoryBean, Method factoryMethod, Object[] args) throws Exception;
}

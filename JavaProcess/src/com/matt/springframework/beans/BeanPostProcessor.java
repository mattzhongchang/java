package com.matt.springframework.beans;

public interface BeanPostProcessor {

	Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception;
	
	Object postProcessAfterInitailization(Object bean, String beanName) throws Exception;
}

package com.matt.springframework.beans;

/**
 * �������ж�ȡBeanDefinition
 * @author Administrator
 *
 */
public interface BeanDefinitionReader {

	void loadBeanDefinitions(String location) throws Exception;
}

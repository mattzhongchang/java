package org.matt.spring.beans.factory.support;

public interface BeanDefinitionReader {

	
	BeanDefinitionRegistry getRegistry();
	
	int loadBeanDefinitions(String location);
}

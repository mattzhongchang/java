package org.matt.spring.beans.factory.config;

public class RuntimeBeanReference implements BeanReference{

	private String beanName;
	
	
	public RuntimeBeanReference(String beanName)
	{
		this.beanName = this.beanName;
	}
	
	@Override
	public String getBeanName() {
		return this.beanName;
	}

	
}

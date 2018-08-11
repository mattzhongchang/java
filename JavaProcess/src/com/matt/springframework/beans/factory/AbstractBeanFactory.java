package com.matt.springframework.beans.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import com.matt.springframework.beans.BeanDefinition;
import com.matt.springframework.beans.BeanPostProcessor;


public abstract class AbstractBeanFactory implements BeanFactory{
	                                                            
	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

	private final List<String> beanDefinitionNames = new ArrayList<String>();
	
	private final List<BeanPostProcessor> beanPostProcessor = new ArrayList<BeanPostProcessor>();

	@Override
	public Object getBean(String name) throws Exception 
	{
		BeanDefinition beanDefinition = this.beanDefinitionMap.get(name);
		if (beanDefinition == null)
		{
			throw new IllegalArgumentException("No bean named " + name + " is defined");
		}
		
		Object bean = beanDefinition.getBean();
		if (bean == null)
		{
			bean = doCreateBean(beanDefinition);
			bean = initializeBean(bean, name);
			beanDefinition.setBean(bean);
		}
		
		return bean;
	}
	
	protected Object initializeBean(Object bean, String name) throws Exception
	{
		for (BeanPostProcessor beanPostProcessor : this.beanPostProcessor)
		{
			bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
		}
		
		// TODO:call initialize method
		for (BeanPostProcessor beanPostProcessor : this.beanPostProcessor)
		{
			bean = beanPostProcessor.postProcessAfterInitailization(bean, name);
		}
		return bean;
	}
	
	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception
	{
		Object bean = createBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}
	
	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
		return beanDefinition.getBeanClass().newInstance();
	}
	
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

	}

	public List getBeansForType(Class type) throws Exception
	{
		List beans = new ArrayList();
		for (String beanDefinitionName : beanDefinitionNames)
		{
			if (type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass()))
			{
				beans.add(getBean(beanDefinitionName));
			}
		}
		return beans;
	}
	
	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception
	{
		this.beanDefinitionMap.put(name, beanDefinition);
		this.beanDefinitionNames.add(name);
	}
	
	public void preInstantiateSingletons() throws Exception
	{
		for (java.util.Iterator<String> it = this.beanDefinitionNames.iterator(); it.hasNext(); )
		{
			String beanName = (String) it.next();
			getBean(beanName);
		}
	}
	
	
}

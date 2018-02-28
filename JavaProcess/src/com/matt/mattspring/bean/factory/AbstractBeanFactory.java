package com.matt.mattspring.bean.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.matt.mattspring.bean.BeanDefinition;
import com.matt.mattspring.bean.BeanPostProcessor;

public abstract class AbstractBeanFactory implements BeanFactory{

	private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
	
	private final List<String> beanDefinitionNames = new ArrayList<String>(); 

	private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();
	
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
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors)
		{
			bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
		}
		
		// TODO:call initialize method
		for (BeanPostProcessor beanPostProcessor : this.beanPostProcessors)
		{
			bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
		}
		
		return bean;
	}
	

	public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception
	{
		this.beanDefinitionMap.put(name, beanDefinition);
		this.beanDefinitionNames.add(name);
	}
	
	public void preInstantiateSingletons() throws Exception
	{
		for (Iterator<String> it = this.beanDefinitionNames.iterator(); it.hasNext(); )
		{
			getBean(it.next());
		}
	}
	

	protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception{
		Object bean = createBeanInstance(beanDefinition);
		beanDefinition.setBean(bean);
		applyPropertyValues(bean, beanDefinition);
		return bean;
	}
	
	protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception
	{
		return beanDefinition.getBeanClass().newInstance();
	}
	
//	protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
	
	protected abstract void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception;
	
	
	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor)
	{
		this.beanPostProcessors.add(beanPostProcessor);
	}
	
	public List getBeansForType(Class type) throws Exception
	{
		List<Object> beans = new ArrayList<Object>();
		for (String beanDefinitionName : this.beanDefinitionNames)
		{
			if (type.isAssignableFrom(this.beanDefinitionMap.get(beanDefinitionName).getBeanClass()))
			{
				beans.add(getBean(beanDefinitionName));
			}
		}
		return beans;
		
	}
	
	
}

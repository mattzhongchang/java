package org.matt.spring.beans.factory.support;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.matt.spring.beans.MutablePropertyValues;
import org.matt.spring.beans.factory.config.BeanDefinition;
import org.springframework.util.Assert;

public class DefaultListableBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegistry{

	private final Log logger = LogFactory.getLog(getClass());
	
	private Map<String, Object> singletonObjects = new HashMap<String, Object>();
	
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();
	
	private final List<String> beanDefinitionNames = new ArrayList<String>(64);
	
	
	// --------------------------------------------------------
	// Implements of BeanDefinitionRegistry interface
	// --------------------------------------------------------
	
	@Override
	public void registerBeanDefinition(String beanName,
			BeanDefinition beanDefinition) {
		Assert.hasText(beanName, "Bean name must not be empty");
		Assert.notNull(beanDefinition, "BeanDefinition must not be null");
		
		synchronized (this.beanDefinitionMap)
		{
			BeanDefinition oldBeanDefinition = this.beanDefinitionMap.get(beanName);
			if (oldBeanDefinition != null)
			{
				logger.info("Cannot register bean definition [" + beanDefinition + "]");
			}
			else
			{
				this.beanDefinitionNames.add(beanName);
			}
			this.beanDefinitionMap.put(beanName, beanDefinition);
		}
		
		
	}

	@Override
	public void removeBeanDefinition(String beanName) {
		Assert.hasText(beanName, "'beanName' must not be empty");
		
		synchronized (this.beanDefinitionMap)
		{
			BeanDefinition bd = this.beanDefinitionMap.remove(beanName);
			if (bd == null)
			{
				this.logger.trace("No bean named '" + beanName + "' found in " + this);
				throw new RuntimeException(beanName);
			}
			this.beanDefinitionNames.remove(beanName);
		}
		
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanName) {
		BeanDefinition bd = this.beanDefinitionMap.get(beanName);
		if (bd == null)
		{
			this.logger.trace("No bean named '" + beanName + "' found in " + this);
			throw new RuntimeException(beanName);
		}
		return bd;
	}

	@Override
	public boolean containsBeanDefinition(String beanName) {
		Assert.notNull(beanName, "Bean name must not be null");
		return this.beanDefinitionMap.containsKey(beanName);
	}

	@Override
	public void registerAlias(String beanName, String aliase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getBean(String name) throws Exception {
		
		
		return doGetBean(name);
	}

	
	protected <T> T doGetBean(String name) throws Exception
	{
		BeanDefinition beanDefinition = this.beanDefinitionMap.get(name);
		if (beanDefinition == null)
		{
			throw new RuntimeException("");
		}
		
//		Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
//		Constructor<?> con = clazz.getDeclaredConstructor();
//		T obj = (T) con.newInstance();
		
		if (beanDefinition instanceof RootBeanDefinition)
		{
			Object obj = createBean(name, (RootBeanDefinition) beanDefinition, null);
			return (T) obj;
		}
		else if (beanDefinition instanceof GenericBeanDefinition)
		{
			Object obj = createBean(name, new RootBeanDefinition(beanDefinition), null);
			return (T) obj;
		}
		
		return null;
	}
	
	public Object getSingleton(String beanName)
	{
		return getSingleton(beanName, true);
	}
	
	protected Object getSingleton(String beanName, boolean allowEarlyReference)
	{
		Object singletonObject = this.singletonObjects.get(beanName);
		if (singletonObject == null)
		{
			
		}
		return singletonObject;
	}
	
	protected void autowireByName(String beanName, RootBeanDefinition bd, MutablePropertyValues pvs)
	{
		
	}
	
	
	public void preInstantiateSingletons()
	{
		List<String> beanNames = new ArrayList<String>(this.beanDefinitionNames);
		
		for (String beanName : beanNames)
		{
			try {
				getBean(beanName);
			} catch (Exception e) {
				logger.error("getBean()“Ï≥££¨beanName=" + beanName, e);
			}
		}
	}
	
	
}

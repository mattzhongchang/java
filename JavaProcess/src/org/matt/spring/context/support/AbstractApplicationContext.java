package org.matt.spring.context.support;

import org.matt.spring.beans.factory.BeanFactory;
import org.matt.spring.beans.factory.config.BeanDefinition;
import org.matt.spring.beans.factory.support.DefaultListableBeanFactory;
import org.matt.spring.beans.factory.xml.XmlBeanDefinitionReader;

public class AbstractApplicationContext {

	private String configLocation;
	
	private DefaultListableBeanFactory beanFactory;
	
	private final Object beanFactoryMonitor = new Object();
	
	public AbstractApplicationContext(String configLocation) {
		this.configLocation = configLocation;
	}

	public void refresh()
	{
		DefaultListableBeanFactory beanFactory = obtainFreshBeanFactory();
		
		finishBeanFactoryInitialization(beanFactory);
		
	}
	
	protected DefaultListableBeanFactory obtainFreshBeanFactory()
	{
		refreshBeanFactory();
		DefaultListableBeanFactory beanFactory = getBeanFactory();
		return beanFactory;
	}
	
	protected void refreshBeanFactory()
	{
		DefaultListableBeanFactory beanFactory = createBeanFactory();
		loadBeanDefinitions(beanFactory);
		synchronized (this.beanFactoryMonitor)
		{
			this.beanFactory = beanFactory;
		}
	}
	
	protected DefaultListableBeanFactory createBeanFactory()
	{
		return new DefaultListableBeanFactory();
	}
	
	public final DefaultListableBeanFactory getBeanFactory()
	{
		synchronized(this.beanFactoryMonitor)
		{
			if (this.beanFactory == null)
			{
				throw new RuntimeException("BeanFactory not initialized or ");
			}
			return this.beanFactory;
		}
	}

	protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory)
	{
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		
		loadBeanDefinitions(reader);
	}
	
	
	protected void loadBeanDefinitions(XmlBeanDefinitionReader reader)
	{
		String configLocation = this.configLocation;
		reader.loadBeanDefinitions(configLocation);
	}
	
	protected Object doCreateBean(String beanName, BeanDefinition mbd, Object[] args)
	{
		
		return null;
	}
	
	
	protected void finishBeanFactoryInitialization(DefaultListableBeanFactory beanFactory)
	{
		beanFactory.preInstantiateSingletons();
	}
	
}

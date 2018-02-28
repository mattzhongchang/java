package com.matt.mattspring.context;

import java.util.Map;

import com.matt.mattspring.bean.BeanDefinition;
import com.matt.mattspring.bean.factory.AbstractBeanFactory;
import com.matt.mattspring.bean.factory.AutowireCapableBeanFactory;
import com.matt.mattspring.bean.io.ResourceLoader;
import com.matt.mattspring.bean.xml.XmlBeanDefinitionReader;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

	private String configLocation;

	public ClassPathXmlApplicationContext(String configLocation) throws Exception
	{
		this(configLocation, new AutowireCapableBeanFactory());
	}

	public ClassPathXmlApplicationContext(String configLocation, 
			AbstractBeanFactory beanFactory) throws Exception {
		super(beanFactory);
		this.configLocation = configLocation;
		refresh();
	}


	@Override
	protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinition(this.configLocation);
		for (Map.Entry<String, BeanDefinition> beanDefinition : xmlBeanDefinitionReader.getRegistry().entrySet())
		{
			beanFactory.registerBeanDefinition(beanDefinition.getKey(), beanDefinition.getValue());
		}
	}

}

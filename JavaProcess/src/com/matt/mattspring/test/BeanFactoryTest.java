package com.matt.mattspring.test;

import java.util.Map;

import org.junit.Test;

import com.matt.mattspring.bean.BeanDefinition;
import com.matt.mattspring.bean.factory.AbstractBeanFactory;
import com.matt.mattspring.bean.factory.AutowireCapableBeanFactory;
import com.matt.mattspring.bean.factory.BeanFactory;
import com.matt.mattspring.bean.io.ResourceLoader;
import com.matt.mattspring.bean.xml.XmlBeanDefinitionReader;

public class BeanFactoryTest {

	@Test
	public void test() throws Exception
	{
		// 1.��ʼ��beanFactory
		AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();

//		// 2.ע��bean
//		BeanDefinition beanDefinition = new BeanDefinition();
//		beanDefinition.setBeanClassName("com.matt.mattspring.test.HelloWorldService");
		
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinition("resource.xml");
		
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet())
		{
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}
		
//		beanFactory.preInstantiateSingletons();
		
		// 5.��ȡbean
		HelloWorldServiceImpl helloWorldService = (HelloWorldServiceImpl) beanFactory.getBean("helloWorldService");
		helloWorldService.helloWorld();
	}
	
	@Test
	public void testLazy() throws Exception
	{
		// 1.��ȡ����
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinition("resource.xml");
		
		// 2.��ʼ��BeanFactory��ע��bean
		AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
		for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet())
		{
			beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
		}
		
		// ��ʼ��bean
	}
}

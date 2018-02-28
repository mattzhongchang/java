package org.matt.spring.beans.factory.support;

import org.matt.spring.beans.factory.config.BeanDefinition;
import org.matt.spring.beans.factory.config.RuntimeBeanReference;
import org.matt.spring.beans.factory.config.TypedStringValue;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.support.ManagedArray;

public class BeanDefinitionValueResolver {

	private AbstractBeanFactory beanFactory;
	
	private String beanName;
	
	private BeanDefinition beanDefinition;
	
	private TypeConverter typeConverter;

	public BeanDefinitionValueResolver(AbstractBeanFactory beanFactory,
			String beanName, BeanDefinition beanDefinition,
			TypeConverter typeConverter) {
		super();
		this.beanFactory = beanFactory;
		this.beanName = beanName;
		this.beanDefinition = beanDefinition;
		this.typeConverter = typeConverter;
	}
	
	public Object resolveValueIfNecessary(Object argName, Object value)
	{
		if (value instanceof RuntimeBeanReference)
		{
			RuntimeBeanReference ref = (RuntimeBeanReference) value;
			return resolveReference(argName, ref);
		}
		else if (value instanceof BeanDefinition)
		{
			BeanDefinition bd = (BeanDefinition) value;
			return null;
		}
		else if (value instanceof ManagedArray)
		{
			return null;
		}
		else if (value instanceof TypedStringValue)
		{
			TypedStringValue typedStringValue = (TypedStringValue) value;
			
			return typedStringValue.getValue();
		}
		
		return value;
	}
	
	
	private Object resolveReference(Object argName, RuntimeBeanReference ref)
	{
		String refName = ref.getBeanName();
		refName = String.valueOf(ref);
		
		Object bean;
		try {
			bean = this.beanFactory.getBean(refName);
			return bean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}

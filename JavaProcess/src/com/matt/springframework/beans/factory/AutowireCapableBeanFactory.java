package com.matt.springframework.beans.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.matt.springframework.BeanReference;
import com.matt.springframework.aop.BeanFactoryAware;
import com.matt.springframework.beans.BeanDefinition;
import com.matt.springframework.beans.PropertyValue;

/**
 * 可自动装配内容的BeanFactory
 * @author Administrator
 *
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory{

	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
		if (bean instanceof BeanFactoryAware)
		{
			((BeanFactoryAware) bean).setBeanFactory(this);
		}
		
		for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValue())
		{
			Object value = propertyValue.getValue();
			if (value instanceof BeanReference)
			{
				BeanReference beanReference = (BeanReference) value;
				value = getBean(beanReference.getName());
			}
			
			try
			{
				Method declaredMethod = bean.getClass().getDeclaredMethod("set" + 
						propertyValue.getName().substring(0, 1) + 
						propertyValue.getName().substring(1), propertyValue.getValue().getClass());
				declaredMethod.setAccessible(true);
				declaredMethod.invoke(bean, value);
			}
			catch (NoSuchMethodException e)
			{
				Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
				declaredField.setAccessible(true);
				declaredField.set(bean, value);
			}
		}
		

	}
}

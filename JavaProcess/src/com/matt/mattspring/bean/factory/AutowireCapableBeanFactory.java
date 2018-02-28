package com.matt.mattspring.bean.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.matt.mattspring.BeanReference;
import com.matt.mattspring.aop.BeanFactoryAware;
import com.matt.mattspring.bean.BeanDefinition;
import com.matt.mattspring.bean.PropertyValue;

public class AutowireCapableBeanFactory extends AbstractBeanFactory{


	
	protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception
	{
		if (bean instanceof BeanFactoryAware)
		{
			((BeanFactoryAware) bean).setBeanFactory(this);
		}
		
		
		for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList())
		{
//			Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
//			declaredField.setAccessible(true);
			Object value = propertyValue.getValue();
			if (value instanceof BeanReference)
			{
				BeanReference beanReference = (BeanReference) value;
				value = getBean(beanReference.getName());
			}
//			declaredField.set(bean, value);
			
			try
			{
				Method declaredMethod = bean.getClass().getDeclaredMethod(
						"set" + propertyValue.getName().substring(0, 1).toUpperCase()
						+ propertyValue.getName().substring(1), value.getClass());
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

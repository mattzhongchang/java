package org.matt.spring.beans.factory.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.matt.spring.beans.factory.BeanFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

public class SimpleInstantiationStrategy implements InstantiationStrategy{
	
	private static final ThreadLocal<Method> currentlyInvokedFactoryMethod = new ThreadLocal<Method>();
	
	public static Method getCurrentlyInvokedFactoryMethod()
	{
		return currentlyInvokedFactoryMethod.get();
	}

	@Override
	public Object instantiate(RootBeanDefinition beanDefinition, String beanName,
			BeanFactory owner) throws Exception {
		
		Constructor<?> constructorToUse = (Constructor<?>) beanDefinition.resolvedConstructorOrFactoryMethod;
		if (constructorToUse == null)
		{
			Class<?> clazz = beanDefinition.getBeanClass();
			constructorToUse = clazz.getDeclaredConstructor((Class[]) null);
			beanDefinition.resolvedConstructorOrFactoryMethod = constructorToUse;
		}
		
		return BeanUtils.instantiateClass(constructorToUse);
	}

	@Override
	public Object instantiate(RootBeanDefinition beanDefinition, String beanName,
			BeanFactory owner, Constructor<?> ctor, Object[] args)
			throws Exception {
		
		return BeanUtils.instantiateClass(ctor, args);
	}

	@Override
	public Object instantiate(RootBeanDefinition beanDefinition, String beanName,
			BeanFactory owner, Object factoryBean, Method factoryMethod,
			Object[] args) throws Exception {
		
		ReflectionUtils.makeAccessible(factoryMethod);
		return factoryMethod.invoke(factoryBean, args);
	}
	

}

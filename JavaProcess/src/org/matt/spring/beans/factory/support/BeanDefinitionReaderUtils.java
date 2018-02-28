package org.matt.spring.beans.factory.support;

import org.matt.spring.beans.MutablePropertyValues;
import org.matt.spring.beans.factory.xml.BeanDefinitionHolder;
import org.springframework.util.ClassUtils;

public class BeanDefinitionReaderUtils {

	
	public static AbstractBeanDefinition createBeanDefinition(String parentName, 
			String className, ClassLoader classLoader) throws ClassNotFoundException
	{
		GenericBeanDefinition bd = new GenericBeanDefinition();
		bd.setPropertyValues(new MutablePropertyValues());
	    bd.setParentName(parentName);
	    if (className != null)
	    {
	    	if (classLoader != null)
	    	{
	    		bd.setBeanClass(ClassUtils.forName(className, classLoader));
	    	}
	    	else
	    	{
	    		bd.setBeanClassName(className);
	    	}
	    }
		return bd;
	}
	
	public static void registerBeanDefinition(BeanDefinitionHolder definitionHolder, BeanDefinitionRegistry registry)
	{
		String beanName = definitionHolder.getBeanName();
		registry.registerBeanDefinition(beanName, definitionHolder.getBeanDefinition());
		
		String[] aliases = definitionHolder.getAliases();
		if (aliases != null)
		{
			for (String aliase : aliases)
			{
				registry.registerAlias(beanName, aliase);
			}
		}
		
	}
}

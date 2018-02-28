package org.matt.spring.beans.factory.support;

import org.matt.spring.beans.factory.config.BeanDefinition;

public class RootBeanDefinition extends AbstractBeanDefinition{

	/** Package-visible field for caching the resolved constructor or factory method */
	Object resolvedConstructorOrFactoryMethod;
	
	
	
	RootBeanDefinition(BeanDefinition original)
	{
		super(original);
	}
}

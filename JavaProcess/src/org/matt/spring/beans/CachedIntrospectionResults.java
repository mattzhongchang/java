package org.matt.spring.beans;

import java.beans.BeanInfo;

public class CachedIntrospectionResults {

	public static final String IGNORE_BEANINFO_PROPERTY_NAME = "spring.beaninfo.ignore";
	
//	private static final boolean shouldIntrospectorIgnoreBeaninfoClasses = 
	
	/** The BeanInfo object for the introspected bean class */
	private BeanInfo beanInfo;
	
	
	BeanInfo getBeanInfo()
	{
		return this.beanInfo;
	}
	
	Class<?> getBeanClass() 
	{
		return this.beanInfo.getBeanDescriptor().getBeanClass();
	}
}

package org.matt.spring.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TypeConverterDelegate {

	private static final Log logger = LogFactory.getLog(TypeConverterDelegate.class);
	
	private PropertyEditorRegistrySupport propertyEditorRegistry;
	
	private Object targetObject;
	
	
	public TypeConverterDelegate(PropertyEditorRegistrySupport propertyEditorRegistry, Object targetObject)
	{
		this.propertyEditorRegistry = propertyEditorRegistry;
		this.targetObject = targetObject;
	}
	
	public <T> T convertIfNecessary(String propertyName, Object oldValue, Object newValue, Class<T> requiredType)
	{
		Object convertedValue = newValue;
		
		return (T) convertedValue.toString();
	}
}

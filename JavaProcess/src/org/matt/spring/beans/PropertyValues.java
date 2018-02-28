package org.matt.spring.beans;

public interface PropertyValues {

	
	PropertyValue[] getPropertyValues();
	
	PropertyValue getPropertyValue(String propertyName);
	
	boolean contains(String propertyName);
}

package org.matt.spring.beans;

import java.beans.PropertyDescriptor;

public class PropertyValue {

	private String name;
	
	private Object value;
	
	private boolean converted = false;
	
	private Object convertedValue;
	
	
	/** package-visible field for caching the resolved property path tokens */
	volatile Object resolvedTokens;
	
	volatile PropertyDescriptor resolvedDescriptor;
	

	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}

	public synchronized boolean isConverted() {
		return converted;
	}
	

	public synchronized Object getConvertedValue() {
		return convertedValue;
	}

	public synchronized void setConvertedValue(Object convertedValue) {
		this.converted = true;
		this.convertedValue = convertedValue;
	}


	public PropertyValue(PropertyValue original, Object newValue)
	{
		this.name = original.getName();
		this.value = newValue;
		this.resolvedTokens = original.resolvedTokens;
		this.resolvedDescriptor = original.resolvedDescriptor;
		
	}
	
	
	
}

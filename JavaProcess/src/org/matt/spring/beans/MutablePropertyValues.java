package org.matt.spring.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MutablePropertyValues implements PropertyValues{

	private List<PropertyValue> propertyValueList;
	
	private Set<String> processedProperties;
	
	private boolean converted = true;

	public MutablePropertyValues() {
		this.propertyValueList = new ArrayList<PropertyValue>();
	}
	
	public MutablePropertyValues(List<PropertyValue> propertyValueList)
	{
		this.propertyValueList = 
			(propertyValueList != null ? propertyValueList : new ArrayList<PropertyValue>());
	}
	

	public List<PropertyValue> getPropertyValueList() {
		return propertyValueList;
	}

	public void setPropertyValueList(List<PropertyValue> propertyValueList) {
		this.propertyValueList = propertyValueList;
	}

	public Set<String> getProcessedProperties() {
		return processedProperties;
	}

	public void setProcessedProperties(Set<String> processedProperties) {
		this.processedProperties = processedProperties;
	}
	

	public boolean isConverted() {
		return converted;
	}

	public void setConverted() {
		this.converted = true;
	}

	@Override
	public PropertyValue[] getPropertyValues() {
		return this.propertyValueList.toArray(new PropertyValue[this.propertyValueList.size()]);
	}

	@Override
	public PropertyValue getPropertyValue(String propertyName) {
		for (PropertyValue pv : this.propertyValueList)
		{
			if (pv.getName().equals(propertyName))
			{
				return pv;
			}
		}
		return null;
	}

	@Override
	public boolean contains(String propertyName) {
		
		return (getPropertyValue(propertyName) != null || 
				(this.processedProperties != null && this.processedProperties.contains(propertyName)));
	}
	
	
	public MutablePropertyValues addPropertyValue(PropertyValue pv)
	{
		for (int i=0; i<this.propertyValueList.size(); i++)
		{
			PropertyValue currentPv = this.propertyValueList.get(i);
			if (currentPv.getName().equals(pv.getName()))
			{
				this.propertyValueList.set(i, pv);
				return this;
			}
		}
		this.propertyValueList.add(pv);
		return this;
	}
	
	
}

package com.matt.mattspring.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 包装一个对象的所有PropertyValue
 * @author Administrator
 *
 */
public class PropertyValues {

	private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

	public PropertyValues() {
	}
	
	public void addPropertyValue(PropertyValue pv)
	{
		// TODO:这里可以对重复的propertyName进行判断
		this.propertyValueList.add(pv);
	}

	public List<PropertyValue> getPropertyValueList() {
		return propertyValueList;
	}
	
	
	
	
}

package com.matt.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 包装一个对象的所有propertyValue值
 * @author Administrator
 *
 */
public class PropertyValues {
	
	private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

	public PropertyValues() {
	}
	
	public void addPropertyValue(PropertyValue pv)
	{
		//TODO:这里可以对于重复propertyName进行判断，直接用list没法做到
		this.propertyValueList.add(pv);
	}
	
	public List<PropertyValue> getPropertyValue()
	{
		return this.propertyValueList;
	}

	

}

package com.matt.mattspring.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * ��װһ�����������PropertyValue
 * @author Administrator
 *
 */
public class PropertyValues {

	private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

	public PropertyValues() {
	}
	
	public void addPropertyValue(PropertyValue pv)
	{
		// TODO:������Զ��ظ���propertyName�����ж�
		this.propertyValueList.add(pv);
	}

	public List<PropertyValue> getPropertyValueList() {
		return propertyValueList;
	}
	
	
	
	
}

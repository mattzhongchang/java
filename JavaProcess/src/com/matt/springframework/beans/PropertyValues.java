package com.matt.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * ��װһ�����������propertyValueֵ
 * @author Administrator
 *
 */
public class PropertyValues {
	
	private final List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

	public PropertyValues() {
	}
	
	public void addPropertyValue(PropertyValue pv)
	{
		//TODO:������Զ����ظ�propertyName�����жϣ�ֱ����listû������
		this.propertyValueList.add(pv);
	}
	
	public List<PropertyValue> getPropertyValue()
	{
		return this.propertyValueList;
	}

	

}

package com.matt.springframework.beans;

/**
 * �û�Bean������ע��
 * @author Administrator
 *
 */
public class PropertyValue {

	private String name;
	
	private Object value;

	public PropertyValue(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
	
}

package com.matt.springframework.beans;

/**
 * 用户Bean的属性注入
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

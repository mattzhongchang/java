package com.matt.mattspring.bean;


public class BeanDefinition {

	private Object bean;
	
	private Class beanClass;
	
	private String beanClassName;
	
	private PropertyValues propertyValues = new PropertyValues();

	public BeanDefinition() {
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}
	
	public Object getBean() {
		return bean;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
		try {
			this.beanClass = Class.forName(beanClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}
	
	
	
	
}

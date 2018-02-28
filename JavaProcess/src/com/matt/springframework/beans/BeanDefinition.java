package com.matt.springframework.beans;

/**
 * Bean的内容及元数据，保存在BeanFactory中
 * @author Administrator
 *
 */
public class BeanDefinition {
	
	private Object bean;
	
	private Class beanClass;
	
	private String className;
	
	private PropertyValues propertyValues = new PropertyValues();

	public BeanDefinition() {
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
		
		try {
			this.beanClass = Class.forName(className);
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

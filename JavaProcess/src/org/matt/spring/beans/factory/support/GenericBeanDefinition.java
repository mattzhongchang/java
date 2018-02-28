package org.matt.spring.beans.factory.support;


public class GenericBeanDefinition extends AbstractBeanDefinition{
	
	private String parentName;

	public GenericBeanDefinition() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


	
	
	

}

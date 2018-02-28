package org.matt.spring.beans.factory.xml;

import org.matt.spring.beans.factory.config.BeanDefinition;
import org.springframework.util.Assert;

public class BeanDefinitionHolder {

	private BeanDefinition beanDefinition;
	
	private String beanName;
	
	private String[] aliases;
	
	public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName) {
		this(beanDefinition, beanName, null);
	}

	public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName,
			String[] aliases) {
	    Assert.notNull(beanDefinition, "BeanDefinition must not be null");
	    Assert.notNull(beanName, "Bean name must not be null");
		this.beanDefinition = beanDefinition;
		this.beanName = beanName;
		this.aliases = aliases;
	}

	public BeanDefinition getBeanDefinition() {
		return beanDefinition;
	}

	public void setBeanDefinition(BeanDefinition beanDefinition) {
		this.beanDefinition = beanDefinition;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String[] getAliases() {
		return aliases;
	}

	public void setAliases(String[] aliases) {
		this.aliases = aliases;
	}
	
	
	
}

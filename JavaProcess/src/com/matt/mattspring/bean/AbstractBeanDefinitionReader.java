package com.matt.mattspring.bean;

import java.util.HashMap;
import java.util.Map;

import com.matt.mattspring.bean.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

	private Map<String, BeanDefinition> registry;
	
	private ResourceLoader resourceLoader;

	public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
		this.registry = new HashMap<String, BeanDefinition>();
		this.resourceLoader = resourceLoader;
	}

	public Map<String, BeanDefinition> getRegistry() {
		return registry;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	
	
	
	
}

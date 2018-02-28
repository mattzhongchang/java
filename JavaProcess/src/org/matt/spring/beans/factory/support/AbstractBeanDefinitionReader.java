package org.matt.spring.beans.factory.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.matt.spring.core.io.DefaultResourceLoader;
import org.matt.spring.core.io.Resource;
import org.matt.spring.core.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

	protected final Log logger = LogFactory.getLog(getClass());
	
	private BeanDefinitionRegistry registry;
	
	private ResourceLoader resourceLoader;

	public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
		super();
		this.registry = registry;
		this.resourceLoader = new DefaultResourceLoader();
	}
	
	
	public final BeanDefinitionRegistry getBeanFactory()
	{
		return this.registry;
	}
	
	public final BeanDefinitionRegistry getRegistry()
	{
		return this.registry;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}


	@Override
	public int loadBeanDefinitions(String location) {
		ResourceLoader resourceLoader = this.resourceLoader;
		Resource resource = resourceLoader.getResource(location);
		int loadCount = loadBeanDefinitions(resource);
		return loadCount;
	}
	

	public abstract int loadBeanDefinitions(Resource resource);
	
	
}

package com.matt.mattspring.context;

import java.util.List;

import com.matt.mattspring.bean.BeanPostProcessor;
import com.matt.mattspring.bean.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext{

	protected AbstractBeanFactory beanFactory;
	
	public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public void refresh() throws Exception
	{
		loadBeanDefinitions(this.beanFactory);
		registerBeanPostProcessors(this.beanFactory);
		onRefresh();
	}
	
	protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;
	
	protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception
	{
		List beanPostProcessors = this.beanFactory.getBeansForType(BeanPostProcessor.class);
		for (Object beanPostProcessor : beanPostProcessors)
		{
			beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
		}
		
	}
	
	protected void onRefresh() throws Exception
	{
		this.beanFactory.preInstantiateSingletons();
	}
	
	@Override
	public Object getBean(String name) throws Exception {
		return this.beanFactory.getBean(name);
	}



}

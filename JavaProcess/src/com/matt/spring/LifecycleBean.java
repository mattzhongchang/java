package com.matt.spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class LifecycleBean implements InitializingBean, DisposableBean
{
	
	private String lifecycleBeanName;
	
	
	
//	public void setLifeCycleBeanName(String lifeCycleBeanName)
//	{
//		System.out.println("Enter LifecycleBean.setLifeCycleBeanName(), lifeCycleBeanName = "  + lifeCycleBeanName);
//		this.lifecycleBeanName = lifecycleBeanName;
//	}

	public void setLifecycleBeanName(String lifecycleBeanName) {
		System.out.println("Enter LifecycleBean.setLifeCycleBeanName(), lifeCycleBeanName = "  + lifecycleBeanName);
		this.lifecycleBeanName = lifecycleBeanName;
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("Enter LifecycleBean.destroy()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Enter LifecycleBean.afterPropertiesSet()");
	}
	
	public void beanStart()
	{
		System.out.println("Enter LifecycleBean.beanStart()");
	}
	
	public void beanEnd()
	{
		System.out.println("Enter LifecycleBean.beanEnd()");
	}

	
}

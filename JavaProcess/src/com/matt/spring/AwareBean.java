package com.matt.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ApplicationContextAware类，setApplicationContext方法在spring初始化时会执行setApplicationContext方法
 * @author Administrator
 *
 */
public class AwareBean implements BeanNameAware, ApplicationContextAware, BeanFactoryAware{
	private BeanFactory                beanFactory;
    
    private ApplicationContext        applicationContext;
    
    private String                     beanName;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("Enter AwareBean.setBeanFactory(), beanfactory = " + beanFactory + "\n");
        this.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		System.out.println("Enter AwareBean.setApplicationContext(), applicationContext = " + applicationContext + "\n");
        this.applicationContext = applicationContext;
		
	}

	@Override
	public void setBeanName(String beanName) {
		System.out.println("Enter AwareBean.setBeanName(), beanName = " + beanName + "\n");
        this.beanName = beanName;
	}

}

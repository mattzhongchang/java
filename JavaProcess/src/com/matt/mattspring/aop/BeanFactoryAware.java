package com.matt.mattspring.aop;

import com.matt.mattspring.bean.factory.BeanFactory;

public interface BeanFactoryAware {

	void setBeanFactory(BeanFactory beanFactory) throws Exception;
}

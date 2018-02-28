package com.matt.springframework.aop;

import com.matt.springframework.beans.factory.BeanFactory;

public interface BeanFactoryAware {

	void setBeanFactory(BeanFactory beanFactory) throws Exception;
}

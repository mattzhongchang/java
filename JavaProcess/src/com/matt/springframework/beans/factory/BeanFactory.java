package com.matt.springframework.beans.factory;

/**
 * BeanµÄÈÝÆ÷
 * @author Administrator
 *
 */
public interface BeanFactory {

	Object getBean(String name) throws Exception;
}

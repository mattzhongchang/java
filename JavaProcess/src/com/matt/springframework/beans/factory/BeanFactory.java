package com.matt.springframework.beans.factory;

/**
 * Bean������
 * @author Administrator
 *
 */
public interface BeanFactory {

	Object getBean(String name) throws Exception;
}

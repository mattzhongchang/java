package org.matt.spring.beans.factory;

public interface FactoryBean<T> {
	
	T getObject() throws Exception;
	
	

}

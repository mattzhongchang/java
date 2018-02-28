package org.matt.spring.beans.factory.config;

import org.matt.spring.beans.MutablePropertyValues;



public interface BeanDefinition {

	String SCOPE_SINGLETON = "singleton";
	
	String SCOPE_PROTOTYPE = "prototype";

	
	/**
	 * Return the name of the parent definition of this bean definition, if any.
	 * @return
	 */
	String getParentName();
	
	void setParentName(String parentName);
	
	String getBeanClassName();
	
	void setBeanClassName(String beanClassName);
	
	String getFactoryBeanName();
	
	String getFactoryMethodName();
	
	String getScope();
	
	void setScope(String scope);
	
	boolean isLazyInit();
	
	void setLazyInit(boolean lazyInit);
	
	ConstructorArgumentValues getConstructorArgumentValues();
	
	MutablePropertyValues getPropertyValues();
	
	boolean isSingleton();
	
	boolean isPrototype();
	
	boolean isAbstract();
	
	String getDescription();
	
	
}

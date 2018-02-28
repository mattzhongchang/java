package com.matt.mattspring.aop;

public class TargetSource {

	private Object target;

	private Class<?> targetClass;
	
	private Class<?>[] interfaces;

	public TargetSource(Object target, Class<?> targetClass, Class<?>... interfaces) {
		super();
		this.targetClass = targetClass;
		this.interfaces = interfaces;
		this.target = target;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public Object getTarget() {
		return target;
	}

	public Class<?>[] getInterfaces() {
		return interfaces;
	}
	

	
}

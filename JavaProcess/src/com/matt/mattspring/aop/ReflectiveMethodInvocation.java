package com.matt.mattspring.aop;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

public class ReflectiveMethodInvocation implements MethodInvocation{

	protected Object target;
	
	protected Method method;
	
	protected Object[] args;
	
	public ReflectiveMethodInvocation(Object target, Method method,
			Object[] args) {
		super();
		this.target = target;
		this.method = method;
		this.args = args;
	}

	@Override
	public AccessibleObject getStaticPart() {
		return this.method;
	}

	@Override
	public Object getThis() {
		return this.target;
	}

	@Override
	public Object proceed() throws Throwable {
		return this.method.invoke(this.target, this.args);
	}

	@Override
	public Object[] getArguments() {
		return this.args;
	}

	@Override
	public Method getMethod() {
		return this.method;
	}
	
	

}

package com.matt.mattspring.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 基于jdk的动态代理
 * @author Administrator
 *
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler{
	
	public JdkDynamicAopProxy(AdvisedSupport advised) {
		super(advised);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		MethodInterceptor methodInterceptor = this.advised.getMethodInterceptor();
		if (this.advised.getMethodMatcher() != null && 
				this.advised.getMethodMatcher().matches(method, this.advised.getTargetSource().getTarget().getClass()))
		{
			return methodInterceptor.invoke(new ReflectiveMethodInvocation(this.advised.getTargetSource().getTarget(), method, args));
		}
		else
		{
			return method.invoke(this.advised.getTargetSource().getTarget(), args);
		}
	}

	@Override
	public Object getProxy() {
		return Proxy.newProxyInstance(getClass().getClassLoader(), 
				this.advised.getTargetSource().getInterfaces(), 
				this);
	}
}

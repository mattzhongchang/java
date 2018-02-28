package com.matt.mattspring.aop;


import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;



public class Cglib2AopProxy extends AbstractAopProxy{

	public Cglib2AopProxy(AdvisedSupport advised)
	{
		super(advised);
	}
	
	@Override
	public Object getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.advised.getTargetSource().getTargetClass());
		enhancer.setInterfaces(this.advised.getTargetSource().getInterfaces());
		enhancer.setCallback(new DynamicAdvisedInterceptor(this.advised));
		Object enhanced = enhancer.create();
		return enhanced;
	}

	
	private static class DynamicAdvisedInterceptor implements MethodInterceptor
	{
		private AdvisedSupport advised;
		
		private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;
		
		public DynamicAdvisedInterceptor(AdvisedSupport advised) {
			super();
			this.advised = advised;
			this.delegateMethodInterceptor = this.advised.getMethodInterceptor();
		}

		@Override
		public Object intercept(Object obj, Method method, Object[] args,
				MethodProxy proxy) throws Throwable {
			if (this.advised.getMethodMatcher() == null || 
					this.advised.getMethodMatcher().matches(method, this.advised.getTargetSource().getTargetClass()))
			{
				this.delegateMethodInterceptor.invoke(new CglibMethodInvocation(
						this.advised.getTargetSource().getTarget(), method, args, proxy));
			}
			
			return new CglibMethodInvocation(this.advised.getTargetSource().getTarget(), method, args, proxy);
		}
	}

	private static class CglibMethodInvocation extends ReflectiveMethodInvocation
	{
		private final MethodProxy methodProxy;

		public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
			super(target, method, args);
			this.methodProxy = methodProxy;
		}

		@Override
		public Object proceed() throws Throwable {
			return this.methodProxy.invoke(this.target, this.args);
		}
		
		
		
	}
	
}

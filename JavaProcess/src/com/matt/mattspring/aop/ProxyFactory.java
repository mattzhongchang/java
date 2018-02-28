package com.matt.mattspring.aop;

public class ProxyFactory extends AdvisedSupport implements AopProxy{

	@Override
	public Object getProxy() {
		return createAopProxy().getProxy();
	}

	protected final AopProxy createAopProxy()
	{
		return new Cglib2AopProxy(this);
	}
}

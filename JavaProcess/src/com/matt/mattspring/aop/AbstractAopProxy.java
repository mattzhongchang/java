package com.matt.mattspring.aop;

public abstract class AbstractAopProxy implements AopProxy{

	protected AdvisedSupport advised;

	public AbstractAopProxy(AdvisedSupport advised)
	{
		this.advised = advised;
	}

	
}

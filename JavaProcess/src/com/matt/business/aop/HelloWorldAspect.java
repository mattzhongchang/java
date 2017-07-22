package com.matt.business.aop;

public class HelloWorldAspect {

	public void beforeAdvice()
	{
		System.out.println(" --before advice-- ");
	}
	
	public void afterAdvice()
	{
		System.out.println(" --after advice-- ");
	}
	
	public void afterFinallyAdvice()
	{
		System.out.println(" --after finally advice-- ");
	}
}

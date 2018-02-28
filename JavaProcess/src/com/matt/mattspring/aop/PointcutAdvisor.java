package com.matt.mattspring.aop;

public interface PointcutAdvisor extends Advisor{

	Pointcut getPointcut();
}

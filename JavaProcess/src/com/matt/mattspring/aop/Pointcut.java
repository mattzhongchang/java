package com.matt.mattspring.aop;

public interface Pointcut {

	ClassFilter getClassFilter();
	
	MethodMatcher getMethodMatcher();
}

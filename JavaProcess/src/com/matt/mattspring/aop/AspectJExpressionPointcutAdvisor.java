package com.matt.mattspring.aop;

import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor{

	private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	
	private Advice advice;
	

	public void setExpression(String expression)
	{
		this.pointcut.setExpression(expression);
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

	@Override
	public Advice getAdvice() {
		return this.advice;
	}

	@Override
	public Pointcut getPointcut() {
		return this.pointcut;
	}

	
}

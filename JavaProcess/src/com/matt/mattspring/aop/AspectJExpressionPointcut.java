package com.matt.mattspring.aop;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher{

	private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

	static {
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
		DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
	}
	
	private PointcutParser pointcutParser;
	
	private String expression;
	
	private PointcutExpression pointcutExpression;
	

	public AspectJExpressionPointcut()
	{
		this(DEFAULT_SUPPORTED_PRIMITIVES);
	}
	
	public AspectJExpressionPointcut(Set<PointcutPrimitive> supportedPrimitives)
	{
		this.pointcutParser = PointcutParser.
		getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(DEFAULT_SUPPORTED_PRIMITIVES);
	}
	
	protected void checkReadyToMatch() {
		if (pointcutExpression == null) {
			pointcutExpression = buildPointcutExpression();
		}
	}
	
	private PointcutExpression buildPointcutExpression() {
		return pointcutParser.parsePointcutExpression(expression);
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public boolean matches(Method method, Class targetClass) {
		checkReadyToMatch();
		ShadowMatch shadowMatch = this.pointcutExpression.matchesMethodExecution(method);
		if (shadowMatch.alwaysMatches())
		{
			return true;
		}
		else if (shadowMatch.neverMatches())
		{
			return false;
		}
		// TODO:其他情况不判断了
		return false;
	}

	@Override
	public boolean matches(Class targetClass) {
		checkReadyToMatch();
		return this.pointcutExpression.couldMatchJoinPointsInType(targetClass);
	}

	@Override
	public ClassFilter getClassFilter() {
		return this;
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return this;
	}
	
	

}

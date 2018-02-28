package test.aop;

import org.junit.Assert;
import org.junit.Test;

import com.matt.business.biz.HelloWorldService;
import com.matt.business.biz.HelloWorldServiceImpl;
import com.matt.mattspring.aop.AspectJExpressionPointcut;

public class AspectJExpressionPointcutTest {

	@Test
	public void testClassFilter() throws Exception 
	{
		String expression = "execution(* com.matt.business.biz.*.*(..))";
		AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
		aspectJExpressionPointcut.setExpression(expression);
		boolean matches = aspectJExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
		Assert.assertTrue(matches);
	}
	
	@Test
	public void testMethodinterceptor() throws Exception
	{
		String expression = "execution(* com.matt.business.biz.*.*(..))";
		AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
		aspectJExpressionPointcut.setExpression(expression);
		boolean matches = aspectJExpressionPointcut.getMethodMatcher().matches(
				HelloWorldServiceImpl.class.getDeclaredMethod("sayHello"), HelloWorldServiceImpl.class);
		Assert.assertTrue(matches);
	}
}

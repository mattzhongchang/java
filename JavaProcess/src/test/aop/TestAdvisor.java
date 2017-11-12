package test.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

public class TestAdvisor implements PointcutAdvisor{

	
	@Override
	public Advice getAdvice() {
		return new TestAfterAdvice();
	}

	@Override
	public boolean isPerInstance() {
		return false;
	}

	@Override
	public Pointcut getPointcut() {
		return new TestPointcut();
	}

}

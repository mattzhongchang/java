package test.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class TestAfterAdvice implements AfterReturningAdvice{

	@Override
	public void afterReturning(Object returnValues, Method method, Object[] arg2,
			Object target) throws Throwable {
		System.out.println("after " + target.getClass().getSimpleName() + "." + method.getName() + "()");
	}

}

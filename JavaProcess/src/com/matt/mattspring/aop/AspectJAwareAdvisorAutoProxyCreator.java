package com.matt.mattspring.aop;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

import com.matt.mattspring.bean.BeanPostProcessor;
import com.matt.mattspring.bean.factory.AbstractBeanFactory;
import com.matt.mattspring.bean.factory.BeanFactory;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware{

	private AbstractBeanFactory beanFactory;
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws Exception {
		this.beanFactory = (AbstractBeanFactory) beanFactory;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws Exception {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws Exception {
		if (bean instanceof AspectJExpressionPointcutAdvisor)
		{
			return bean;
		}
		if (bean instanceof MethodInterceptor)
		{
			return bean;
		}
		List<AspectJExpressionPointcutAdvisor> advisors = 
			this.beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
		for (AspectJExpressionPointcutAdvisor advisor : advisors)
		{
			if (advisor.getPointcut().getClassFilter().matches(bean.getClass()))
			{
				ProxyFactory advisedSupport = new ProxyFactory();
				advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
				advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
				
				TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
				advisedSupport.setTargetSource(targetSource);
				
				return new JdkDynamicAopProxy(advisedSupport).getProxy();
			}
		}
		
		return bean;
	}

	
}

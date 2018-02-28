package com.matt.mattspring.test.aop;

import org.junit.Test;

import com.matt.business.biz.HelloWorldServiceImpl;
import com.matt.mattspring.aop.AdvisedSupport;
import com.matt.mattspring.aop.Cglib2AopProxy;
import com.matt.mattspring.aop.TargetSource;
import com.matt.mattspring.context.ApplicationContext;
import com.matt.mattspring.context.ClassPathXmlApplicationContext;
import com.matt.mattspring.test.HelloWorldService;

public class Cglib2AopProxyTest {

	@Test
	public void testInterceptor() throws Exception
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resource.xml");
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		helloWorldService.helloWorld();
		
		// 1.���ñ��������(Joinpoint)
		AdvisedSupport advisedSupport = new AdvisedSupport();
		TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class, HelloWorldService.class);
		advisedSupport.setTargetSource(targetSource);
		
		// 2.����������(Advice)
		TimerInterceptor timerTnterceptor = new TimerInterceptor();
		advisedSupport.setMethodInterceptor(timerTnterceptor);
		
		// 3.��������(Proxy)
		Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
		HelloWorldService helloWorldServiceProxy = (HelloWorldService) cglib2AopProxy.getProxy();
		
		// 4.����AOP�ĵ���
		helloWorldServiceProxy.helloWorld();
		
	}
}

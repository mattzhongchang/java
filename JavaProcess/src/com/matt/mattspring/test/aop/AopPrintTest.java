package com.matt.mattspring.test.aop;

import com.matt.mattspring.aop.AdvisedSupport;
import com.matt.mattspring.aop.JdkDynamicAopProxy;
import com.matt.mattspring.aop.TargetSource;
import com.matt.mattspring.context.ApplicationContext;
import com.matt.mattspring.context.ClassPathXmlApplicationContext;
import com.matt.mattspring.test.HelloWorldService;
import com.matt.mattspring.test.HelloWorldServiceImpl;

public class AopPrintTest {

	public static void main(String[] args) throws Exception {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		
		// -------- helloWorldService without AOP
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resource.xml");
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		helloWorldService.helloWorld();
		
		// -------- helloWorldService with AOP
		// 0.Advisor
		AdvisedSupport advisedSupport = new AdvisedSupport();
		// 1.���ñ��������(Joinpoint)
		TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class, 
				new Class[]{HelloWorldService.class});
		advisedSupport.setTargetSource(targetSource);
		
		// 2.����������(Advice)
		TimerInterceptor timerInterceptor = new TimerInterceptor();
		advisedSupport.setMethodInterceptor(timerInterceptor);
		
		// 3.��������(Proxy)
		JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
		HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();
		
		// 4.����AOP�ĵ���
		helloWorldServiceProxy.helloWorld();
	}
}

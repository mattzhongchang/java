package test.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestAOP {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:test.xml");
		TestTarget target = (TestTarget) applicationContext.getBean("proxyFactoryBean");
		target.test();
		System.out.println("------�޵зָ���-----");
        target.test2();
	}
}

package test.aop.proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class App {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:conf/test.xml");
		MyTarget target = (MyTarget) applicationContext.getBean("myTarget");
		target.hello();
		System.out.println("------end-----");
	}
}

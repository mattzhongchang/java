package test.aop.aopconfig;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.matt.business.biz.HelloWorldService;

public class AopConfigClient {

	private final static String SPRING_PATH = "config/aop-config.xml";
	
	public static void main(String[] args) throws Exception
	{
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(SPRING_PATH);
		HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");
		helloWorldService.sayHello();
	}
}

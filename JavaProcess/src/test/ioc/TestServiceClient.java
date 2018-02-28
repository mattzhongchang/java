package test.ioc;

import org.springframework.context.support.FileSystemXmlApplicationContext;


public class TestServiceClient {

	private final static String SPRING_PATH = "config/springioc.xml";
	
	public static void main(String[] args) throws Exception
	{
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(SPRING_PATH);
		TestService testService = (TestService) applicationContext.getBean("testService");
		testService.test();
		
	}
}

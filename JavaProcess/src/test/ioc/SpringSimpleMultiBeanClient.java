package test.ioc;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringSimpleMultiBeanClient {

	private final static String SPRING_PATH = "config/spring-init.xml";
	
	public static void main(String[] args) throws Exception
	{
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(SPRING_PATH);
		SpringSimpleMultiBean springSimpleMultiBean = (SpringSimpleMultiBean) applicationContext.getBean("springSimpleMultiBean");
		springSimpleMultiBean.say();
		
	}
	
	private void test()
	{
		SpringSimpleMultiBeanClient client = SpringSimpleMultiBeanClient.this;
	}
}

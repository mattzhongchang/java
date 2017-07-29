package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matt.spring.factorybean.Animal;
import com.matt.spring.factorybean.AnimalFactoryBean;

public class AnimalFactoryBeanTest {
    
	private static final String[] spring_config = new String[]{"ApplicationContext.xml", "yyadb.xml", "srpdb.xml"};
	
	@Test
	public void testFactoryBean()
	{
        ApplicationContext ctx = null;
		ctx = new ClassPathXmlApplicationContext(spring_config);
        
		AnimalFactoryBean animalFactoryBean = (AnimalFactoryBean) ctx.getBean("animalFactoryBean");
		Animal animal = null;
		try {
			animal = (Animal) animalFactoryBean.getObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		animal.move();
	}
}

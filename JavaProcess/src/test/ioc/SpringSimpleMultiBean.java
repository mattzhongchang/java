package test.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class SpringSimpleMultiBean implements BeanFactoryAware, BeanNameAware, BeanFactoryPostProcessor, BeanPostProcessor, InitializingBean{

	private Integer id;
	
	private String name;
	

	public SpringSimpleMultiBean() {
		System.out.println("构造函数 SpringSimpleMultiBean()");  
	}
	
	public void initMethod()
	{
		System.out.println("init method Begin");  
	}
	
	public void say()
	{
		System.out.println("hello I am "+name);  
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	/**
	 * InitializingBean接口的具体实现 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean接口的具体实现 begin and id is " + this.id + " and name is " + this.name);  
	}

	/**
	 * BeanPostProcessor接口 before 初始化 
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("BeanPostProcessor接口 before 初始化  postProcessAfterInitialization begin");  
        return bean;  
	}

	/**
	 * BeanPostProcessor接口after 初始化 
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("BeanPostProcessor接口after 初始化  postProcessAfterInitialization begin");  
        return bean;  
	}

	/**
	 * BeanFactoryPostProcessor 接口初始化 
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryPostProcessor 接口初始化 begin and this beanFactory is "+beanFactory);  
	}

	/**
	 * BeanNameAware初始化 
	 */
	@Override
	public void setBeanName(String name) {
		System.out.println("BeanNameAware初始化 set BeanName begin and name is "+name);  
	}

	/**
	 * BeanFactoryName初始化 
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryName初始化 begin and beanFactory is "+beanFactory);
	}

}

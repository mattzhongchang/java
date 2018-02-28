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
		System.out.println("���캯�� SpringSimpleMultiBean()");  
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
	 * InitializingBean�ӿڵľ���ʵ�� 
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean�ӿڵľ���ʵ�� begin and id is " + this.id + " and name is " + this.name);  
	}

	/**
	 * BeanPostProcessor�ӿ� before ��ʼ�� 
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("BeanPostProcessor�ӿ� before ��ʼ��  postProcessAfterInitialization begin");  
        return bean;  
	}

	/**
	 * BeanPostProcessor�ӿ�after ��ʼ�� 
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		System.out.println("BeanPostProcessor�ӿ�after ��ʼ��  postProcessAfterInitialization begin");  
        return bean;  
	}

	/**
	 * BeanFactoryPostProcessor �ӿڳ�ʼ�� 
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryPostProcessor �ӿڳ�ʼ�� begin and this beanFactory is "+beanFactory);  
	}

	/**
	 * BeanNameAware��ʼ�� 
	 */
	@Override
	public void setBeanName(String name) {
		System.out.println("BeanNameAware��ʼ�� set BeanName begin and name is "+name);  
	}

	/**
	 * BeanFactoryName��ʼ�� 
	 */
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("BeanFactoryName��ʼ�� begin and beanFactory is "+beanFactory);
	}

}

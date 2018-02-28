package org.matt.spring.context.support;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext{

	public ClassPathXmlApplicationContext(String configLocation)
	{
		super(configLocation);
		refresh();
	}
}

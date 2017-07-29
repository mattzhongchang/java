package com.matt.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class AnimalFactoryBean implements FactoryBean<Animal> {
    
	private String animal;

	public void setAnimal(String animal) {
		System.out.println("Enter AnimalFactoryBean.setAnimal(), animal = " + animal);
		this.animal = animal;
	}

	@Override
	public Animal getObject() throws Exception {
		if ("Monkey".equals(this.animal))
		{
			return new Monkey();
		}
		else if ("Tiger".equals(this.animal))
		{
			return new Tiger();
		}
		else
		{
			return null;
		}
	}

	@Override
	public Class<?> getObjectType() {
		return Animal.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}

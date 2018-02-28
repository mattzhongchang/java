package com.matt.business.test.bean;

public class SayHelloServiceImpl implements SayHelloService{

	private String text;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void say() {
		System.out.println("say " + text);
		
	}

	
}

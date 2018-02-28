package com.matt.mattspring.test;

public class HelloWorldServiceImpl implements HelloWorldService {
	
	private String text;
	
	private OutputService outputService;
	
	public void setText(String text) {
		this.text = text;
	}

	public void setOutputService(OutputService outputService) {
		this.outputService = outputService;
	}

	@Override
	public void helloWorld()
	{
		this.outputService.output(this.text);
//		System.out.println("Hello World! text:" + text);
	}
}

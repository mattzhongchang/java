package com.matt.business.log;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.RollingFileAppender;



public class GT3KLogger implements Logger{

	private org.apache.log4j.Logger debugLog;
	
	private org.apache.log4j.Logger invokeLog;
	
	private org.apache.log4j.Logger performanceLog;
	
	
	public GT3KLogger()
	{
		String classPath = getClass().getClassLoader().getResource("").toString();
		String path = "/tasklog/";
		
		String log4jConfig = "D:/java/git/java/JavaProcess/config/log4j.properties"; 
		PropertyConfigurator.configure(log4jConfig);
		
		this.debugLog = org.apache.log4j.Logger.getLogger("debugLog");
		createLogger(this.debugLog, path, "DebugLog");
		
		this.invokeLog = org.apache.log4j.Logger.getLogger("invokeLog");
		createLogger(this.invokeLog, path, "InvokeLog");
		
		
	}
	
	
	private void createLogger(org.apache.log4j.Logger logger, String path, String name) 
	{
		RollingFileAppender appender = null;
		File dir = new File(path);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		File file = null;
		String fileName = "name.txt";
		try {
			file = File.createTempFile("name", ".txt", dir);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (!file.exists())
		{
			if (file.getParentFile() != null)
			{
				System.out.println("Fail to create file or directory: " + file.getParentFile().getAbsolutePath());
			}
			file.mkdirs();
		}
		System.out.println("create log " + file.getAbsolutePath() + " ");
		try {
			appender = new RollingFileAppender(new PatternLayout(), fileName, true);
			setRollFileAppender(appender, name);
			logger.addAppender(appender);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setRollFileAppender(RollingFileAppender appender, String name)
	{
		String maxFileSize = null;
		String maxBackFileIndex = null;
		
		String pattern = "[%-5p] %d(%r) --> [%t] %l: %m %x %n";
		appender.setMaxFileSize("20");
		appender.setMaxBackupIndex(100);
		appender.setLayout(new PatternLayout(pattern));
		appender.setImmediateFlush(true);
		
		System.out.println(appender);
		
	}
	
	
	public void debug(String message)
	{
		this.debugLog.debug(Thread.currentThread() + " " + message);
	}
	
	@Override
	public void info(String message) {
		debug(message);
	}

	
}

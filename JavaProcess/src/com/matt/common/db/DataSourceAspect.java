package com.matt.common.db;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class DataSourceAspect implements MethodInterceptor{

	private Logger logger = Logger.getLogger(DataSourceAspect.class);
	
	public void intercept(JoinPoint point)
	{
		Class<?> target = point.getTarget().getClass();
		MethodSignature signature = (MethodSignature) point.getSignature();
		// 
		for (Class<?> clazz : target.getInterfaces())
		{
			resolveDataSource(clazz, signature.getMethod());
		}
		resolveDataSource(target, signature.getMethod());
	}
	
	private void resolveDataSource(Class<?> clazz, Method method)
	{
		Class<?>[] types = method.getParameterTypes();
		// 默认使用类型注解
		if (clazz.isAnnotationPresent(DataSourceKey.class))
		{
			DataSourceKey dataSourceKey = clazz.getAnnotation(DataSourceKey.class);
			MultipleDataSource.setDataSourceKey(dataSourceKey.dataSourceKey());
			
			MultipleDataSource multipleDataSource = (MultipleDataSource) SpringContextUtil.getBean("multipleDataSource");
			BasicDataSource dataSource = (BasicDataSource) multipleDataSource.determineCurrentLookupKey();
			DataSourceInfo dataSourceInfo = new DataSourceInfo();
			dataSourceInfo.setDataSource(dataSource);
			dataSourceInfo.printDataSource();
			
		}
		
		// 方法注解可以覆盖类型注解
		try {
			Method m = clazz.getMethod(method.getName(), types);
			if (m != null && m.isAnnotationPresent(DataSourceKey.class))
			{
				DataSourceKey dataSourceKey = m.getAnnotation(DataSourceKey.class);
				MultipleDataSource.setDataSourceKey(dataSourceKey.dataSourceKey());
				
				MultipleDataSource multipleDataSource = (MultipleDataSource) SpringContextUtil.getBean("multipleDataSource");
				BasicDataSource dataSource = (BasicDataSource) multipleDataSource.determineCurrentLookupKey();
				DataSourceInfo dataSourceInfo = new DataSourceInfo();
				dataSourceInfo.setDataSource(dataSource);
				dataSourceInfo.printDataSource();
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		DataSourceKey dataSourceKey = invocation.getMethod().getAnnotation(DataSourceKey.class);
		if (dataSourceKey == null)
		{
			dataSourceKey = invocation.getThis().getClass().getAnnotation(DataSourceKey.class);
		}
		
		if (dataSourceKey == null)
		{
			logger.debug("未设置数据源，将采用默认的数据源!");
		}
		else
		{
			MultipleDataSource multipleDataSource = (MultipleDataSource) SpringContextUtil.getBean("multipleDataSource");
			String dataSource = (String) multipleDataSource.determineCurrentLookupKey();
			logger.debug("dataSource=" + dataSource);
			DataSourceInfo dataSourceInfo = new DataSourceInfo();
			dataSourceInfo.setDataSource((BasicDataSource) SpringContextUtil.getBean(dataSourceKey.dataSourceKey()));
			dataSourceInfo.printDataSource();
		}
		return invocation.proceed();
	}
}

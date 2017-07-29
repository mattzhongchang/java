package com.matt.common.db;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class DataSourceAspect {

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
		}
		
		// 方法注解可以覆盖类型注解
		try {
			Method m = clazz.getMethod(method.getName(), types);
			if (m != null && m.isAnnotationPresent(DataSourceKey.class))
			{
				DataSourceKey dataSourceKey = m.getAnnotation(DataSourceKey.class);
				MultipleDataSource.setDataSourceKey(dataSourceKey.dataSourceKey());
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
	}
}

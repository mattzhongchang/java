package org.matt.spring.beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.springframework.util.Assert;

public class BeanUtils {

	public static <T> T instantiateClass(Constructor<T> ctor, Object... args) throws Exception
	{
		Assert.notNull(ctor, "Constructor must not be null");
		
		BeanUtils.makeAccessible(ctor);
		return ctor.newInstance(args);
	}
	
	public static void makeAccessible(Constructor<?> ctor)
	{
		if ((!Modifier.isPublic(ctor.getModifiers()) || 
				!Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && 
				!ctor.isAccessible())
		{
			ctor.setAccessible(true);
		}
	}
}

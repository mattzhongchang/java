package org.matt.spring.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.util.Assert;


public class ClassUtils {
	
	public static final String ARRAY_SUFFIX = "[]";
	
	private static final String INTERNAL_ARRAY_PREFIX = "[";
	
	private static final String NON_PRIMITIVE_ARRAY_PREFIX = "[L";

	private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new HashMap<Class<?>, Class<?>>();
	
	// for example:int.class -> Integer.class
	private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new HashMap<Class<?>, Class<?>>();
	
	private static final Map<String, Class<?>> primitiveTypeNameMap = new HashMap<String, Class<?>>();
	
	private static final Map<String, Class<?>> commonClassCache = new HashMap<String, Class<?>>();
	
	static
	{
		primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
		primitiveWrapperTypeMap.put(Byte.class, byte.class);
		primitiveWrapperTypeMap.put(Character.class, char.class);
		primitiveWrapperTypeMap.put(Double.class, double.class);
		primitiveWrapperTypeMap.put(Float.class, float.class);
		primitiveWrapperTypeMap.put(Integer.class, int.class);
		primitiveWrapperTypeMap.put(Long.class, long.class);
		primitiveWrapperTypeMap.put(Short.class, short.class);
		
		for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperTypeMap.entrySet())
		{
			primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());
			registerCommonClasses(entry.getKey());
		}
		
		Set<Class<?>> primitiveTypes = new HashSet<Class<?>>(32);
		primitiveTypes.addAll(primitiveWrapperTypeMap.values());
		primitiveTypes.addAll(Arrays.asList(new Class<?>[]{
				boolean[].class, byte[].class, char[].class, double[].class, 
				float[].class, int[].class, long[].class, short[].class}));
		primitiveTypes.add(void.class);
		for (Class<?> primitiveType : primitiveTypes)
		{
			primitiveTypeNameMap.put(primitiveType.getName(), primitiveType);
		}

		registerCommonClasses(Boolean[].class, Byte[].class, Character[].class, Double[].class, 
				Float[].class, Integer[].class, Long[].class, Short[].class);
		registerCommonClasses(Number.class, Number[].class, String.class, String[].class, 
				Object.class, Object[].class, Class.class, Class[].class);
		registerCommonClasses(Throwable.class, Exception.class, RuntimeException.class, 
				Error.class, StackTraceElement.class, StackTraceElement[].class);
	}
	
	
	private static void registerCommonClasses(Class<?>... commonClasses)
	{
		for (Class<?> clazz : commonClasses)
		{
			commonClassCache.put(clazz.getName(), clazz);
		}
	}
	
	
	public static Class<?> forName(String name, ClassLoader classLoader) throws ClassNotFoundException
	{
		Assert.notNull(name, "Name must not be null");
		
		Class<?> clazz = resolvePrimitiveClassName(name);
		if (clazz == null)
		{
			clazz = commonClassCache.get(name);
		}
		if (clazz != null)
		{
			return clazz;
		}
		
		// "java.lang.String[]" style arrays
		if (name.endsWith(ARRAY_SUFFIX))
		{
			String elementClassName = name.substring(0, name.length() - ARRAY_SUFFIX.length());
			Class<?> elementClass = forName(elementClassName, classLoader);
			return Array.newInstance(elementClass, 0).getClass();
		}
		
		// "[Ljava.lang.String;" style arrays
		if (name.startsWith(NON_PRIMITIVE_ARRAY_PREFIX) && name.endsWith(";"))
		{
			String elementName = name.substring(NON_PRIMITIVE_ARRAY_PREFIX.length(), name.length()-1);
			Class<?> elementClass = forName(elementName, classLoader);
			return Array.newInstance(elementClass, 0).getClass();
		}
		
		// "[[I" or "[[Ljava.lang.String;" style arrays
		if (name.startsWith(INTERNAL_ARRAY_PREFIX))
		{
			String elementName = name.substring(INTERNAL_ARRAY_PREFIX.length());
			Class<?> elementClass = forName(elementName, classLoader);
			return Array.newInstance(elementClass, 0).getClass();
		}
		
		ClassLoader clToUse = classLoader;
		if (clToUse == null)
		{
			clToUse = getDefaultClassLoader();
		}
		
		try {
			return (clToUse != null ? clToUse.loadClass(name) : Class.forName(name));
		} catch (ClassNotFoundException e) {
			int lastDotIndex = name.lastIndexOf('.');
			if (lastDotIndex != -1)
			{
				String innerClassName = name.substring(0, lastDotIndex) + "$" + 
				                        name.substring(lastDotIndex + 1);
				try {
					return clToUse != null ? clToUse.loadClass(innerClassName) : Class.forName(innerClassName);
				} catch (ClassNotFoundException e1) {
					// 
				}
			}
			throw e;
		}
		
	}
	
	public static Class<?> resolvePrimitiveClassName(String name)
	{
		Class<?> result = null;
		if (name != null && name.length() <= 8)
		{
			result = primitiveTypeNameMap.get(name);
		}
		return result;
	}
	
	public static ClassLoader getDefaultClassLoader()
	{
		ClassLoader cl = null;
		try
		{
			cl = Thread.currentThread().getContextClassLoader();
		}
		catch (Throwable ex)
		{
			
		}
		if (cl == null)
		{
			// No thread context class loader -> use class loader of this class.
			cl = ClassUtils.class.getClassLoader();
			if (cl == null)
			{
				// getClassLoader() returning null indicates the bootstrap ClassLoader
				try
				{
					cl = ClassLoader.getSystemClassLoader();
				}
				catch (Throwable ex)
				{
					// cannot access system ClassLoader - oh well, maybe the caller can live with null...
				}
			}
		}
		
		return cl;
	}
}

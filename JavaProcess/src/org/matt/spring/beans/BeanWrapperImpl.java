package org.matt.spring.beans;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.matt.spring.beans.factory.config.TypedStringValue;
import org.springframework.util.Assert;

public class BeanWrapperImpl {

	private static final Log logger = LogFactory.getLog(BeanWrapperImpl.class);
	
	/** The wrapped object */
	private Object object;
	
	private String nestedPath = "";
	
    private Object rootObject;
    
    private CachedIntrospectionResults cachedIntrospectionResults;
    
    private Map<String, BeanWrapperImpl> nestedBeanWrappers;
    
    TypeConverterDelegate typeConverterDelegate;
    
    
    private boolean defaultEditorActive;
    
    
    public final Object getWrappedInstance()
    {
    	return this.object;
    }
    
    public final Class<?> getWrappedClass()
    {
    	return (this.object != null ? this.object.getClass() : null);
    }
    
    public final String getNestedPath()
    {
    	return this.nestedPath;
    }
    
    public final Object getRootInstance()
    {
    	return this.rootObject;
    }
    
    
    
    
    public BeanWrapperImpl(Object object)
    {
    	registerDefaultEditors();
    	setWrappedInstance(object);
    }
    
    protected void registerDefaultEditors()
    {
    	this.defaultEditorActive = true;
    }
    
    public void setWrappedInstance(Object object)
    {
    	setWrappedInstance(object, "", null);
    }
    
    public void setWrappedInstance(Object object, String nestedPath, Object rootObject)
    {
    	Assert.notNull(object, "Bean object must not be null");
    	this.object = object;
    	this.nestedPath = (nestedPath != null ? nestedPath : "");
    	this.rootObject = (!"".equals(this.nestedPath) ? rootObject : object);
    	this.nestedBeanWrappers = null;
//    	this.typeConverterDelegate = new TypeConverterDelegate(this, object);
    	this.typeConverterDelegate = new TypeConverterDelegate(null, object);
    	setIntrospectionClass(object.getClass());
    }
    
    protected void setIntrospectionClass(Class<?> clazz)
    {
    	if (this.cachedIntrospectionResults != null && !clazz.equals(this.cachedIntrospectionResults.getBeanClass()))
    	{
    		this.cachedIntrospectionResults = null;
    	}
    }
    
    public Object converterForProperty(Object value, String propertyName)
    {
    	// TODO 
    	return null;
    }
    
    public void setPropertyValues(PropertyValues pvs)
    {
    	for (PropertyValue pv : pvs.getPropertyValues())
    	{
    		setPropertyValue(pv.getName(), pv.getValue());
    	}
    }
    
    public void setPropertyValue(String propertyName, Object value)
    {
    	Class<?> clazz = getWrappedClass();
    	
    	try {
    		
    		if (value instanceof TypedStringValue)
    		{
    			value = ((TypedStringValue) value).getValue();
    		}
    		
			Method declaredMethod = clazz.getDeclaredMethod("set" + 
					propertyName.substring(0, 1).toUpperCase() + 
					propertyName.substring(1), value.getClass());
			declaredMethod.setAccessible(true);
			try {
				declaredMethod.invoke(this.object, value);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
    
    
}

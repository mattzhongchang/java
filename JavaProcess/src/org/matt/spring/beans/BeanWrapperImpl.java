package org.matt.spring.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.matt.spring.beans.factory.config.TypedStringValue;
import org.springframework.beans.PropertyAccessorUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

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
    
    
    public boolean isWritableProperty(String propertyName)
    {
    	PropertyDescriptor pd = getPropertyDescriptorInternal(propertyName);
    	if (pd != null)
    	{
    		if (pd.getWriteMethod() != null)
    		{
    			return true;
    		}
    	}
    	else
    	{
    		// TODO
    		return true;
    	}
    	
    	return false;
    }
    
    
    protected PropertyDescriptor getPropertyDescriptorInternal(String propertyName)
    {
    	BeanWrapperImpl nestedBw = getBeanWrapperForPropertyPath(propertyName);
    	return nestedBw.getCachedIntrospectionResults().getPropertyDescriptor(getFinalPath(nestedBw, propertyName));
    }
    
    private String getFinalPath(BeanWrapperImpl bw, String nestedPath)
    {
    	if (bw == this)
    	{
    		return nestedPath;
    	}
    	
    	return nestedPath.substring(PropertyAccessorUtils.getLastNestedPropertySeparatorIndex(nestedPath) + 1);
    }
    
	/**
	 * Recursively navigate to return a BeanWrapper for the nested property path.
	 * @param propertyPath property property path, which may be nested
	 * @return a BeanWrapper for the target bean
	 */
	protected BeanWrapperImpl getBeanWrapperForPropertyPath(String propertyPath) {
		int pos = PropertyAccessorUtils.getFirstNestedPropertySeparatorIndex(propertyPath);
		// Handle nested properties recursively.
		if (pos > -1) {
//			String nestedProperty = propertyPath.substring(0, pos);
//			String nestedPath = propertyPath.substring(pos + 1);
//			BeanWrapperImpl nestedBw = getNestedBeanWrapper(nestedProperty);
//			return nestedBw.getBeanWrapperForPropertyPath(nestedPath);
			
			return this;
		}
		else {
			return this;
		}
	}
    
    
    
    
    protected void setIntrospectionClass(Class<?> clazz)
    {
    	if (this.cachedIntrospectionResults != null && !clazz.equals(this.cachedIntrospectionResults.getBeanClass()))
    	{
    		this.cachedIntrospectionResults = null;
    	}
    }
    
    
    private CachedIntrospectionResults getCachedIntrospectionResults()
    {
    	if (this.cachedIntrospectionResults == null) {
			this.cachedIntrospectionResults = CachedIntrospectionResults.forClass(getWrappedClass());
		}
		return this.cachedIntrospectionResults;
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
//    		setPropertyValue(pv.getName(), pv.getValue());
    		
    		setPropertyValue(pv);
    	}
    }
    
    
    public void setPropertyValue(PropertyValue pv)
    {
    	PropertyTokenHolder tokens = (PropertyTokenHolder) pv.resolvedTokens;
    	if (tokens == null)
    	{
    		String propertyName = pv.getName();
    		BeanWrapperImpl nestedBw = getBeanWrapperForPropertyPath(propertyName);
    		tokens = getPropertyNameTokens(getFinalPath(nestedBw, propertyName));
    		if (nestedBw == this)
    		{
    			
    		}
    		nestedBw.setPropertyValue(tokens, pv);
    	}
    	else
    	{
    		setPropertyValue(tokens, pv);
    	}
    }
    
    
    private void setPropertyValue(PropertyTokenHolder tokens, PropertyValue pv)
    {
    	String propertyName = tokens.canonicalName;
    	String actualName = tokens.actualName;
    	
    	if (tokens.keys != null)
    	{
    		
    	}
    	else
    	{
    		PropertyDescriptor pd = pv.resolvedDescriptor;
    		if (pd == null || !pd.getWriteMethod().getDeclaringClass().isInstance(this.object))
    		{
    			pd = getCachedIntrospectionResults().getPropertyDescriptor(actualName);
    			if (pd == null || pd.getWriteMethod() == null)
    			{
    				logger.error("setPropertyValue“Ï≥££°");
    			}
    			
    		}
    		Object oldValue = null;
    		
    		Object originalValue = pv.getValue();
    		Object valueToApply = originalValue;
    		
    		if (pv.isConverted())
    		{
    			valueToApply = pv.getConvertedValue();
    		}
    		
    		final Method writeMethod = (pd instanceof GenericTypeAwarePropertyDescriptor ? 
    				((GenericTypeAwarePropertyDescriptor) pd).getWriteMethodForActualAccess() : 
    					pd.getWriteMethod());
    		
    		final Object value = valueToApply;
    		try {
				writeMethod.invoke(this.object, value);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }
    
    
    private PropertyTokenHolder getPropertyNameTokens(String propertyName)
    {
    	PropertyTokenHolder tokens = new PropertyTokenHolder();
    	String actualName = null;
    	List<String> keys = new ArrayList<String>(2);
    	int searchIndex = 0;
    	while (searchIndex != -1)
    	{
    		int keyStart = propertyName.indexOf("[", searchIndex);
    		searchIndex = -1;
    		if (keyStart != -1)
    		{
    			int keyEnd = propertyName.indexOf("]", keyStart + "[".length());
    			if (keyEnd != -1)
    			{
    				if (actualName == null)
    				{
    					actualName = propertyName.substring(0, keyStart);
    				}
    				String key = propertyName.substring(keyStart + "[".length(), keyEnd);
    				if ((key.startsWith("'") && key.endsWith("'")) || (key.startsWith("\"") && key.endsWith("\"")))
    				{
    					key = key.substring(1, key.length() - 1);
    				}
    				keys.add(key);
    				searchIndex = keyEnd + "]".length();
    			}
    		}
    	}
    	tokens.actualName = (actualName != null ? actualName : propertyName);
    	tokens.canonicalName = tokens.actualName;
    	if (!keys.isEmpty())
    	{
    		tokens.canonicalName += "[" + 
    		          StringUtils.collectionToDelimitedString(keys, "]" + "[") + 
    		          "]";
    		tokens.keys = StringUtils.toStringArray(keys);
    	}
    	return tokens;
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
    
    
    private static class PropertyTokenHolder
    {
    	public String canonicalName;
    	
    	public String actualName;
    	
    	public String[] keys;
    }
    
}

package org.matt.spring.beans.factory.support;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.matt.spring.beans.BeanWrapperImpl;
import org.matt.spring.beans.MutablePropertyValues;
import org.matt.spring.beans.PropertyValue;
import org.matt.spring.beans.PropertyValues;
import org.matt.spring.beans.factory.BeanFactory;
import org.matt.spring.beans.factory.config.BeanDefinition;
import org.matt.spring.beans.factory.config.TypedStringValue;
import org.springframework.beans.PropertyAccessorUtils;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.MethodParameter;
import org.springframework.util.ObjectUtils;


public abstract class AbstractBeanFactory implements BeanFactory{

	private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();
	
	
	private TypeConverter typeConverter;
	
	
	protected TypeConverter getCustomTypeConverter()
	{
		return this.typeConverter;
	}
	
	
	protected Object createBean(String beanName, RootBeanDefinition mbd, Object[] args)
	{
		return doCreateBean(beanName, mbd, args);
	}
	
	
	protected Object doCreateBean(String beanName, RootBeanDefinition mbd, Object[] args)
	{
		BeanWrapperImpl instanceWrapper = createBeanInstance(beanName, mbd, args);
		
		Object bean = (instanceWrapper != null ? instanceWrapper.getWrappedInstance() : null);
		Class<?> beanType = (instanceWrapper != null ? instanceWrapper.getWrappedClass() : null);
		
		// Initialize the bean instance.
		Object exposedObject = bean;
		
		populateBean(beanName, mbd, instanceWrapper);
		
		return instanceWrapper.getWrappedInstance();
	}
	
	
	protected void populateBean(String beanName, RootBeanDefinition mbd, BeanWrapperImpl bw)
	{
		PropertyValues pvs = mbd.getPropertyValues();
		
		applyPropertyValues(beanName, mbd, bw, pvs);
		
	}

	protected void applyPropertyValues(String beanName, BeanDefinition mbd, BeanWrapperImpl bw, PropertyValues pvs)
	{
		MutablePropertyValues mpvs = null;
		List<PropertyValue> original;
		
		if (pvs instanceof MutablePropertyValues)
		{
			mpvs = (MutablePropertyValues) pvs;
			original = mpvs.getPropertyValueList();
		}
		else
		{
			original = Arrays.asList(pvs.getPropertyValues());
		}
		
		TypeConverter converter = getCustomTypeConverter();
		BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this, beanName, mbd, converter);
		
		
		List<PropertyValue> deepCopy = new ArrayList<PropertyValue>(original.size());
		boolean resolveNecessary = false;
		for (PropertyValue pv : original)
		{
			String propertyName = pv.getName();
			Object originalValue = pv.getValue();
			
			Object resolvedValue = valueResolver.resolveValueIfNecessary(pv, originalValue);
			Object convertedValue = resolvedValue;
			
			boolean convertible = bw.isWritableProperty(propertyName) && 
			         !PropertyAccessorUtils.isNestedOrIndexedProperty(propertyName);
			if (convertible)
			{
				convertedValue = convertForProperty(resolvedValue, propertyName, bw, converter);
			}
			
			if (resolvedValue == originalValue)
			{
				if (convertible)
				{
					pv.setConvertedValue(convertedValue);
				}
				deepCopy.add(pv);
			}
			else if (convertible && originalValue instanceof TypedStringValue && 
					!(convertedValue instanceof Collection || ObjectUtils.isArray(convertedValue)))
			{
				pv.setConvertedValue(convertedValue);
				deepCopy.add(pv);
			}
			else
			{
				resolveNecessary = true;
				deepCopy.add(new PropertyValue(pv, convertedValue));
			}
		}
		
		if (mpvs != null && !resolveNecessary)
		{
			mpvs.setConverted();
		}
		
		bw.setPropertyValues(new MutablePropertyValues(deepCopy));
		
		
	}
	
	
	private Object convertForProperty(Object value, String propertyName, BeanWrapperImpl bw, TypeConverter converter)
	{
		if (converter instanceof BeanWrapperImpl)
		{
			return ((BeanWrapperImpl) converter).converterForProperty(value, propertyName);
		}
		else
		{
			// TODO 
			return value;
		}
	}
	

	protected BeanWrapperImpl createBeanInstance(String beanName, RootBeanDefinition mbd, Object[] args)
	{
		return instantiateBean(beanName, mbd);
	}
	
	protected BeanWrapperImpl instantiateBean(String beanName, RootBeanDefinition mbd)
	{
		final BeanFactory parent = this;
		try {
			Object beanInstance = getInstantiationStrategy().instantiate(mbd, beanName, parent);
			BeanWrapperImpl bw = new BeanWrapperImpl(beanInstance);
			
			return bw;
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		return null;
	}


	public InstantiationStrategy getInstantiationStrategy() {
		return this.instantiationStrategy;
	}
	
	
	
	private static class CustomTypeConverter implements TypeConverter
	{
		
		private final NumberFormat numberFormat;
		
		public CustomTypeConverter(NumberFormat numberFormat) {
			super();
			this.numberFormat = numberFormat;
		}

		@Override
		public Object convertIfNecessary(Object value, Class requiredType)
				throws TypeMismatchException {
			if (value instanceof String && Float.class.isAssignableFrom(requiredType))
			{
				try {
					return new Float(this.numberFormat.parse((String) value).floatValue());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
			else if (value instanceof String && int.class.isAssignableFrom(requiredType))
			{
				return new Integer(5);
			}
			else
			{
				return value;
			}
			
		}

		@Override
		public Object convertIfNecessary(Object value, Class requiredType,
				MethodParameter methodParam) throws TypeMismatchException {
			return convertIfNecessary(value, requiredType);
		}

		@Override
		public Object convertIfNecessary(Object value, Class requiredType,
				Field field) throws TypeMismatchException {
			return convertIfNecessary(value, requiredType);
		}
		
		
	}
	
}

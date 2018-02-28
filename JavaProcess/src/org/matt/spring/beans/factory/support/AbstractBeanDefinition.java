package org.matt.spring.beans.factory.support;

import org.matt.spring.beans.MutablePropertyValues;
import org.matt.spring.beans.factory.config.BeanDefinition;
import org.matt.spring.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.MethodOverrides;

public class AbstractBeanDefinition implements BeanDefinition{
	
	public static final String SCOPE_DEFAULT = "";
	
	public static final int AUTOWIRE_NO = 0;
	
	public static final int AUTOWIRE_BY_NAME = 1;

	public static final int AUTOWIRE_BY_TYPE = 2;

	public static final int AUTOWIRE_CONSTRUCTOR = 3;
	
	public static final int AUTOWIRE_AUTODETECT = 4;

	
	public volatile Object beanClass;
	
	private String scope = SCOPE_DEFAULT;
	
	private boolean singleton = true;
	
	private boolean prototype = false;
	
	private boolean lazyInit = false;
	
	private int autowireMode = 0;
	
	private int dependencyCheck = 0;
	
	private String[] dependsOn;
	
	private boolean autowireCandidate  = true;
	
	private boolean primary = false;
	
	private String factoryBeanName;
	
	private String factoryMethodName;
	
	private String initMethodName;
	
	private String destroyMethodName;
	
	private String description;
	
	private boolean abstractFlag;
	
	private ConstructorArgumentValues constructorArgumentValues;
	
	private MutablePropertyValues propertyValues;
	
	
	
	public AbstractBeanDefinition() {
		this(null, null);
	}

	public AbstractBeanDefinition(ConstructorArgumentValues constructorArgumentValues,
			MutablePropertyValues propertyValues) {
		this.constructorArgumentValues = constructorArgumentValues;
		this.propertyValues = propertyValues;
	}
	
	protected AbstractBeanDefinition(BeanDefinition original)
	{
		setParentName(original.getParentName());
		setBeanClassName(original.getBeanClassName());
		setFactoryBeanName(original.getFactoryBeanName());
		setFactoryMethodName(original.getFactoryMethodName());
		setScope(original.getScope());
		setAbstract(original.isAbstract());
		setLazyInit(original.isLazyInit());

		setConstructorArgumentValues(original.getConstructorArgumentValues());
		setPropertyValues(original.getPropertyValues());
		
		if (original instanceof AbstractBeanDefinition)
		{
			AbstractBeanDefinition originalAbd = (AbstractBeanDefinition) original;
			setBeanClass(originalAbd.getBeanClass());
			setAutowireMode(originalAbd.getAutowireMode());
			setDependencyCheck(originalAbd.getDependencyCheck());
			setDependsOn(originalAbd.getDependsOn());
			setAutowireCandidate(originalAbd.isAutowireCandidate());
			setPrimary(originalAbd.isPrimary());
			setInitMethodName(originalAbd.getInitMethodName());
			setDestroyMethodName(originalAbd.getDestroyMethodName());
		}
	}
	

	@Override
	public String getParentName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParentName(String parentName) {
		// TODO Auto-generated method stub
		
	}
	
	public Class<?> getBeanClass() throws IllegalStateException {
		Object beanClassObject = this.beanClass;
		if (beanClassObject == null) {
			throw new IllegalStateException("No bean class specified on bean definition");
		}
		if (!(beanClassObject instanceof Class)) {
			throw new IllegalStateException(
					"Bean class name [" + beanClassObject + "] has not been resolved into an actual Class");
		}
		return (Class<?>) beanClassObject;
	}

	@Override
	public String getBeanClassName() {
		Object beanClassObject = this.beanClass;
		if (beanClassObject instanceof Class)
		{	
			return ((Class<?>) beanClassObject).getName();
		}
		else
		{
			return (String) beanClassObject;
		}
	}

	@Override
	public void setBeanClassName(String beanClassName) {
		this.beanClass = beanClassName;
	}
	
	public void setBeanClass(Class<?> beanClass)
	{
		this.beanClass = beanClass;
	}

	@Override
	public String getScope() {
		return this.scope;
	}

	@Override
	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}

	@Override
	public boolean isLazyInit() {
		return this.lazyInit;
	}

	@Override
	public void setLazyInit(boolean lazyInit) {
		this.lazyInit = lazyInit;
	}

	public int getAutowireMode() {
		return autowireMode;
	}

	public void setAutowireMode(int autowireMode) {
		this.autowireMode = autowireMode;
	}

	@Override
	public boolean isSingleton() {
		return this.singleton;
	}

	@Override
	public boolean isPrototype() {
		return this.prototype;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	public ConstructorArgumentValues getConstructorArgumentValues() {
		return constructorArgumentValues;
	}

	public void setConstructorArgumentValues(
			ConstructorArgumentValues constructorArgumentValues) {
		this.constructorArgumentValues = constructorArgumentValues;
	}

	@Override
	public MutablePropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(MutablePropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

    public void setAbstract(boolean abstractFlag)
    {
    	this.abstractFlag = abstractFlag;
    }
    
    public boolean isAbstract()
    {
    	return this.abstractFlag;
    }

	public int getDependencyCheck() {
		return dependencyCheck;
	}

	public void setDependencyCheck(int dependencyCheck) {
		this.dependencyCheck = dependencyCheck;
	}

	public String[] getDependsOn() {
		return dependsOn;
	}

	public void setDependsOn(String[] dependsOn) {
		this.dependsOn = dependsOn;
	}

	public boolean isAutowireCandidate() {
		return autowireCandidate;
	}

	public void setAutowireCandidate(boolean autowireCandidate) {
		this.autowireCandidate = autowireCandidate;
	}

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public String getInitMethodName() {
		return initMethodName;
	}

	public void setInitMethodName(String initMethodName) {
		this.initMethodName = initMethodName;
	}

	public String getDestroyMethodName() {
		return destroyMethodName;
	}

	public void setDestroyMethodName(String destroyMethodName) {
		this.destroyMethodName = destroyMethodName;
	}

	public String getFactoryBeanName() {
		return factoryBeanName;
	}

	public void setFactoryBeanName(String factoryBeanName) {
		this.factoryBeanName = factoryBeanName;
	}

	public String getFactoryMethodName() {
		return factoryMethodName;
	}

	public void setFactoryMethodName(String factoryMethodName) {
		this.factoryMethodName = factoryMethodName;
	}
	
	
	
}

package org.matt.spring.beans.factory.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.matt.spring.beans.PropertyValue;
import org.matt.spring.beans.factory.config.BeanDefinition;
import org.matt.spring.beans.factory.config.TypedStringValue;
import org.matt.spring.beans.factory.support.AbstractBeanDefinition;
import org.matt.spring.beans.factory.support.BeanDefinitionReaderUtils;
import org.matt.spring.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class BeanDefinitionParserDelegate {

	
	public static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";
	
	public static final String MULTI_VALUE_ATTRIBUTE_DELIMITERS = ",; ";
	
	public static final String TRUE_VALUE = "true";
	
	public static final String FALSE_VALUE = "false";
	
	public static final String DEFAULT_VALUE = "default";
	
	public static final String DESCRIPTION_ELEMENT = "description";
	
	public static final String AUTOWIRE_NO_VALUE = "no";
	
	public static final String AUTOWIRE_BY_NAME_VALUE = "byName";
	
	public static final String AUTOWIRE_BY_TYPE_VALUE = "byType";
	
	public static final String AUTOWIRE_CONSTRUCTOR_VALUE = "constructor";
	
	public static final String AUTOWIRE_AUTODETECT_VALUE = "autodetect";
	
	public static final String DEPENDENCY_CHECK_ALL_ATTRIBUTE_VALUE = "all";
	
	public static final String DEPENDENCY_CHECK_SIMPLE_ATTRIBUTE_VALUE = "simple";
	
	public static final String DEPENDENCY_CHECK_OBJECTS_ATTRIBUTE_VALUE = "objects";
	
	public static final String NAME_ATTRIBUTE = "name";
	
	public static final String BEAN_ELEMENT = "bean";
	
	public static final String META_ELEMENT = "meta";
	
	public static final String ID_ATTRIBUTE = "id";
	
	public static final String PARENT_ATTRIBUTE = "parent";
	
	public static final String CLASS_ATTRIBUTE = "class";
	
	public static final String ABSTRACT_ATTRIBUTE = "abstract";
	
	public static final String SCOPE_ATTRIBUTE = "scope";
	
	public static final String SINGLETON_ATTRIBUTE = "singleton";
	
	public static final String LAZY_INIT_ATTRIBUTE = "lazy-init";
	
	public static final String AUTOWIRE_ATTRIBUTE = "autowire";
	
	public static final String AUTOWIRE_CANDIDATE_ATTRIBUTE = "autowire-candidate";
	
	public static final String PRIMARY_ATTRIBUTE = "primary";
	
	public static final String DEPENDS_ON_ATTRIBUTE = "depends-on";
	
	public static final String INIT_METHOD_ATTRIBUTE = "init-method";
	
	public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";
	
	public static final String FACTORY_METHOD_ATTRIBUTE = "factory-method";
	
	public static final String FACTORY_BEAN_ATTRIBUTE = "factory-bean";
	
	public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";
	
	public static final String INDEX_ATTRIBUTE = "index";
	
	public static final String TYPE_ATTRIBUTE = "type";
	
	public static final String VALUE_TYPE_ATTRIBUTE = "value-type";
	
	public static final String KEY_TYPE_ATTRIBUTE = "key-type";
	
	public static final String PROPERTY_ELEMENT = "property";
	
	public static final String REF_ATTRIBUTE = "ref";
	
	public static final String VALUE_ATTRIBUTE = "value";
	
	public static final String LOOKUP_METHOD_ELEMENT = "lookup-method";
	
	public static final String REPLACED_METHOD_ELEMENT = "replaced-method";
	
	public static final String REPLACER_ATTRIBUTE = "replacer";
	
	public static final String ARG_TYPE_ELEMENT = "arg-type";
	
	public static final String ARG_TYPE_MATCH_ATTRIBUTE = "match";
	
	public static final String REF_ELEMENT = "ref";
	
	public static final String IDREF_ELEMENT = "idref";
	
	public static final String BEAN_REF_ATTRIBUTE = "bean";
	
	public static final String LOCAL_REF_ATTRIBUTE = "local";
	
	public static final String PARENT_REF_ATTRIBUTE = "parent";
	
	public static final String VALUE_ELEMENT = "value";
	
	public static final String NULL_ELEMENT = "null";
	
	public static final String ARRAY_ELEMENT = "array";
	
	public static final String LIST_ELEMENT = "list";
	
	public static final String SET_ELEMENT = "set";
	
	public static final String MAP_ELEMENT = "map";
	
	public static final String ENTRY_ELEMENT = "entry";
	
	public static final String KEY_ELEMENT = "key";
	
	public static final String KEY_REF_ATTRIBUTE = "key-ref";
	
	public static final String VALUE_REF_ATTRIBUTE = "value-ref";
	
	public static final String PROPS_ELEMENT = "props";
	
	public static final String PROP_ELEMENT = "prop";
	
	public static final String MERGE_ATTRIBUTE = "merge";
	
	public static final String QUALIFIER_ELEMENT = "qualifier";
	
	public static final String QUALIFIER_ATTRIBUTE_ELEMENT = "attribute";
	
	public static final String DEFAULT_LAZY_INIT_ATTRIBUTE = "default-lazy-init";
	
	public static final String DEFAULT_MERGE_ATTRIBUTE = "default-merge";
	
	public static final String DEFAULT_AUTOWIRE_ATTRIBUTE = "default-autowire";
	
	public static final String DEFAULT_EDPENDENCY_CHECK_ATTRIBUTE = "default-dependecy-check";
	
	public static final String DEFAULT_AUTOWIRE_CANDIDATES_ATTRIBUTE = "default-autowire-candidates";
	
	public static final String DEFAULT_INIT_METHOD_ATTRIBUTE = "default-init-method";
	
	public static final String DEFAULT_DESTORY_METHOD_ATTRIBUTE = "default-destroy-method";
	
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private XmlReaderContext readerContext;
	
	
	public XmlReaderContext getReaderContext() {
		return readerContext;
	}

	public void setReaderContext(XmlReaderContext readerContext) {
		this.readerContext = readerContext;
	}

//	protected void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate)
//	{
//		BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);
//		if (bdHolder != null)
//		{
//			BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, this.readerContext.getReader().getRegistry());
//		}
//	}
	
	public BeanDefinitionHolder parseBeanDefinitionElement(Element ele)
	{
		return parseBeanDefinitionElement(ele, null);
	}
	
	public BeanDefinitionHolder parseBeanDefinitionElement(Element ele, BeanDefinition containingBean)
	{
		String id = ele.getAttribute(ID_ATTRIBUTE);
		String nameAttr = ele.getAttribute(NAME_ATTRIBUTE);
		
		List<String> aliases = new ArrayList<String>();
		if (StringUtils.hasLength(nameAttr))
		{
			String[] nameArr = StringUtils.tokenizeToStringArray(nameAttr, MULTI_VALUE_ATTRIBUTE_DELIMITERS);
			aliases.addAll(Arrays.asList(nameArr));
		}
		
		String beanName = id;
		if (!StringUtils.hasText(beanName) && !aliases.isEmpty())
		{
			beanName = aliases.remove(0);
		}
		
		AbstractBeanDefinition beanDefinition = parseBeanDefinitionElement(ele, beanName, containingBean);
		
		if (beanDefinition != null)
		{
			String[] aliasesArray = StringUtils.toStringArray(aliases);
			return new BeanDefinitionHolder(beanDefinition, beanName, aliasesArray);
		}
		return null;
	}
	
	/**
	 * 解析Element为AbstractBeanDefinition
	 * @param ele
	 * @param beanName
	 * @param containingBean
	 * @return
	 */
	public AbstractBeanDefinition parseBeanDefinitionElement(Element ele, String beanName, BeanDefinition containingBean)
	{
		String className = null;
		if (ele.hasAttribute(CLASS_ATTRIBUTE))
		{
			className = ele.getAttribute(CLASS_ATTRIBUTE);
		}
		
		String parent = null;
		if (ele.hasAttribute(PARENT_ATTRIBUTE))
		{
			parent = ele.getAttribute(PARENT_ATTRIBUTE);
		}
		
		try {
			// 创建用于装属性的AbstractBeanDefinition类型的GenericBeanDefinition
			AbstractBeanDefinition bd = createBeanDefinition(className, parent);
			// 硬编码解析默认bean的各种属性
			parseBeanDefinitionAttributes(ele, beanName, containingBean, bd);
			// 解析property子元素
			parsePropertyElements(ele, bd);

			// TODO 各种解析 暂缺
			
			return bd;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected AbstractBeanDefinition createBeanDefinition(String className, String parentName) throws ClassNotFoundException
	{
		return BeanDefinitionReaderUtils.createBeanDefinition(
				parentName, className, BeanDefinitionParserDelegate.class.getClassLoader());
	}
	
	
	public AbstractBeanDefinition parseBeanDefinitionAttributes(Element ele, String beanName, 
			BeanDefinition containingBean, AbstractBeanDefinition bd)
	{
		// 解析scope属性
		if (ele.hasAttribute(SCOPE_ATTRIBUTE))
		{
			bd.setScope(ele.getAttribute(SCOPE_ATTRIBUTE));
			if (ele.hasAttribute(SINGLETON_ATTRIBUTE))
			{
				error("Specify either 'scope' or 'singleton', not both", ele);
			}
		}
		else if (ele.hasAttribute(SINGLETON_ATTRIBUTE))
	    {
			bd.setScope(TRUE_VALUE.equals(ele.getAttribute(SINGLETON_ATTRIBUTE)) ? 
					"singleton" : "scope");
		}
		else if (containingBean != null)
		{
			bd.setScope(containingBean.getScope());
		}
		
		// 解析abstract属性
		if (ele.hasAttribute(ABSTRACT_ATTRIBUTE))
		{
			bd.setAbstract(TRUE_VALUE.equals(ele.getAttribute(ABSTRACT_ATTRIBUTE)));
		}
		
		// 解析lazy-init属性
		String lazyInit = ele.getAttribute(LAZY_INIT_ATTRIBUTE);
		if (DEFAULT_VALUE.equals(lazyInit))
		{
//			lazyInit = 
		}
		bd.setLazyInit(TRUE_VALUE.equals(lazyInit));
		
		// 解析autowire属性
		String autowire = ele.getAttribute(AUTOWIRE_ATTRIBUTE);
		bd.setAutowireMode(getAutowireMode(autowire));
		
//		String dependencyCheck = ele.getAttribute(DEPENDENCY_CHECK_ATTRIBUTE);
//	    bd.set
		
		// 解析dependency-check属性
		if (ele.hasAttribute(DEPENDS_ON_ATTRIBUTE))
		{
			String dependsOn = ele.getAttribute(DEPENDS_ON_ATTRIBUTE);
			bd.setDependsOn(StringUtils.tokenizeToStringArray(dependsOn, ",; "));
		}
		
		// 解析autowire-candidate属性
		String autowireCandidate = ele.getAttribute(AUTOWIRE_CANDIDATE_ATTRIBUTE);
		if ("".equals(autowireCandidate) || DEFAULT_VALUE.equals(autowireCandidate))
		{
			
		}
		else
		{
			bd.setAutowireCandidate(TRUE_VALUE.equals(autowireCandidate));
		}

		// 解析primary属性
		if (ele.hasAttribute(PRIMARY_ATTRIBUTE))
		{
		    bd.setPrimary(TRUE_VALUE.equals(ele.getAttribute(PRIMARY_ATTRIBUTE)));
		}
		
		// 解析init-method属性
		if (ele.hasAttribute(INIT_METHOD_ATTRIBUTE))
		{
			String initMethodName = ele.getAttribute(INIT_METHOD_ATTRIBUTE);
			if (!"".equals(initMethodName))
			{
				bd.setInitMethodName(initMethodName);
			}
		}
		else
		{
			// 
		}
		
		// 解析destroy-method属性
		if (ele.hasAttribute(DESTROY_METHOD_ATTRIBUTE))
		{
			String destroyMethodName = ele.getAttribute(DESTROY_METHOD_ATTRIBUTE);
			if (!"".equals(destroyMethodName))
			{
			    bd.setDestroyMethodName(destroyMethodName);
		    }
		}
		else
		{
			//
		}
		
		if (ele.hasAttribute(FACTORY_METHOD_ATTRIBUTE))
		{
			bd.setFactoryMethodName(ele.getAttribute(FACTORY_METHOD_ATTRIBUTE));
		}
		
		if (ele.hasAttribute(FACTORY_BEAN_ATTRIBUTE))
		{
			bd.setFactoryBeanName(ele.getAttribute(FACTORY_BEAN_ATTRIBUTE));
		}
		
		return bd;
	}
	
	
	public int getAutowireMode(String attValue)
	{
		String att = attValue;
		if (DEFAULT_VALUE.equals(att))
		{
			
		}
		int autowire = AbstractBeanDefinition.AUTOWIRE_NO;
		if (AUTOWIRE_BY_NAME_VALUE.equals(att))
		{
			autowire = AbstractBeanDefinition.AUTOWIRE_BY_NAME;
		}
		else if (AUTOWIRE_BY_TYPE_VALUE.equals(att))
		{
			autowire = AbstractBeanDefinition.AUTOWIRE_BY_TYPE;
		}
		else if (AUTOWIRE_CONSTRUCTOR_VALUE.equals(att))
		{
			autowire = AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR;
		}
		else if (AUTOWIRE_AUTODETECT_VALUE.equals(att))
		{
			autowire = AbstractBeanDefinition.AUTOWIRE_AUTODETECT;
		}
		return autowire;
	}
	
	/**
	 * 解析property
	 * @param ele
	 * @param bd
	 */
	protected void parsePropertyElements(Element beanEle, AbstractBeanDefinition bd)
	{
		NodeList nl = beanEle.getChildNodes();
		for (int i=0; i<nl.getLength(); i++)
		{
			Node node = nl.item(i);
			if (nodeNameEquals(node, PROPERTY_ELEMENT))
			{
				parsePropertyElement((Element) node, bd);
			}
		}
	}
	
	public void parsePropertyElement(Element ele, BeanDefinition bd)
	{
		String propertyName = ele.getAttribute(NAME_ATTRIBUTE);
		if (!StringUtils.hasLength(propertyName))
		{
			error("Tag 'property' must have a 'name' attribute", ele);
			return;
		}
		Object val = parsePropertyValue(ele, bd, propertyName);
		PropertyValue pv = new PropertyValue(propertyName, val);
		bd.getPropertyValues().addPropertyValue(pv);
		
	}
	
	public Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName)
	{
		String elementName = (propertyName != null) ? 
				"<property> element for property '" + propertyName + "'" : "<constructor-arg> element";
		NodeList nl = ele.getChildNodes();
		Element subElement = null;
		for (int i=0; i<nl.getLength(); i++)
		{
			Node node = nl.item(i);
		}
		
		boolean hasRefAttribute = ele.hasAttribute(REF_ATTRIBUTE);
		boolean hasValueAttribute = ele.hasAttribute(VALUE_ATTRIBUTE);
		if (hasRefAttribute && hasValueAttribute)
		{
			error(elementName + " is only allowed to contain either 'ref' attribute OR 'value' attribute", ele);
		}
		
		if (hasRefAttribute)
		{
			String refName = ele.getAttribute(REF_ATTRIBUTE);
			if (!StringUtils.hasLength(refName))
			{
				error(elementName + " contains empty 'ref' attribute", ele);
			}
			RuntimeBeanReference ref = new RuntimeBeanReference(refName);
			return ref;
		}
		else if (hasValueAttribute)
		{
			TypedStringValue valueHolder = new TypedStringValue(ele.getAttribute(VALUE_ATTRIBUTE));
			return valueHolder;
		}
		else
		{
			error(elementName + " must specify a ref or value", ele);
			return null;
		}
	}
	
	
	
	public boolean nodeNameEquals(Node node, String desiredName)
	{
		return desiredName.equals(node.getNodeName()) || desiredName.equals(node.getLocalName());
	}
	
	public boolean isDefaultNamespace(String namespaceUri)
	{
		return (!StringUtils.hasLength(namespaceUri) || BEANS_NAMESPACE_URI.equals(namespaceUri));
	}
	
	public boolean isDefaultNamespace(Node node)
	{
		return isDefaultNamespace(getNamespaceURI(node));
	}
	
	public String getNamespaceURI(Node node)
	{
		return node.getNamespaceURI();
	}
	
	protected void error(String message, Element source)
	{
		
	}
	
}

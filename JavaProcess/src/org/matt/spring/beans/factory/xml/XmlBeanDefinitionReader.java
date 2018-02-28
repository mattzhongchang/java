package org.matt.spring.beans.factory.xml;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.matt.spring.beans.factory.support.AbstractBeanDefinitionReader;
import org.matt.spring.beans.factory.support.BeanDefinitionRegistry;
import org.matt.spring.core.io.Resource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

	
	
//	private BeanDefinitionRegistry registry;

	private Class<?> documentReaderClass = DefaultBeanDefinitionDocumentReader.class;
	
	private DefaultDocumentLoader documentLoader = new DefaultDocumentLoader();
	
	
	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		super(registry);
		
	}

//	public BeanDefinitionRegistry getRegistry() {
//		return registry;
//	}
//
//	public void setRegistry(BeanDefinitionRegistry registry) {
//		this.registry = registry;
//	}
	
	
	public int loadBeanDefinitions(Resource resource)
	{
		try {
			InputSource inputSource = new InputSource(resource.getInputStream());
			return doLoadBeanDefinitions(inputSource, resource);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	

	protected int doLoadBeanDefinitions(InputSource inputSource, Resource resource)
	{
		try {
			Document doc = this.documentLoader.loadDocument(inputSource);
			return registerBeanDefinitions(doc, resource);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return 0;
		
	}
	
	public int registerBeanDefinitions(Document doc, Resource resource)
	{
		DefaultBeanDefinitionDocumentReader documentReader = createBeanDefinitionDocumentReader();
		documentReader.registerBeanDefinitions(doc, new XmlReaderContext(this, resource));
		
		return 1;
	}
	
	protected DefaultBeanDefinitionDocumentReader createBeanDefinitionDocumentReader() 
	{
		Class<?> clazz = this.documentReaderClass;
		Constructor<?> ctor = null;
		try {
			ctor = clazz.getDeclaredConstructor();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctor.setAccessible(true);
		try {
			return (DefaultBeanDefinitionDocumentReader) ctor.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return null;
		
	}
	
}

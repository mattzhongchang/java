package com.matt.springframework.beans.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.matt.springframework.BeanReference;
import com.matt.springframework.beans.AbstractBeanDefinitionReader;
import com.matt.springframework.beans.BeanDefinition;
import com.matt.springframework.beans.PropertyValue;
import com.matt.springframework.beans.io.ResourceLoader;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
	
	public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
		super(resourceLoader);
	}

	@Override
	public void loadBeanDefinitions(String location) throws Exception {
		InputStream in = getResourceLoader().getResource(location).getInputStream();
		
		
		// TODO Auto-generated method stub
		
	}
	
	protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(inputStream);
		registerBeanDefinitions(doc);
	}
	
	protected void registerBeanDefinitions(Document doc)
	{
		Element root = doc.getDocumentElement();
		parseBeanDefinitions(root);
	}

	protected void  parseBeanDefinitions(Element root)
	{
		NodeList nodes = root.getChildNodes();
		for (int i=0; i<nodes.getLength(); i++)
		{
			Node node = nodes.item(i);
			if (node instanceof Element)
			{
				Element ele = (Element) node;
				parseBeanDefinition(ele);
			}
		}
	}
	
	protected void parseBeanDefinition(Element ele)
	{
		String name = ele.getAttribute("id");
		String className = ele.getAttribute("class");
		BeanDefinition beanDefinition = new BeanDefinition();
		processProperty(ele, beanDefinition);
		beanDefinition.setClassName(className);
		getRegistry().put(name, beanDefinition);
		
	}
	
	protected void processProperty(Element ele, BeanDefinition beanDefinition)
	{
		NodeList propertyNode = ele.getElementsByTagName("property");
		for (int i=0; i<propertyNode.getLength(); i++)
		{
			Node node = propertyNode.item(i);
			if (node instanceof Element)
			{
				Element propertyEle = (Element) node;
				String name = propertyEle.getAttribute("name");
				String value = propertyEle.getAttribute("value");
				if (value != null && value.length()>0)
				{
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
				}
				else
				{
					String ref = propertyEle.getAttribute("ref");
					if (ref == null || ref.isEmpty())
					{
						throw new IllegalArgumentException("Configuration problem: <property> element for property '" + 
								name + "' must specify a ref or value");
					}
					BeanReference beanRef = new BeanReference(ref);
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanRef));
					
				}
			}
		}
	}
	
}

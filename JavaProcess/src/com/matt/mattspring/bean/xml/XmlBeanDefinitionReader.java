package com.matt.mattspring.bean.xml;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.matt.mattspring.BeanReference;
import com.matt.mattspring.bean.AbstractBeanDefinitionReader;
import com.matt.mattspring.bean.BeanDefinition;
import com.matt.mattspring.bean.PropertyValue;
import com.matt.mattspring.bean.io.ResourceLoader;


public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{
	
	public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
		super(resourceLoader);
	}

	@Override
	public void loadBeanDefinition(String location) throws Exception {
		InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
		doLoadBeanDefinitions(inputStream);
	}
	
	protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		Document doc = docBuilder.parse(inputStream);
		// ½âÎöbean
		registerBeanDefinitions(doc);
		inputStream.close();
	}
	
	public void registerBeanDefinitions(Document doc)
	{
		Element root = doc.getDocumentElement();
		parseBeanDefinitions(root);
	}
	
	protected void parseBeanDefinitions(Element root)
	{
		NodeList nodeList = root.getChildNodes();
		for (int i=0; i<nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			if (node instanceof Element)
			{
				Element ele = (Element) node;
				processBeanDefinition(ele);
			}
		}
	}
	
	protected void processBeanDefinition(Element ele)
	{
		String name = ele.getAttribute("id");
		String className = ele.getAttribute("class");
		
		BeanDefinition beanDefinition = new BeanDefinition();
		processProperty(ele, beanDefinition);
		beanDefinition.setBeanClassName(className);
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
				if (value != null && value.length() > 0)
				{
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
				}
				else
				{
					String ref = propertyEle.getAttribute("ref");
					if (ref == null || ref.length() == 0)
					{
						throw new IllegalArgumentException("Configuration problem: <property> element for property '"
								+ name + "' must specify a ref or value");
					}
					BeanReference beanReference = new BeanReference(ref);
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
					
				}
			}
		}
	}

}

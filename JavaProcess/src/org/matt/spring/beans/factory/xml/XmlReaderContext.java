package org.matt.spring.beans.factory.xml;

import org.matt.spring.core.io.Resource;

public class XmlReaderContext {

	private XmlBeanDefinitionReader reader;
	
	private Resource resource;
	

	public XmlReaderContext(XmlBeanDefinitionReader reader, Resource resource) {
		super();
		this.reader = reader;
		this.resource = resource;
	}

	public XmlBeanDefinitionReader getReader() {
		return reader;
	}

	public void setReader(XmlBeanDefinitionReader reader) {
		this.reader = reader;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	
	
}

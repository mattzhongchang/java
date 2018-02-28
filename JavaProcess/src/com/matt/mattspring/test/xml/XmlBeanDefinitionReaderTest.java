package com.matt.mattspring.test.xml;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.matt.mattspring.bean.BeanDefinition;
import com.matt.mattspring.bean.io.ResourceLoader;
import com.matt.mattspring.bean.xml.XmlBeanDefinitionReader;

public class XmlBeanDefinitionReaderTest {

	@Test
	public void test() throws Exception{
		XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
		xmlBeanDefinitionReader.loadBeanDefinition("resource.xml");
		Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
		Assert.assertTrue(registry.size() > 0);
	}
}

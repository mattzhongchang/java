package org.matt.spring.beans.factory.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.matt.spring.beans.factory.support.BeanDefinitionReaderUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DefaultBeanDefinitionDocumentReader {

	public static final String BEAN_ELEMENT = BeanDefinitionParserDelegate.BEAN_ELEMENT;
	
	public static final String NESTED_BEANS_ELEMENT = "beans";
	
	public static final String ALIAS_ELEMENT = "alias";
	
	public static final String NAME_ATTRIBUTE = "name";
	
	public static final String ALIAS_ATTRIBUTE = "alias";
	
	public static final String IMPORT_ELEMENT = "import";
	
	public static final String RESOURCE_ATTRIBUTE = "resource";
	
	public static final String PROFILE_ATTRIBUTE = "profile";
	
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private XmlReaderContext readerContext;
	
	private BeanDefinitionParserDelegate delegate;
	
	
	public XmlReaderContext getReaderContext() {
		return readerContext;
	}

	public void setReaderContext(XmlReaderContext readerContext) {
		this.readerContext = readerContext;
	}

	
	public void registerBeanDefinitions(Document doc, XmlReaderContext readerContext)
	{
		this.readerContext = readerContext;
		Element root = doc.getDocumentElement();
		doRegisterBeanDefinitions(root);
	}
	
	protected void doRegisterBeanDefinitions(Element root)
	{
		String profileSpec = root.getAttribute(PROFILE_ATTRIBUTE);
		
		BeanDefinitionParserDelegate parent = this.delegate;
		
		this.delegate = createDelegate(this.readerContext, root, parent);
		
		parseBeanDefinitions(root, this.delegate);
		
		this.delegate = parent;
		
	}
	
	protected BeanDefinitionParserDelegate createDelegate(XmlReaderContext readerContext, 
			Element ele, BeanDefinitionParserDelegate parentDelegate)
	{
		BeanDefinitionParserDelegate delegate = new BeanDefinitionParserDelegate();
		delegate.setReaderContext(readerContext);
		
		return delegate;
	}
	
	protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate)
	{
		if (delegate.isDefaultNamespace(root))
		{
			NodeList nl = root.getChildNodes();
			for (int i=0; i<nl.getLength(); i++)
			{
				Node node = nl.item(i);
				if (node instanceof Element)
				{
					Element ele = (Element) node;
					if (delegate.isDefaultNamespace(ele))
					{
						parseDefaultElement(ele, delegate);
					}
					else
					{
						//
					}
				}
			}
		}
	}
	
	
	private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate)
	{
		if (delegate.nodeNameEquals(ele, IMPORT_ELEMENT))
		{
			importBeanDefinitionResource(ele);
		}
		else if (delegate.nodeNameEquals(ele, ALIAS_ELEMENT))
		{
			processAliasRegistration(ele);
		}
		else if (delegate.nodeNameEquals(ele, BEAN_ELEMENT))
		{
			processBeanDefinition(ele, delegate);
		}
		else if (delegate.nodeNameEquals(ele, NESTED_BEANS_ELEMENT))
		{
			// 
		}
	}
	
	protected void importBeanDefinitionResource(Element ele)
	{
		
	}
	
	protected void processAliasRegistration(Element ele)
	{
		
	}

	protected void processBeanDefinition(Element ele, BeanDefinitionParserDelegate delegate)
	{
		BeanDefinitionHolder bdHolder = delegate.parseBeanDefinitionElement(ele);
		
		BeanDefinitionReaderUtils.registerBeanDefinition(bdHolder, getReaderContext().getReader().getRegistry());
	}
}

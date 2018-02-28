package org.matt.spring.beans.factory.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DefaultDocumentLoader {

	
	public Document loadDocument(InputSource inputSource) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(inputSource);
	}
	
}

package org.matt.spring.core.io;

import java.net.URL;

import org.castor.core.util.Assert;

public class DefaultResourceLoader implements ResourceLoader{

	
	
	@Override
	public Resource getResource(String location) {
		Assert.notNull(location, "Location must not be null");
		if (location.startsWith(CLASSPATH_URL_PREFIX))
		{
			return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
		}
		
		URL url;
		try {
//			url = new URL(location);
			url = this.getClass().getClassLoader().getResource(location);
			return new UrlResource(url);
		} catch (Exception e) {
			
			e.printStackTrace();
			return getResourceByPath(location);
		}
	}

	protected Resource getResourceByPath(String path)
	{
		return new ClassPathResource(path);
	}
	
}

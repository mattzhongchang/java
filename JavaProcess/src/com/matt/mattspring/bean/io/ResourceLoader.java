package com.matt.mattspring.bean.io;

import java.net.URL;

public class ResourceLoader {

	public Resource getResource(String location)
	{
		URL url = this.getClass().getClassLoader().getResource(location);
		return new UrlResource(url);
	}
	
}

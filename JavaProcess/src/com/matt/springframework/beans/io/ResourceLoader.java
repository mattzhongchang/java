package com.matt.springframework.beans.io;

import java.net.URL;

public class ResourceLoader {

	public Resource getResource(String location)
	{
		URL url = this.getClass().getClassLoader().getResource(location);
		URLResource urlResource = new URLResource(url);
		return urlResource;
	}
}

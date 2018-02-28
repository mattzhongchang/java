package org.matt.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

	private String path;
	
	public ClassPathResource(String path) {
		super();
		this.path = path;
	}

	
	@Override
	public InputStream getInputStream() throws IOException {
		return ClassLoader.getSystemResourceAsStream(this.path);
	}

}

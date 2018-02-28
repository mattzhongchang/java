package org.matt.spring.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class UrlResource implements Resource{
	
	private URI uri;
	
	private URL url;
	
	
	public UrlResource(URL url) {
		super();
		this.url = url;
	}


	@Override
	public InputStream getInputStream() throws IOException {
		URLConnection con = this.url.openConnection();
		con.connect();
		return con.getInputStream();
		
	}

	
}

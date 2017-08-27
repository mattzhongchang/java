package demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Test03 {

	public static void main(String[] args) {
		URL url = Test03.class.getClassLoader().getResource("");
		System.out.println(url.toString());
		
		InputStream in = Test03.class.getClassLoader().getResourceAsStream("config.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		prop.list(System.out);
	}
}

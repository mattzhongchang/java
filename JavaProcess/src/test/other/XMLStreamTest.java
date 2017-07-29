package test.other;

import com.thoughtworks.xstream.XStream;

public class XMLStreamTest {
    
	private String name;
	
	private int age;
	
	private String address;
	
	private String telnum;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelnum() {
		return telnum;
	}

	public void setTelnum(String telnum) {
		this.telnum = telnum;
	}
	
	public static void main(String[] args)
	{
		XStream xs = new XStream ();
		XMLStreamTest xml = new XMLStreamTest();
		xml.setName("÷”≥©");
		xml.setAge(18);
		xml.setAddress("Œ‰∫∫ –∫∫—Ù«¯¡˙—Ù¥Ûµ¿");
		xml.setTelnum("13453345656");
		
		String xmlStr = xs.toXML(xml);
		System.out.println(xmlStr);
	}
	
}

package demo;

import java.util.Vector;

public class Demo05 {

	public static void main(String[] args) {
		Vector<String> vector = new Vector<String>(100);
		for (int i=0; i<120; i++)
		{
			vector.add(i + "");
		}
		
		for (int i=0; i<85;i++)
		{
			String str = vector.remove(0);
			System.out.println(str + "==" + vector.size());
		}
		
		vector.add("adf");
		vector.add("ddd");
		vector.add("fff");
		
		
	}
}

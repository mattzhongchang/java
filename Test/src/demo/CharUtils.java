package demo;

public class CharUtils {

	public static boolean isAscii(char ch)
	{
		int i = (int) ch;
		if (i>-128 && i< 127)
		{
			return true;
		}
		return false;
	}
}

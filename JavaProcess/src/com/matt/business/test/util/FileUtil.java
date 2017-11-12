package com.matt.business.test.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileUtil {
	
	public static void main(String[] args) {
		String result = readFile("file/sql.log");
	}

	public static String readFile(String path)
	{
		StringBuilder builder = new StringBuilder("");
		File file = new File(path);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
			String line = "";
			while ((line = in.readLine()) != null)
			{
//				System.out.println(line);
				builder.append(line);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return builder.toString();
	}
}

package com.matt.business.test.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SplitUtil {

	public static void main(String[] args) {

		List<String> result = new ArrayList<String>();
		String sql = FileUtil.readFile("logs/test.log");
//		splitStr(result, sql, 1024, "GBK");
		
//		binarySplit(result, sql, 1024, "GBK");
		
		divisionSplit(result, new StringBuilder(sql), 1024, "GBK");
		
		for (String str : result)
		{
//			System.out.println(str);
		}
		
	}
	
	public static void divisionSplit(List<String> result, String sql, int maxLength, String charsetName)
	{
		Charset charset = Charset.forName(charsetName);
		byte[] bytes = sql.getBytes(charset);
		
		if (sql == null || "".equals(sql))
		{
			return;
		}
		
		if (bytes.length <= maxLength)
		{
			result.add(sql);
			return;
		}
		
		
		String preSql = sql;
		String nextSql = "";
		while (preSql.getBytes(charset).length > maxLength)
		{
			int num = preSql.getBytes(charset).length/maxLength + 1;
			String temp = preSql.substring(0, preSql.length()/num);
			nextSql = preSql.substring(preSql.length()/num) + nextSql;
			preSql = temp;
		}
		
		result.add(preSql);
		divisionSplit(result, nextSql, maxLength, charsetName);
		
	}
	
	public static void divisionSplit(List<String> result, StringBuilder sql, int maxLength, String charsetName)
	{
		Charset charset = Charset.forName(charsetName);
		if (sql == null || "".equals(sql))
		{
			return;
		}

		int charNum = sql.length();
		if (sql.length() > maxLength)
		{
			
		}
		
		byte[] bytes = sql.toString().getBytes(charset);
		if (bytes.length <= maxLength)
		{
			result.add(sql.toString());
			return;
		}
		
		StringBuilder preSql = sql;
		StringBuilder nextSql = new StringBuilder();
		
		while (bytes.length > maxLength)
		{
			int num = bytes.length/maxLength + 1;
			StringBuilder temp = preSql.replace(preSql.length()/num, preSql.length(), "");
			nextSql = preSql.replace(0, preSql.length()/num, "");
			preSql = temp;
			bytes = preSql.toString().getBytes(charset);
		}
		
		result.add(preSql.toString());
		divisionSplit(result, nextSql, maxLength, charsetName);
		
	}
	
	public static void binarySplit(List<String> result, String sql, int maxLength, String charsetName)
	{
		Charset charset = Charset.forName(charsetName);
		byte[] bytes = sql.getBytes(charset);
		
		if (sql == null || "".equals(sql))
		{
			return;
		}
		
		if (bytes.length <= maxLength)
		{
			result.add(sql);
			return;
		}
		
		String preSql = sql;
		String nextSql = "";
		while (preSql.getBytes(charset).length > maxLength)
		{
			String temp = preSql.substring(0, preSql.length()/2);
			nextSql = preSql.substring(preSql.length()/2) + nextSql;
			preSql = temp;
			
		}
		
		result.add(preSql);
		binarySplit(result, nextSql, maxLength, charsetName);
		
		
	}
	
	
	public static void splitStr(List<String> result, String sql, int maxLength, String charsetName)
	{
		Charset charset = Charset.forName(charsetName);
		byte[] bytes = sql.getBytes(charset);
		
		if (sql == null || "".equals(sql))
		{
			return;
		}
		
		if (bytes.length <= maxLength)
		{
			result.add(sql);
			return;
		}
		
		int num = bytes.length/maxLength + 1;
		int charNum = sql.length();
		
		int len = sql.length();
		String preSql = "";
		String nextSql = "";
		for (int i=len; i>=0; i--)
		{
			String subSql = sql.substring(0, i);
//			System.out.println(subSql.getBytes().length + "  " + i);
			if (subSql.getBytes().length < maxLength)
			{
				preSql = subSql;
				result.add(subSql);
				nextSql = sql.substring(i);
				break;
			}
		}
		
		splitStr(result, nextSql, maxLength, charsetName);
		
		
	}
	
	
	
}

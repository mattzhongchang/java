package com.matt.business.test;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.matt.business.dao.SqlTextDao;
import com.matt.business.model.SqlText;
import com.matt.business.test.util.FileUtil;
import com.matt.business.test.util.SplitUtil;

public class SqlTest {


	private static final String log4j = "config/log4j.properties";
	
	private static final String[] spring_config = new String[]{"ApplicationContext.xml", "yyadb.xml", "srpdb.xml"};
	
	public static void main(String[] args)
	{
		PropertyConfigurator.configure(log4j);
		Logger log = Logger.getLogger(UserController.class);
		ApplicationContext ctx = null;
		
		ctx = new ClassPathXmlApplicationContext(spring_config);
		log.debug("Spring loaded");
		SqlTextDao sqlTextDao = (SqlTextDao) ctx.getBean("sqlTextDao");
		
		String sql = FileUtil.readFile("logs/test.log");
		
		List<String> result = new ArrayList<String>();
		
		SplitUtil.divisionSplit(result, new StringBuilder(sql), 1024, "GBK");
		
		String qryId = "SQL_" + System.currentTimeMillis();
//		for (int i=0; i<result.size(); i++)
//		{
//			SqlText sqlText = new SqlText();
//			sqlText.setSqlId("SQL_" + System.currentTimeMillis()+i);
//			sqlText.setQryId(qryId);
//			sqlText.setSn(i + 1);
//			sqlText.setText(result.get(i));
//			
//			sqlTextDao.insertSqlText(sqlText);
//		}
		
		String str = "312) --> [main] org.springframework.beans.CachedIntrospectionResults.<init>(CachedIntrospectionResults.java:250): Found bean property 'plugins' of type [[Lorg.apache.ibatis.plugin.Interceptor;]  [DEBUG] 2017-07-18 06:29:31,408(312) --> [main] org.springframework.beans.CachedIntrospectionResults.<init>(CachedIntrospectionResults.java:250): Found bean property 'objectType' of type [java.lang.Class]  [DEBUG] 2017-07-18 06:29:31,408(312) --> [main] org.springframework.beans.CachedIntrospectionResults.<init>(CachedIntrospectionResults.java:250): Found bean property 'object' of type [java.lang.Object]  [DEBUG] 2017-07-18 06:29:31,408(312) --> [main] org.springframework.beans.CachedIntrospectionResults.<init>(CachedIntrospectionResults.java:250): Found bean property 'mapperLocations' of type [[Lorg.springframework.core.io.Resource;]  [DEBUG] 2017-07-18 06:29:31,408(312) --> [main] org.springframework.beans.CachedIntrospectionResults.<init>(CachedIntrospectionResults.java:250): Found bean property 'failFast' of type";
		Charset charset = Charset.forName("GBK");
		byte[] bytes = str.getBytes(charset);
		System.out.println("bytes");
		
		qryId = "SQL_ID";
		for (int i=0; i<10; i++)
		{
			str = str + i;
			bytes = str.getBytes(charset);
			System.out.println(str);
			System.out.println("bytes=" + bytes.length);
			SqlText sqlText = new SqlText();
			sqlText.setQryId(qryId);
			sqlText.setSn(i + 1);
			sqlText.setText(str);
			
			sqlTextDao.insertSqlText(sqlText);
		}
		
		
		
	}
}

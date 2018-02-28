package com.matt.common.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class DataSourceInfo {
	
	private static Logger logger = Logger.getLogger(DataSourceInfo.class);

	private BasicDataSource dataSource;
	
	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void printDataSource()
	{
		if (this.dataSource == null)
		{
			logger.debug("dataSource==null");
		}
		else
		{
			logger.debug("���ӳ��û�:" + this.dataSource.getUsername() + ",��ʼ��������" + this.dataSource.getInitialSize() + 
					",�����������" + this.dataSource.getMaxActive() + 
					",��������������" + this.dataSource.getMaxIdle() + ",��С������������" + this.dataSource.getMinIdle() + 
					",���ڼ�����������" + this.dataSource.getNumActive() + ",���ڿ�����������" + this.dataSource.getNumIdle());
		}
	}
	
}

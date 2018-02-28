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
			logger.debug("连接池用户:" + this.dataSource.getUsername() + ",初始连接数：" + this.dataSource.getInitialSize() + 
					",最大连接数：" + this.dataSource.getMaxActive() + 
					",最大空闲连接数：" + this.dataSource.getMaxIdle() + ",最小空闲连接数：" + this.dataSource.getMinIdle() + 
					",正在激活连接数：" + this.dataSource.getNumActive() + ",正在空闲连接数：" + this.dataSource.getNumIdle());
		}
	}
	
}

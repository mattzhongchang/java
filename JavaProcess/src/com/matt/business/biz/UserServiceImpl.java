package com.matt.business.biz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.matt.business.dao.UserDao;
import com.matt.business.model.User;
import com.matt.common.db.DataSourceKey;

@DataSourceKey(dataSourceKey="jdbcDataSource")
@Service("userServiceImpl")
public class UserServiceImpl implements UserService 
{
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
	private TransactionTemplate transactionTemplate;
	
	@Autowired
	private UserDao UserDao;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public void setDao(UserDao dao) {
		this.UserDao = dao;
	}
	
	public void addUsetTest()
	{
		System.out.println("test method.");
	}

	@DataSourceKey(dataSourceKey="jdbcDataSource")
	public void addUser(final User user)
	{
		transactionTemplate.execute(new TransactionCallback<Void>(){

			@Override
			public Void doInTransaction(TransactionStatus txStatus) {
				try
				{
					UserDao.addUser(user);
					if (true)
					{
						throw new RuntimeException("手动抛出异常");
					}
				}
				catch (Exception e)
				{
					txStatus.isRollbackOnly();
					log.error("addUser(User)异常：", e);
				}
				return null;
			}
			
		});
	}
	
	public int saveUser(User user)
	{
		int num = this.UserDao.addUser(user);
//		if (true)
//		{
//			throw new RuntimeException("saveUser RuntimeException:");
//		}
		return num;
	}
	

}

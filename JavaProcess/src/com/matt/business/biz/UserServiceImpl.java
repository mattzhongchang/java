package com.matt.business.biz;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.matt.business.dao.UserDao;
import com.matt.business.model.User;

public class UserServiceImpl implements UserService 
{
	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
	private TransactionTemplate transactionTemplate;
	
	private UserDao dao;

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	public void addUser(final User user)
	{
		transactionTemplate.execute(new TransactionCallback<Void>(){

			@Override
			public Void doInTransaction(TransactionStatus txStatus) {
				try
				{
					dao.addUser(user);
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
		this.dao.addUser(user);
		if (true)
		{
			throw new RuntimeException("saveUser RuntimeException:");
		}
		return 1;
	}
	

}

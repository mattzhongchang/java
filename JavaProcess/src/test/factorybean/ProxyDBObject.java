package test.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class ProxyDBObject implements FactoryBean<Object>{

	private String currentDB;
	
	public String getCurrentDB() {
		return currentDB;
	}

	public void setCurrentDB(String currentDB) {
		this.currentDB = currentDB;
	}

	@Override
	public Object getObject() throws Exception {
		if ("mysql".equals(this.currentDB))
		{
			return new MysqlDBOperator();
		}
		return new RedisDBOperator();
	}

	@Override
	public Class<?> getObjectType() {
		if ("mysql".equals(this.currentDB))
		{
			return MysqlDBOperator.class;
		}
		return RedisDBOperator.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}

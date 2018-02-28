package test.factorybean;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Client {

	
	private final static String SPRING_PATH = "config/factory-bean.xml";
	
	
	public static void main(String[] args) throws Exception
	{
		FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(SPRING_PATH);
		ProxyDBObject proxyDBObject = (ProxyDBObject) applicationContext.getBean("&proxyDBObject");
		DBOperator dbOperator = (DBOperator) proxyDBObject.getObject();
		MysqlDBEntity mysqlDBEntity = new MysqlDBEntity();
		mysqlDBEntity.setAttribute("mysql attribute");
		dbOperator.save(mysqlDBEntity);
		
		
		DBOperator dbOper = applicationContext.getBean("proxyDBObject", DBOperator.class);
		dbOper.update(mysqlDBEntity);
		
	}
}

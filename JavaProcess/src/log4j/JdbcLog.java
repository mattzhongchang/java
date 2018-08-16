package log4j;


import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class JdbcLog {

	private static final String log4j = "bin/log4j/log4j.properties";
	
	private static final String[] spring_config = new String[]{"ApplicationContext.xml", "yyadb.xml", "srpdb.xml"};
	
	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
	
	private static Logger logger = Logger.getLogger("shard_detail"); 
	
	public static void main(String[] args) throws ClassNotFoundException { 
		Class.forName("com.mysql.jdbc.Driver");
		PropertyConfigurator.configure(log4j);
		Logger log = Logger.getLogger(JdbcLog.class);
		ApplicationContext ctx = null;
		
		ctx = new ClassPathXmlApplicationContext(spring_config);

		
		long shard_id = System.currentTimeMillis();
		threadLocal.set(shard_id);
		
		
		MDC.put("sharding_id", shard_id);
		MDC.put("before_ds", "ds2s");
		MDC.put("after_ds", "ds3s");
		log.debug("Spring loaded");

		MDC.put("sharding_id", threadLocal.get());
		logger.info("232332323");
		logger.info("123");
		
		
		
	}
}

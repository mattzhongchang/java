package redis.pubsub;

import java.util.Date;
import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.pubsub.model.CssMessage;
import redis.pubsub.model.VisitorAndJob;

public class Demo01 {

	public static void main(String[] args) {
		String redisIp = "localhost";
		int redisPort = 6379;
		System.out.println(String.format("redis pool is starting, redis ip %s, redis port %d", redisIp, redisPort));
		

		System.out.println("=========================================");
		
		Jedis jedis = new Jedis("localhost");
		
		jedis.set("redisChatTest", "我是天才"); 
		jedis.expire("redisChatTest", 5);
		
		CssMessage cssMessage = new CssMessage();
		cssMessage.setId("sdfsfsfd2323");
		cssMessage.setVisitorId("kdsdfsdkiiddsfsSDFSFSFD1");
		cssMessage.setVisitorName("Cust_232339777");
		cssMessage.setScene("weibao");
		cssMessage.setChannel("APP");
		cssMessage.setSessionId("sfdsffdwerwijdfgdfjkgf");
		cssMessage.setSkillGroupId("120");
		cssMessage.setMessage("/<a href=\"http://7dx.pc6.com/wwb5/HiJson212.zip\" onmousedown=\"softCountNew(444619,638529)\">湖北电信下载</a>");
		
		VisitorAndJob bean = new VisitorAndJob();
		bean.setCreateDate(new Date());
		bean.setJobId("sdfsidsf");
		bean.setVisitorId("sdfsffdfd");
		bean.setCssMessage(cssMessage);
		
		jedis.set("job_id", "");
		
		
		jedis.hset("zhongchang", "sex", "男");
		jedis.hset("zhongchang", "birday", "2018");
		jedis.hset("zhongchang", "job", "123");
		
		Map<String, String> map = jedis.hgetAll("zhongchang");
		System.out.println("=========================================");
		System.out.println(map);

	
	}
}

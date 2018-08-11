package redis.pubsub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Publisher {

	private final JedisPool jedisPool;
	
	
	
	public Publisher(JedisPool jedisPool)
	{
		this.jedisPool = jedisPool;
	}
	
	public void start()
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Jedis jedis = this.jedisPool.getResource();
		while (true)
		{
			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!"quie".equals(line))
			{
				jedis.set(line, "新浪微博：小叶子一点也不逗");  
		        jedis.expire(line, 5);  		
//				jedis.expire("expire", 5);
				jedis.publish("mychannel", line);
			}
			else
			{
				break;
			}
		}
	}
	

}

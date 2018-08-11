package redis.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SubThread extends Thread{
	private final JedisPool jedisPool;
	
	private final Subscriber subscriber = new Subscriber();
	
	private final String channel = "mychannel";
	
	private static final String key_event = "__keyevent@0__:expired";
	
	public SubThread(JedisPool jedisPool)
	{
		super("subThread");
		this.jedisPool = jedisPool;
	}
	
	
	@Override
	public void run()
	{
		System.out.println(String.format("subscribe redis, channel %s, thread will be block", channel));
		Jedis jedis = null;
		try 
		{
			jedis = this.jedisPool.getResource();
			jedis.subscribe(this.subscriber, this.channel, key_event);
//			jedis.subscribe(this.subscriber, key_event);
		}
		catch (Exception e)
		{
			System.out.println(String.format("subsrcibe channel error, %s", e));
		}
		finally
		{
			if (jedis != null)
			{
				
			}
		}
		System.out.println("SubThread run...");
	}
	
	public static void main(String[] args) {
		String redisIp = "localhost";
		int redisPort = 6379;
		JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), redisIp, redisPort);
		SubThread subThread = new SubThread(jedisPool);
		subThread.start();
		
	}

}

package redis.pubsub.listener;

import redis.clients.jedis.Jedis;

public class JedisSubscriber {

	public static final String key_event = "__key*__:*";
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		jedis.subscribe(new KeyExpiredListener(), key_event);
	}
}

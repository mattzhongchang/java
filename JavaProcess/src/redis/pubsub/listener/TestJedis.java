package redis.pubsub.listener;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestJedis {

	public static void main(String[] args) {  
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");  
  
        Jedis jedis = pool.getResource();  
        jedis.set("notify", "����΢����СҶ��һ��Ҳ����");  
        jedis.expire("notify", 3);  
  
    }  
}

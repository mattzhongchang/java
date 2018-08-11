package redis.pubsub.listener;

import redis.clients.jedis.JedisPubSub;

public class KeyExpiredListener extends JedisPubSub {

	@Override  
    public void onPSubscribe(String pattern, int subscribedChannels) {  
        System.out.println("onPSubscribe "  
                + pattern + " " + subscribedChannels);  
    }  
  
    @Override  
    public void onPMessage(String pattern, String channel, String message) {  
  
        System.out.println("onPMessage pattern "  
                        + pattern + " " + channel + " " + message);  
    }

	@Override
	public void onMessage(String arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}  
}

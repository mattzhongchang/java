package redis.pubsub;

import redis.clients.jedis.JedisPubSub;

public class Subscriber extends JedisPubSub
{
	public Subscriber()
	{
		
	}
	

	@Override
	public void onMessage(String channel, String message) {
		System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
		
	}

	@Override
	public void onPMessage(String arg0, String arg1, String arg2) {
		System.out.println("onPMessage");
		
	}

	@Override
	public void onPSubscribe(String arg0, int arg1) {
		System.out.println("onPSubscribe");
		
	}

	@Override
	public void onPUnsubscribe(String arg0, int arg1) {
		System.out.println("onPUnsubscribe");
		
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannel %d", channel, subscribedChannels));
		
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d", channel, subscribedChannels));
		
	}

}

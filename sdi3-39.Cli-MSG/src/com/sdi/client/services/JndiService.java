package com.sdi.client.services;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * 
 * @author sdi3-39
 * 
 */
public class JndiService {

	public static final String JMS_CONNECTION_FACTORY 
		= "jms/RemoteConnectionFactory";
	public static final String CHAT_QUEUE 
		= "jms/queue/TripChatQueue";
	public static final String CHAT_TOPIC
		= "jms/topic/TripChatTopic";
	public static final String CHAT_CLIENT_QUEUE 
		= "jms/queue/TripChatClientQueue";
	private JndiService() {
	}

	public static Object find(String jndiKey) {
		Context ctx;
		try {
			ctx = new InitialContext();
			return ctx.lookup(jndiKey);

		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

}

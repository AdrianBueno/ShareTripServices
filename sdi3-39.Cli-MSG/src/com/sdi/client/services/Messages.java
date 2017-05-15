package com.sdi.client.services;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;

import com.sdi.infrastructure.util.Credentials;

public class Messages {

	private Messages() {
	}

	public static MapMessage createLoginMessage(Credentials c, Session s)
			throws JMSException {

		MapMessage msg = s.createMapMessage();
		msg.setString("COMMAND", "GET_TRIPS");
		msg.setString("LOGIN", c.getUser());
		msg.setString("PASS", c.getPass());
		return msg;
	}

	public static MapMessage createChatMessage(Credentials c, Session s,
			String chat, String tripId) throws JMSException {

		MapMessage msg = s.createMapMessage();
		msg.setString("COMMAND", "SEND_MESSAGE");
		msg.setString("LOGIN", c.getUser());
		msg.setString("PASS", c.getPass());
		msg.setString("TRIP_ID", tripId);
		msg.setString("CHAT", chat);

		return msg;
	}

}

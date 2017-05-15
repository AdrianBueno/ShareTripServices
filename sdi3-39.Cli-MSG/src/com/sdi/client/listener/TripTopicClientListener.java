package com.sdi.client.listener;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.logging.Logger;

import com.sdi.infrastructure.util.console.Console;

public class TripTopicClientListener implements MessageListener {

	private static final Logger log;
	static {
		log = Logger.getLogger(TripTopicClientListener.class);
	}
	
	private String tripId;
	
	@Override
	public void onMessage(Message msg) {
		log.info("Message Received.");
		try {
			process(msg);
		} catch (JMSException je) {
			log.info(je.getMessage());
		}
	}
	
	
	private void process(Message m) throws JMSException {
		if (!messageOfExpectedType(m)) {
			log.info("Not of expected type " + m);
			return;
		}
		MapMessage msg = (MapMessage) m;
		switch (msg.getString("COMMAND")) {
		case "chatresponse":
			processMsg(msg);
			break;
		case "sendMessage":
			break;
		default:
			break;
		}
	}
	
	private void processMsg(MapMessage map) throws JMSException{
		String tripId = map.getString("TRIP_ID");
		/* Si el viaje seleccionado por este usuario no es el que llega
		 * del topic lo rechazamos
		 */
		if(!tripId.equals(this.tripId))
			return;
		String chat = map.getString("CHAT");
		Console.println();
		Console.print(chat);
		Console.println();
	}
	
	
	private boolean messageOfExpectedType(Message msg) {
		return msg instanceof MapMessage;
	}


	public String getTripId() {
		return tripId;
	}


	public void setTripId(String tripId) {
		this.tripId = tripId;
	}
	
	

}

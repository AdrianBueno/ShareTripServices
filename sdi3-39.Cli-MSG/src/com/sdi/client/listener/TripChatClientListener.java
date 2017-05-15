package com.sdi.client.listener;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.logging.Logger;

import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.util.console.ModelPrinter;

public class TripChatClientListener implements MessageListener {

	private static final Logger log;
	static {
		log = Logger.getLogger(TripChatClientListener.class);
	}
	
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
		log.info(msg.getString("COMMAND"));
		switch (msg.getString("COMMAND")) {
		case "tripresponse":
			showTrips(msg);
			break;
		case "sendMessage":
			break;
		default:
			break;
		}
	}
	
	private void showTrips(MapMessage map) throws JMSException{
		log.info("Iniciando listado de trips");
		Long num = Long.parseLong(map.getString("TRIPS_SIZE"));
		
		for(Integer i = 0 ; i < num ; i++){
			Trip trip = new Trip();
			String data = map.getString(i.toString());
			if(!data.equals("")){
				trip.loadTrip(data);
				ModelPrinter.printTrip(trip);
			}
		}
	}
	
	
	private boolean messageOfExpectedType(Message msg) {
		return msg instanceof MapMessage;
	}

}

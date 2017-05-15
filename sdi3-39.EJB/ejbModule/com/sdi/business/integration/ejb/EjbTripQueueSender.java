package com.sdi.business.integration.ejb;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.integration.TripQeueSender;
import com.sdi.infrastructure.model.Trip;

@Stateless
public class EjbTripQueueSender implements TripQeueSender {
	private static final String USER = "sdi";
	private static final String PASS = "password";
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(EjbTripQueueSender.class);
	}

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;
	@Resource(mappedName = "java:/queue/TripChatClientQueue")
	private Destination queue;
	@Resource
	private SessionContext ctx;
	
	private Connection con;
	private Session session;
	private MessageProducer sender;
	
	public EjbTripQueueSender() {
		log.info("EjbTripQueueSender Instanciado");
	}
	@Override
	public void sendTripList(List<Trip> trips) {
		send(ctx.getCallerPrincipal(), trips);
	}

	private void send(Principal callerPrincipal, List<Trip> trips) {
		log.info("Intentando enviar lista de viajes");
		try {
			establishSession(USER, PASS); //Conexión y sesión
			MapMessage map = createMessage(trips); //Obtenemos mapa
			sender = session.createProducer(queue);
			sender.send(map);	//Enviamos mapa
			log.info("Lista de viajes enviada");
		} catch (JMSException jex) {
			jex.printStackTrace();
		} finally {
			close(con);
		}
	}
	
	/*
	 * Establecemos la sessión y la conexión a partir de un usuario
	 */
	private void establishSession(String user, String pass) throws JMSException{
		con = factory.createConnection(user, pass);
		session = con.createSession(false,
						Session.AUTO_ACKNOWLEDGE);
	}


	private MapMessage createMessage(List<Trip> trips) throws JMSException{
		MapMessage map = session.createMapMessage();
		
		Integer num = 0;
		for(Trip trip : trips){
			StringBuilder str = new StringBuilder();
			str.append(trip.getId().toString());
			str.append(":");
			str.append(trip.getStatus().toString());
			str.append(":");
			str.append(trip.getDepartureAddress().getCity());
			str.append(":");
			str.append(trip.getDestinationAddress().getCity());
			map.setString(num.toString(), str.toString());
			num++;			
		}

		map.setString("COMMAND", "tripresponse");
		map.setString("TRIPS_SIZE", num.toString());
		return map;
	}
	
	
	private void close(Connection con) {
		try {
			con.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

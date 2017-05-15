package com.sdi.business.integration.ejb;

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

import com.sdi.business.integration.MessageTopicPublisher;

@Stateless
public class EjbMessageTopicPublisher implements MessageTopicPublisher {
	private static final String USER = "sdi";
	private static final String PASS = "password";
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(EjbTripQueueSender.class);
	}

	@Resource(mappedName = "java:/ConnectionFactory")
	private ConnectionFactory factory;
	@Resource(mappedName = "java:/topic/TripChatTopic")
	private Destination queue;
	@Resource
	private SessionContext ctx;

	private Connection con;
	private Session session;
	private MessageProducer sender;

	public EjbMessageTopicPublisher() {
		log.info("EjbTripQueue Instanciado");
	}

	@Override
	public void publishMessage(MapMessage map) {
		send(map);
	}

	private void send(MapMessage map) {
		log.info("Intentando divulgar la los mensajes del chat");
		try {
			establishSession(USER, PASS); // Conexión y sesión
			MapMessage newMap = session.createMapMessage();
			newMap.setString("COMMAND", "chatresponse");
			newMap.setString("TRIP_ID", map.getString("TRIP_ID"));
			newMap.setString("CHAT", map.getString("CHAT"));
			sender = session.createProducer(queue);
			sender.send(newMap); // Enviamos mapa
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
	private void establishSession(String user, String pass) throws JMSException {
		con = factory.createConnection(user, pass);
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	private void close(Connection con) {
		try {
			con.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}

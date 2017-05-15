package com.sdi.business.integration.ejb;

import java.security.Principal;

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

import com.sdi.business.integration.LogQueueSender;

@Stateless
public class EjbLogQueueSender implements LogQueueSender {
	private static final String USER = "sdi";
	private static final String PASS = "password";
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(EjbLogQueueSender.class);
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

	public EjbLogQueueSender() {
		log.info("EjbLogQueueSender Instanciado");
	}

	@Override
	public void sendLogMessage(String userLogin, String tripId, String message) {
		send(ctx.getCallerPrincipal(), userLogin, tripId, message);
	}

	private void send(Principal callerPrincipal, String userLogin,
			String tripId, String message) {
		log.info("Intentando enviar mensaje ilegal a cola de log.");
		try {
			establishSession(USER, PASS); // Conexión y sesión
			MapMessage map = createMessage(userLogin, tripId, message); // Obtenemos
																		// mapa
			sender = session.createProducer(queue);
			sender.send(map); // Enviamos mapa
			log.info("Mensaje ilegal enviado a cola de log.");
		} catch (JMSException jex) {
			jex.printStackTrace();
		} finally {
			close(con);
		}

	}

	private MapMessage createMessage(String userLogin, String tripId,
			String message) throws JMSException {
		MapMessage map = session.createMapMessage();

		map.setString("USER", userLogin);
		map.setString("ILLEGAL_TRIP", tripId);
		map.setString("ILLEGAL_MSG", message);
		
		return map;
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

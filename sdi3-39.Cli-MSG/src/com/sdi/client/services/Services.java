package com.sdi.client.services;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.sdi.client.listener.TripChatClientListener;
import com.sdi.client.listener.TripTopicClientListener;
import com.sdi.infrastructure.util.console.Console;
/**
 * En esta clase mantengo objetos de sesión.
 * @author sdi3-39
 *
 */
public class Services {
	
	
	private ConnectionFactory factory;
	private Destination chatQueue;
	private Destination chatClientQueue;
	private Connection con;
	private Session session;
	private MessageProducer sender;
	private MessageConsumer consumerChatQueue;
	private MessageConsumer consumerTopic;
	
	
	private TripTopicClientListener topicListener;
	private TripChatClientListener chatClientListener;
	
	
	private Services(){}
	
	public static Services getInstance(){
		Services ss = new Services();
		ss.initialize();
		return ss;
	}
	
	
	public void sendChatQueue(MapMessage map) throws JMSException{
		if(sender != null)
			sender.send(map);
	}
	
	public void start(){
		try {
			con.start();
		} catch (JMSException e) {
			Console.print("Error al abrir la conexión");
		}
	}
	
	public void close(){
		try {
			con.close();
		} catch (JMSException e) {
			Console.print("Error al cerrar la conexión");
		}
	}
	
	
	
	
	public Session getSession() {
		return session;
	}

	private  void initialize() {
		factory = (ConnectionFactory) 
				JndiService.find(JndiService.JMS_CONNECTION_FACTORY);
		chatQueue = (Destination) 
				JndiService.find(JndiService.CHAT_QUEUE);
		chatClientQueue = (Destination) 
				JndiService.find(JndiService.CHAT_CLIENT_QUEUE);
		try {
			createConnection();
			createSession();
			createProducesAndConsumers();
		} catch (JMSException e) {
			Console.print("Hubo un problema al inicializar el servicio");
		}		
	}
	
	private void createConnection() throws JMSException{
		con = factory.createConnection("sdi", "password");	
	}
	
	private void createSession() throws JMSException{
		session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
	}
	
	private void createProducesAndConsumers() throws JMSException{
		sender = session.createProducer(chatQueue);
		
		consumerChatQueue = session.createConsumer(chatClientQueue);
		chatClientListener = new TripChatClientListener();
		consumerChatQueue.setMessageListener(chatClientListener);
		
		consumerTopic = session.createConsumer(chatClientQueue);
		topicListener = new TripTopicClientListener();
		consumerTopic.setMessageListener(topicListener);
	}

	public TripTopicClientListener getTopicListener() {
		return topicListener;
	}

	public TripChatClientListener getChatClientListener() {
		return chatClientListener;
	}
	
	
}

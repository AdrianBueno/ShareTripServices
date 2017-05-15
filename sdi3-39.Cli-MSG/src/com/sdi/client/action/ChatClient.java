package com.sdi.client.action;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import com.sdi.client.services.Messages;
import com.sdi.client.services.Services;
import com.sdi.infrastructure.util.Credentials;
import com.sdi.infrastructure.util.console.Console;

/**
 * Esta clase hace el paso de mensajes al servidor
 * y tiene el bucle para escribir en el chat.
 * @author sdi3-39
 *
 */
public class ChatClient {

	private Services s;
	private Credentials credentials;
	private Long id;

	public ChatClient(Services s) {
		this.s = s;
	}

	public void execute() throws JMSException {
		s.start(); // Abrimos conexión
		String login = Console.readString("Login: ");
		String password = Console.readString("Password: ");
		credentials = new Credentials(login, password);
		MapMessage map = Messages.createLoginMessage(credentials,
				s.getSession());
		s.sendChatQueue(map);
		id = getId();
		s.getTopicListener().setTripId(id.toString());
		while (true) {
			String chat = Console.readString("Escribe: \n");
			map = Messages.createChatMessage(credentials, s.getSession(), chat,
					id.toString());
			s.sendChatQueue(map);
		}
		// s.close(); //Cerramos conexión
		// Console.print("Conexión cerrada");
	}

	protected Long getId() {
		Long id;
		Console.print("Introduce un número válido y positivo.");
		do {
			Console.println("Viaje id (El listado de mensajes puede tardar): ");
			id = Console.readLong("ID: ");
		} while (id == null || id < 1);

		return id;
	}
	

}

package com.sdi.client.actions;

import java.util.List;

import org.jboss.logging.Logger;

import com.sdi.business.UsersService;
import com.sdi.business.impl.locator.RemoteEjbServicesLocator;
import com.sdi.infrastructure.model.User;
import com.sdi.menu.Action;

public class RemoteListUsers implements Action {
	private static final Logger log;
	private static UsersService us;
	static {

		log = Logger.getLogger(RemoteListUsers.class);
	}

	@Override
	public void execute() throws Exception {
		us = new RemoteEjbServicesLocator().getUsersService();
		log.info("Intentando listar usuarios");
		List<User> users = us.listUsers();
		String listado = createList(users);
		System.out.println(listado);
		log.info("Usuarios listados.");

	}

	private String createList(List<User> users) {
		StringBuilder str = new StringBuilder();
		str.append("Lista de usuarios del sistema");
		str.append("\n");
		str.append("------------------------------------\n");
		for (User user : users) {
			str.append("Login: ");
			str.append(user.getLogin() + " Id: "+user.getId()+"\n");
			str.append("Nombre: ");
			str.append(user.getName() + " ");
			str.append(user.getEmail() + "\n");
			str.append("Viajes Promovidos: ");
			str.append(user.getTrips().size() + "\n");
			str.append("Viajes participados: ");
			str.append(user.getSeats().size() + "\n");
			str.append("\n");
			str.append("___________________________\n");

		}
		str.append("\n");
		str.append("------------------------------------");
		str.append("Usuarios listados");
		return str.toString();
	}
}

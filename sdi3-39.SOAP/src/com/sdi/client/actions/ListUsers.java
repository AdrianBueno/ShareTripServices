package com.sdi.client.actions;

import java.util.List;
import java.util.logging.Logger;

import com.sdi.menu.Action;
import com.sdi.ws.ApplicationException_Exception;
import com.sdi.ws.EjbUsersServiceService;
import com.sdi.ws.User;
import com.sdi.ws.UsersService;



public class ListUsers implements Action {
	private static final Logger log;
	private static UsersService us;
	static {

		log = Logger.getAnonymousLogger();
	}

	@Override
	public void execute() throws Exception {
		try{
			us = new EjbUsersServiceService().getUsersServicePort();
			log.info("Intentando listar usuarios");
			List<User> users = us.listUsers();
			String listado = createList(users);
			System.out.println(listado);
			log.info("Usuarios listados.");
		}catch(RuntimeException e){
			System.err.println("Error al listar usuarios");
			throw e;
		}
	}

	private String createList(List<User> users) throws ApplicationException_Exception {
		StringBuilder str = new StringBuilder();
		str.append("Lista de usuarios del sistema");
		str.append("\n");
		str.append("------------------------------------\n");
		for (User user : users) {
			Integer trips = us.getNumberOfTrips(user.getId());
			Integer seats = us.getNumberOfSeats(user.getId());
			str.append("Login: ");
			str.append(user.getLogin() + " Id: "+user.getId()+"\n");
			str.append("Nombre: ");
			str.append(user.getName() + " ");
			str.append(user.getEmail() + "\n");
			str.append("Viajes Promovidos: ");
			str.append(trips + "\n");
			str.append("Viajes participados: ");
			str.append(seats + "\n");
			str.append("\n");
			str.append("___________________________\n");
		}
		str.append("\n");
		str.append("------------------------------------");
		str.append("Usuarios listados");
		return str.toString();
	}
}

package com.sdi.client.actions;

import java.util.logging.Logger;

import com.sdi.console.Console;
import com.sdi.menu.Action;
import com.sdi.ws.EjbUsersServiceService;
import com.sdi.ws.UsersService;

public class DisableUser implements Action {
	private static final Logger log;
	private static UsersService us;

	protected static final int EXIT = 0;

	static {
		
		log = Logger.getAnonymousLogger();
	}

	@Override
	public void execute() throws Exception {
		log.info("Iniciando desactivado de usuario");
		us = new EjbUsersServiceService().getUsersServicePort();
		Long userId = getUserId();
		us.disableUser(userId);
		Console.println("Usuario desactivado");
	}
	protected Long getUserId() {
		Long userId;

		do {
			Console.print("Disable User Id: ");
			userId = Console.readLong();

		} while (userId == null || userId < 1);

		return userId;
	}
}

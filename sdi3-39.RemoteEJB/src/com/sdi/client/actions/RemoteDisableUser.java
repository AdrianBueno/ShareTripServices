package com.sdi.client.actions;

import org.jboss.logging.Logger;

import com.sdi.business.UsersService;
import com.sdi.business.impl.locator.RemoteEjbServicesLocator;
import com.sdi.console.Console;
import com.sdi.menu.Action;

public class RemoteDisableUser implements Action {
	private static final Logger log;
	private static UsersService us;

	protected static final int EXIT = 0;

	static {
		log = Logger.getLogger(RemoteDisableUser.class);
	}

	@Override
	public void execute() throws Exception {
		log.info("Iniciando cliente de deshabilitación de usuarios");
		us = new RemoteEjbServicesLocator().getUsersService();
		Long userId = getUserId();
		us.DisableUser(userId);
		log.info("Se ha desactivado un usuario");
		Console.print("Usuario desactivado");

	}
	protected Long getUserId() {
		Long userId;
		Console.print("Introduce un número válido y positivo.");
		do {
			Console.print("Disable User Id: ");
			userId = Console.readLong();
		} while (userId == null || userId < 1);

		return userId;
	}
}

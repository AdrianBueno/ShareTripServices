package com.sdi.client.actions;

import org.jboss.logging.Logger;

import com.sdi.business.AdminService;
import com.sdi.business.impl.locator.RemoteEjbServicesLocator;
import com.sdi.console.Console;
import com.sdi.menu.Action;

public class RemoteHardReset implements Action {

	private static final Logger log;
	private static AdminService as;
	static {
		log = Logger.getLogger(RemoteHardReset.class);
	}	
	
	@Override
	public void execute() throws Exception {
			log.info("Iniciando cliente para resetear base de datos.");
			as = new RemoteEjbServicesLocator().getAdminService();
			as.resetDataBase();
			Console.print("La base de datos ha sido reiniciada.");
			Console.print("Se han aplicado los valores por defecto");
	}

}

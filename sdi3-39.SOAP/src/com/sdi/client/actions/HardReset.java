package com.sdi.client.actions;

import java.util.logging.Logger;

import com.sdi.menu.Action;
import com.sdi.ws.AdminService;
import com.sdi.ws.EjbAdminServiceService;

public class HardReset implements Action {

	private static final Logger log;
	private static AdminService as;

	protected static final int EXIT = 0;

	static {
		log = Logger.getAnonymousLogger();
	}	
	
	@Override
	public void execute() throws Exception {
		try{
			log.info("Iniciando reseteo base de datos");
			as = new EjbAdminServiceService().getAdminServicePort();
			as.resetDataBase();
		}catch(RuntimeException e){
			System.err.println("Cascó");
			System.err.println(e.getMessage());
		}
	}

}

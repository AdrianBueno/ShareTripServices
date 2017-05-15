package com.sdi.client;

import com.sdi.client.action.AcceptApply;
import com.sdi.client.action.ListSeats;
import com.sdi.client.action.ListTrips;
import com.sdi.client.action.LoginUser;
import com.sdi.client.infrastructure.exceptions.BusinessException;
import com.sdi.console.Console;
import com.sdi.menu.BaseMenu;

public class Main extends BaseMenu {

	public Main() {
		menuOptions = new Object[][] {
				{ "Listar viajes del usuario", ListTrips.class },
				{"Listar solicitudes por viaje", ListSeats.class},
				{"Acceptar pasajero", AcceptApply.class}};
	}

	public static void main(String[] args) {
		new Main().execute();
	}
	@Override
	public void execute() {
		int opt = 0;
		do {
			try{
				if(opt == 0)
					new LoginUser().execute();
				else
					processOption(opt);
				
			}catch(BusinessException e){
				Console.println(e.getMessage());
			}catch(RuntimeException rte){
				Console.println(rte.getMessage());
				System.err.println("Runtime");
				
			}catch(Exception be){
				Console.println(be.getMessage());
				System.err.println("Undef");
			}
			showMenu();
			opt = getMenuOption();
		} while (opt != EXIT);
	}


	
	
}

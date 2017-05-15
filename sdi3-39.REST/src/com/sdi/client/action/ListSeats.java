package com.sdi.client.action;

import java.util.List;

import com.sdi.client.infrastructure.faces.UsersServiceRest;
import com.sdi.client.infrastructure.model.User;
import com.sdi.client.infrastructure.services.RestService;
import com.sdi.client.infrastructure.services.SessionService;
import com.sdi.console.Console;
import com.sdi.menu.Action;

public class ListSeats implements Action {

	@Override
	public void execute() throws Exception {
		UsersServiceRest usr = RestService.getUsr();
		Long id = Console.readLong("Id de Viaje: ");
		List<User> applicants = usr.listApplicants(id);
		if(applicants == null)
			Console.println("No se han obtenido pasajeros para ese asiento");
		else
			listApplicants(applicants);
		SessionService.setTripId(id); //gurdamos id seleccionado.
		Console.println("Seleccionado Trip: "+ id.toString());
	}
	
	private void listApplicants(List<User> applicants){
		Console.println();
		for (User user : applicants) {
			Console.print("Pasajero ID: "+user.getId().toString()+"\n");
			Console.print("Nombre: "+user.getName()+ " "
					+user.getSurname()+"\n");
			Console.print("Email: "+user.getEmail()+"\n");
			Console.println("--------------------------------------------");
		}
		
	}

}

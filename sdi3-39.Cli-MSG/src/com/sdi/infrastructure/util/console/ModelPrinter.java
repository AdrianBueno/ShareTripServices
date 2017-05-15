package com.sdi.infrastructure.util.console;

import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;

public class ModelPrinter extends Printer {
	
	private ModelPrinter(){}
	
	public static void printTrip(Trip trip){
		println("");
		print("Id: ");
		print(trip.getId().toString()+"\n");
		print(trip.getDepartureAddress().getCity()
				+ " -> " + trip.getDestinationAddress().getCity()+"\n");
		print("\nStatus: "+trip.getStatus());
		println("\n");
	}
	
	public static void printUser(User user){
		StringBuilder str = new StringBuilder();
		str.append("\n");
		str.append("Login: ");
		str.append(user.getLogin() + " Id: "+user.getId()+"\n");
		str.append("Nombre: ");
		str.append(user.getName() + " ");
		str.append(user.getEmail() + "\n");
		str.append("\n");
		print(str.toString());
	}

}

package com.sdi.client.action;

import java.util.List;

import com.sdi.client.infrastructure.faces.TripsServiceRest;
import com.sdi.client.infrastructure.model.Trip;
import com.sdi.client.infrastructure.model.User;
import com.sdi.client.infrastructure.model.types.TripStatus;
import com.sdi.client.infrastructure.services.RestService;
import com.sdi.client.infrastructure.services.SessionService;
import com.sdi.console.Console;
import com.sdi.menu.Action;

public class ListTrips implements Action {

	@Override
	public void execute() throws Exception {
		TripsServiceRest tsr = RestService.getTsr();
		User user = SessionService.getUser();
		List<Trip> trips = tsr
				.listTripsUserAndStatus(user.getId(), TripStatus.OPEN);
		if(trips == null)
			Console.print("No se ha podido obtener ningún Viaje del servidor");
		else
			printTrips(trips);
	}
	
	private void printTrips(List<Trip> trips){
		for(Trip trip : trips){
			Console.print("Id: ");
			Console.print(trip.getId().toString()+"\n");
			Console.print(trip.getDepartureAddress().getCity()
					+ " -> " + trip.getDestinationAddress().getCity()+"\n");
			Console.print(trip.getDepartureDate() 
					+ " -> "+ trip.getArrivalDate()+"\n");
			Console.print("Fecha cierre: ");
			Console.print(trip.getClosingDate()+"\n");
			Console.print("Plazas disponibles: ");
			Console.print(trip.getAvailablePax()+"\n");
			Console.print("Plazas máximas: ");
			Console.print(trip.getMaxPax() +"\n");
			Console.println("-----------------------------------------------");
			Console.println();
		}
	}

}

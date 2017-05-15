package com.sdi.client.action;

import com.sdi.client.infrastructure.faces.AppliesServiceRest;
import com.sdi.client.infrastructure.model.Seat;
import com.sdi.client.infrastructure.services.RestService;
import com.sdi.client.infrastructure.services.SessionService;
import com.sdi.console.Console;
import com.sdi.menu.Action;

public class AcceptApply implements Action {

	@Override
	public void execute() throws Exception {
		AppliesServiceRest asr = RestService.getAsr();
		Long tripId = SessionService.getTripId();
		Console.println("Seleccionado Trip: "+ tripId.toString());
		Long userId = Console.readLong("Id de pasajero: ");
		Seat seat = asr.acceptApply(tripId, userId);
		if(seat == null)
			Console.println("No se puede aceptar a un pasajero ya aceptado");
		else
			Console.println("Pasajaero aceptado");
	}

}

package com.sdi.client.actions;

import java.util.logging.Logger;

import com.sdi.console.Console;
import com.sdi.menu.Action;
import com.sdi.ws.EjbRatingsServiceService;
import com.sdi.ws.RatingsService;

public class DeleteRatings implements Action {
	private static final Logger log;
	private static RatingsService rs;

	protected static final int EXIT = 0;

	static {
		log = Logger.getAnonymousLogger();
	}

	@Override
	public void execute() throws Exception {
		log.info("Iniciando cliente de borrado de valoraciones");
		rs = new EjbRatingsServiceService().getRatingsServicePort();	
		Long ratingId = getRatingId();
		rs.removeRating(ratingId);
		log.info("Se ha eliminado una valoración");
		Console.print("Valoración eliminada.");
	}

	protected Long getRatingId() {
		Long tripId;
		do {
			Console.print("Rating id: ");
			tripId = Console.readLong();

		} while (tripId == null || tripId < 1);

		return tripId;
	}

}

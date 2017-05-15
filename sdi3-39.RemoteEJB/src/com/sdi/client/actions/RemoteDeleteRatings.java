package com.sdi.client.actions;

import org.jboss.logging.Logger;

import com.sdi.business.RatingsService;
import com.sdi.business.impl.locator.RemoteEjbServicesLocator;
import com.sdi.console.Console;
import com.sdi.menu.Action;

public class RemoteDeleteRatings implements Action {
	private static final Logger log;
	private static RatingsService rs;

	protected static final int EXIT = 0;

	static {
		log = Logger.getLogger(RemoteListLastMonthRatings.class);
	}

	@Override
	public void execute() throws Exception {
		log.info("Iniciando cliente de borrado de valoraciones");
		rs = new RemoteEjbServicesLocator().getRatingService();	
		Long ratingId = getRatingId();
		rs.removeRating(ratingId);
		log.info("Se ha eliminado una valoración");
		Console.print("Valoración eliminada.");

	}

	protected Long getRatingId() {
		Long id;

		do {
			Console.print("Rating id: ");
			id = Console.readLong();
		} while (id == null || id < 1);

		return id;
	}

}

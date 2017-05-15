package com.sdi.client.actions;

import java.util.List;

import org.jboss.logging.Logger;

import com.sdi.business.RatingsService;
import com.sdi.business.impl.locator.RemoteEjbServicesLocator;
import com.sdi.console.Console;
import com.sdi.infrastructure.model.Rating;
import com.sdi.menu.Action;

public class RemoteListLastMonthRatings implements Action {
	private static final Logger log;
	private static RatingsService rs;
	
	static {
		log = Logger.getLogger(RemoteListLastMonthRatings.class);
	}

	@Override
	public void execute() throws Exception {
		log.info("Iniciando cliente de listado de valoraciones");
		rs = new RemoteEjbServicesLocator().getRatingService();
		List<Rating> ratings = rs.listLastMonthRatings();
		String listado = createList(ratings);
		Console.print(listado);
		log.info("Valoraciones listadas.");

	}

	private String createList(List<Rating> ratings) {
		StringBuilder str = new StringBuilder();
		str.append("Lista de ratings del último mes");
		str.append("\n");
		str.append("------------------------------------" + "\n");
		for (Rating rating : ratings) {
			str.append("RatingId: ");
			str.append(rating.getId()).append("\n");
			str.append("Destino: ");
			str.append(rating.getAboutSeat().getTrip().getDestinationAddress().getAddress()).append("\n");
			str.append("Usuario valorado: ");
			str.append(rating.getAboutSeat().getUser().getLogin()).append("\n");
			str.append("Usuario origen: ");
			str.append(rating.getFromSeat().getUser().getLogin()).append("\n");
			str.append("Valoración: ");
			str.append(rating.getValue()).append("\n");
			str.append("Comentarios: ");
			str.append(rating.getComment()).append("\n");
			str.append("___________________________").append("\n").append("\n");

		}
		str.append("\n");
		str.append("------------------------------------");
		str.append("Usuarios listados");
		return str.toString();
	}

}

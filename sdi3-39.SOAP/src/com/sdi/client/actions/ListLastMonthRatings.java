package com.sdi.client.actions;

import java.util.List;
import java.util.logging.Logger;

import com.sdi.console.Console;
import com.sdi.menu.Action;
import com.sdi.ws.BusinessException_Exception;
import com.sdi.ws.EjbRatingsServiceService;
import com.sdi.ws.Rating;
import com.sdi.ws.RatingsService;
import com.sdi.ws.Trip;
import com.sdi.ws.User;

public class ListLastMonthRatings implements Action {
	private static final Logger log;
	private static RatingsService rs;
	
	static {
		log = Logger.getAnonymousLogger();
	}

	@Override
	public void execute() throws Exception {
		log.info("Iniciando cliente de listado de valoraciones");
		rs = new EjbRatingsServiceService().getRatingsServicePort();
		List<Rating> ratings = rs.listLastMonthRatings();
		String listado = createList(ratings);
		Console.print(listado);
		log.info("Valoraciones listadas.");
	}

	private String createList(List<Rating> ratings) throws BusinessException_Exception {
		StringBuilder str = new StringBuilder();
		str.append("Lista de ratings del último mes");
		str.append("\n");
		str.append("------------------------------------" + "\n");
		for (Rating rating : ratings) {
			User userFrom = rs.getUserFrom(rating.getId());
			User userAbout = rs.getUserAbout(rating.getId());
			Trip trip = rs.getTripAbout(rating.getId());
			str.append("RatingId: ");
			str.append(rating.getId()).append(" ");
			str.append("Destino: ");
			str.append(trip.getDestinationAddress().getAddress()).append("\n");
			str.append("Usuario valorado: ");
			str.append(userAbout.getLogin()).append("\n");
			str.append("Usuario origen: ");
			str.append(userFrom.getLogin()).append("\n");
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

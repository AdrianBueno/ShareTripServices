package com.sdi.persistence.finder;

import java.util.List;

import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.persistence.Jpa;
/**
 * Esta clase se encarga de acceder a los datos de asientos de la base de datos
 * @author sdi-39
 * @version 1 15/06
 */
public class SeatFinder {
	
	
	private SeatFinder(){}
	
	/**
	 * Se encarga de obtener un asiento determinado
	 * @param trip fuente
	 * @param user fuente
	 * @return Seat la plaza relazionada con los parámetros.
	 */
	public static Seat getSeat(Trip trip, User user){
		List<Seat> list = Jpa.getManager()
				.createNamedQuery("Seat.findSeat",Seat.class)
				.setParameter(1, trip)
				.setParameter(2,user).getResultList();
				return list.size() > 0 ? list.get(0) : null;
		
	}
	/**
	 * Lista de plazas
	 * @return Lista de plazas
	 */
	public static List<Seat> listSeast(){
		return (List<Seat>) Jpa.getManager()
				.createNamedQuery("Seat.findAll",Seat.class).getResultList();	
	}
	
	/**
	 * Lista de plazas aceptadas de un viaje
	 * @return Lista de plazas
	 */
	public static List<Seat> listSeastAccepted(Trip trip){
		return (List<Seat>) Jpa.getManager()
				.createNamedQuery("Seat.findAccepted",Seat.class)
				.setParameter(1, trip).getResultList();	
	}
	
	
	
}

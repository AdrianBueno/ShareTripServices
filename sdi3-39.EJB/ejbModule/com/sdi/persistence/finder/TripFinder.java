package com.sdi.persistence.finder;

import java.util.List;

import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.model.types.TripStatus;
import com.sdi.persistence.Jpa;

/**
 * Se encarga de obtener los viajes en diferentes cojuntos de la 
 * base de datos.
 * @author sdi2-39
 */
public class TripFinder {

	private TripFinder() {}

	/**
	 * Obtiene una lista de viajes descrita por el nombre del método.
	 * @return List<Trip> Lista de viajes
	 */
	public static List<Trip> listTrips(){
		return Jpa.getManager()
				.createNamedQuery("Trip.findAll",Trip.class)
				.getResultList();
	}
	/**
	 * Obtiene una lista de viajes descrita por el nombre del método.
	 * @param UserModel Los viajes de este usuario no se obtendran
	 * @return List<Trip> Lista de viajes
	 */
	public static List<Trip> listAvailableTrips(User user){
		return Jpa.getManager()
				.createNamedQuery("Trip.findAvailable",Trip.class)
				.setParameter(1, user)
				.getResultList();
	}
	/**
	 * Obtiene una lista de viajes descrita por el nombre del método.
	 * @return List<Trip> Lista de viajes
	 */
	public static List<Trip> listAllAvailableTrips(){
		return Jpa.getManager()
				.createNamedQuery("Trip.findAllAvailable",Trip.class)
				.getResultList();
	}
	/**
	 * Obtiene una lista de viajes descrita por el nombre del método.
	 * @param TripStatus los valores correspondientes en la lista serán iguales
	 * @return List<Trip> Lista de viajes
	 */
	public static List<Trip> listTripsByStatus(TripStatus status){
		return Jpa.getManager()
				.createNamedQuery("Trip.findByStatus",Trip.class)
				.setParameter(1, status)
				.getResultList();
	}
	/**
	 * Obtiene una lista de viajes descrita por el nombre del método.
	 * @param UserModel los viajes serán de este usuario.
	 * @return List<Trip> Lista de viajes
	 */
	public static List<Trip> listTripsByPromoter(User user){
		return Jpa.getManager()
				.createNamedQuery("Trip.findByPromoter",Trip.class)
				.setParameter(1, user)
				.getResultList();
	}
	/**
	 * Obtiene una lista de viajes descrita por el nombre del método.
	 * @param UserModel peticiones a viajes de este usuario
	 * @return List<Trip> Lista de viajes
	 */
	public static List<Trip> listApplyTrips(User user){
		return Jpa.getManager()
				.createNamedQuery("Trip.findApply",Trip.class)
				.setParameter(1, user)
				.getResultList();
	}
	
	/**
	 * Obtiene una lista de viajes descrita por el nombre del método.
	 * @param UserModel viajes hechos por este usuario
	 * @return List<Trip> Lista de viajes
	 */
	public static List<Trip> listTripsByUserAndStatus(User user,TripStatus st) {
		return Jpa.getManager()
				.createNamedQuery("Trip.findByUserAndStatus",Trip.class)
				.setParameter(1, user)
				.setParameter(2, st)
				.getResultList();
	}
	
	
	/**
	 * Obtiene un trip de un usuario con la misma fecha de llegada
	 * @param trip con la fecha
	 * @param user usuario al que promotor
	 * @return Trip
	 */
	public static Trip getEqualTrip(Trip trip, User user){
		List<Trip> list = (List<Trip>) Jpa.getManager()
				.createNamedQuery("Trip.findEqual",Trip.class)
				.setParameter(1, user)
				.setParameter(2, trip.getArrivalDate())
				.getResultList();
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * Obtiene una lista de viajes en los que el usuario está aceptado.
	 * 
	 * @param user el usuario
	 * @return List<Trip> Lista de viajes
	 */
	public static List<Trip> listAcceptedByUser(User user) {
		return Jpa.getManager()
				.createNamedQuery("Trip.findAccepted",Trip.class)
				.setParameter(1, user)
				.getResultList();
	}

}

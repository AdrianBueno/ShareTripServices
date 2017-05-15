package com.sdi.business;

import java.util.List;

import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.types.TripStatus;

public interface TripsService {

	/**
	 * ACtualiza un viaje
	 * 
	 * @param trip
	 *            fuente
	 * @return List<User> lista de usuarios
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	public Trip updateTrip(Trip trip) throws BusinessException;

	/**
	 * Registra un nuevo viaje
	 * 
	 * @param trip
	 *            fuente
	 * @param user
	 *            fuente
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	public Trip registerTrip(Trip trip, Long user) throws BusinessException;

	/**
	 * Cancela un viaje existente
	 * 
	 * @param trip
	 *            fuente
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	public Trip cancelTrip(Long trip) throws BusinessException;

	/**
	 * Lista todos los viajes
	 * 
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	public List<Trip> listTrips() throws BusinessException;

	/**
	 * Lista todos los viajes por estado
	 * 
	 * @param status
	 *            fuente
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	public List<Trip> listTripsByStatus(TripStatus status)
			throws BusinessException;

	/**
	 * Lista todos los viajes disponislbes para un usuario. Esto implica todos
	 * los viajes a los que pueda solicitar plaza lo que excluye los propios.
	 * 
	 * @param user
	 *            fuente
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	public List<Trip> listAvailablesTrips(Long user) throws BusinessException;

	/**
	 * Lista los viajes creados por un usuario
	 * 
	 * @param user
	 *            fuente
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	public List<Trip> listTripsByPromoter(Long user) throws BusinessException;

	/**
	 * Lista los viajes que dónde un usuario ha solicitado plaza.
	 * 
	 * @param user
	 *            fuente
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	public List<Trip> listApplyTrips(Long user) throws BusinessException;

	/**
	 * Lista los viajes hechos por un usuario
	 * 
	 * @param user
	 *            fuente
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	public List<Trip> listDoneTrips(Long user) throws BusinessException;

	/**
	 * Lista todos los viajes de un usuario en los que está involucrado
	 * 
	 * @param user
	 *            el usuario
	 * @return Lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	public List<Trip> listInvolvedTrips(Long user) throws BusinessException;

	/**
	 * Lista viajes por usuario y estado
	 * 
	 * @param user
	 *            el usuario
	 * @param status
	 *            el estado
	 * @return Lista con los viajes
	 * @throws BusinessException
	 *             si no cumple los asserts
	 */
	public List<Trip> listTripsByUserAndStatus(Long user, TripStatus status)
			throws BusinessException;

	/**
	 * Lista viajes en los que el usuario está aceptado.
	 * 
	 * @param user
	 *            el usuario
	 * @return Lista con los viajes
	 * @throws BusinessException
	 *             si no cumple los asserts
	 */
	public List<Trip> listAcceptedAndPromotedByUser(Long user)
			throws BusinessException;

	/**
	 * Indica si un usuario es un participante (promotor o asiento aceptado) de
	 * un viaje determinado.
	 * 
	 * @param trip Viaje
	 * @param user Usuario
	 * @return valor que indica la pertenencia
	 * @throws BusinessException si no cumple los asserts
	 */
	public boolean isUserParticipant(Long trip, Long user)
			throws BusinessException;

}

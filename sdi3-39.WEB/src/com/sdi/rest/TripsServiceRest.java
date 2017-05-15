package com.sdi.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.types.TripStatus;

@Path("/TripsServiceRs")
public interface TripsServiceRest {

	/**
	 * Actualiza un viaje
	 * 
	 * @param trip
	 *            fuente
	 * @return List<User> lista de usuarios
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
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
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Trip registerTrip(Trip trip, Long user) throws Exception;

	/**
	 * Cancela un viaje existente
	 * 
	 * @param trip
	 *            fuente
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Trip cancelTrip(@PathParam("id") Long trip) throws Exception;

	/**
	 * Lista todos los viajes
	 * 
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Trip> listTrips() throws Exception;

	/**
	 * Lista todos los viajes por estado
	 * 
	 * @param status
	 *            fuente
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@GET
	@Path("/listByStatus/{status}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Trip> listTripsByStatus(@PathParam("status") TripStatus status)
			throws Exception;

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
	@GET
	@Path("/listAvailables/{user}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Trip> listAvailablesTrips(@PathParam("user") Long user)
			throws Exception;

	/**
	 * Lista los viajes creados por un usuario
	 * 
	 * @param user
	 *            fuente
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@GET
	@Path("/listByPromoter/{user}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Trip> listTripsByPromoter(@PathParam("user") Long user)
			throws Exception;

	/**
	 * Lista los viajes que dónde un usuario ha solicitado plaza.
	 * 
	 * @param user
	 *            fuente
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@GET
	@Path("/listApplied/{user}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Trip> listApplyTrips(@PathParam("user") Long user)
			throws Exception;

	/**
	 * Lista los viajes hechos por un usuario
	 * 
	 * @param user
	 *            fuente
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@GET
	@Path("/listDone/{user}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Trip> listDoneTrips(@PathParam("user") Long user)
			throws Exception;
	
	
	@GET
	@Path("/listUserAndStatus/{user}/{status}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Trip> listTripsUserAndStatus(@PathParam("user") Long user, 
			@PathParam("status") TripStatus status)
			throws Exception;
	
	@GET
	@Path("/listInvolved/{user}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Trip> listInvolvedTrips(@PathParam("user") Long user)
			throws Exception;

}

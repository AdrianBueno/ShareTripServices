package com.sdi.client.infrastructure.faces;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.client.infrastructure.exceptions.BusinessException;
import com.sdi.client.infrastructure.model.Seat;
import com.sdi.client.infrastructure.model.types.TravelStatus;

@Path("/SeatsServiceRs")
public interface SeatsServiceRest {

	/**
	 * Lista los asientos de un viaje
	 * 
	 * @param trip
	 *            viaje fuente
	 * @return List<Seat> lista de plazas
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@GET
	@Path("/listSeatsFromTrip/{trip}/{status}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Seat> listSeatsFromTrip(@PathParam("trip") Long trip,
			@PathParam("status") TravelStatus status) throws BusinessException;

	/**
	 * Lista los asientos de un usuario
	 * 
	 * @param user
	 *            usuario fuente
	 * @return List<Seat> lista de plazas
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@GET
	@Path("/listSeatsFromUser/{user}/{status}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Seat> listSeatsFromUser(@PathParam("user") Long user,
			@PathParam("status") TravelStatus status) throws BusinessException;
}

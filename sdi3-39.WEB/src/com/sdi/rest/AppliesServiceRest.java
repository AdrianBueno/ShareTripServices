package com.sdi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Application;
import com.sdi.infrastructure.model.Seat;


@Path("/AppliesServiceRs")
public interface AppliesServiceRest {

	/**
	 * Realiza una solicitud a un viaje
	 * @param trip el viaje sobre el que se hace la petición
	 * @param user el usuario qeu hace la petición
	 * @return Application la petición creada y registrada
	 * @throws BusinessException Si no cumple los asserts
	 */
	@PUT
	@Path("/new/{trip}/{user}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Application newApply(@PathParam("trip") Long trip,
			@PathParam("user") Long user) throws BusinessException;
	/**
	 * Acepta una solicitud de un viaje
	 * @param trip el viaje sobre el que se hace la petición
	 * @param user el usuario qeu hace la petición
	 * @return Seat la plaza aceptada
	 * @throws BusinessException Si no cumple los asserts
	 */
	@POST
	@Path("/accept/{trip}/{user}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Seat acceptApply(@PathParam("trip") Long trip,
			@PathParam("user") Long user) throws BusinessException;
	/**
	 * Cancela una plaza o petición de un viaje.
	 * @param trip el viaje sobre el que se hace la petición
	 * @param user el usuario qeu hace la petición
	 * @return Seat la plaza aceptada
	 * @throws BusinessException Si no cumple los asserts
	 */
	@DELETE
	@Path("/cancel/{trip}/{user}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Seat cancelApply(@PathParam("trip") Long trip,
			@PathParam("user") Long user) throws BusinessException;
	/**
	 * Excluye un asiento
	 * @param trip del asiento
	 * @param user del asiento
	 * @return Seat excluido
	 * @throws BusinessException si no se puede realizar
	 */
	@POST
	@Path("/exclude/{trip}/{user}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Seat excludeSeat(@PathParam("trip") Long trip,
			@PathParam("user") Long user) throws BusinessException;
	
	
}

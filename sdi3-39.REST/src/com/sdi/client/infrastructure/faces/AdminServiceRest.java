package com.sdi.client.infrastructure.faces;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.sdi.client.infrastructure.exceptions.BusinessException;


@Path("/AdminServiceRs")
public interface AdminServiceRest {
	
	/**
	 * Mantenimietno, resetea base de datos
	 * @throws BusinessException Si no cumple los asserts
	 */
	@GET
	@Path("/resetDataBase")
	public void resetDataBase() throws BusinessException;

}

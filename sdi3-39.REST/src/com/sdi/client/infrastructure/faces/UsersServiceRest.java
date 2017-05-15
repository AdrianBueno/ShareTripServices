package com.sdi.client.infrastructure.faces;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sdi.client.infrastructure.exceptions.ApplicationException;
import com.sdi.client.infrastructure.exceptions.BusinessException;
import com.sdi.client.infrastructure.model.User;

@Path("/UsersServiceRs")
public interface UsersServiceRest {

	/**
	 * Verifica el login y la contraseña de un usuario
	 * 
	 * @param login
	 *            propiedad "login" de un usuario
	 * @param pass
	 *            La contraseña de la cuenta de usuario
	 * @return User el usuario correspondiente con los parametros
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@GET
	@Path("/login/{user}/{password}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User loginUser(@PathParam("user") String login,
			@PathParam("password") String pass) throws BusinessException;

	/**
	 * Registra a un usuario en la base de datos
	 * 
	 * @param user
	 *            el usuario creado para registrarse
	 * @return User el usuario registrado
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User RegisterUser(User user) throws BusinessException;

	/**
	 * Deshabilita un usuario previamente activo
	 * 
	 * @param userId
	 *            el id del usuario existente
	 * @return Retorna el propio usuario desactivado
	 * @throws BusinessException
	 *             Si el usuario no existe o si está inactivo ya
	 */
	@POST
	@Path("{user}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User DisableUser(@PathParam("user") Long userId)
			throws BusinessException;

	/**
	 * Lista los usuarios que han solicitado plaza en un viaje.
	 * 
	 * @param trip
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@GET
	@Path("/listApplicants/{trip}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<User> listApplicants(@PathParam("trip") Long trip)
			throws BusinessException;

	/**
	 * Lista todos los usuarios del sistema.
	 * 
	 * @return List<USer> lista de usuarios
	 * @throws BusinessException
	 *             Si no cumple los asserts
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<User> listUsers() throws BusinessException;

	/**
	 * Obtiene el número de viajes de un usuario
	 * 
	 * @param userId
	 *            usuario dado
	 * @return int numéro de viajes
	 * @throws ApplicationException
	 */
	@GET
	@Path("/numberOfTrips/{user}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Integer getNumberOfTrips(@PathParam("user") Long userId)
			throws ApplicationException;

	/**
	 * Obtiene el número de viajes en los que a participado
	 * 
	 * @param userId
	 *            el usuario dado
	 * @return int número de viajes
	 */
	@GET
	@Path("/numberOfSeats/{user}")
	@Produces({ MediaType.TEXT_PLAIN })
	public Integer getNumberOfSeats(@PathParam("user") Long userId)
			throws ApplicationException;

}

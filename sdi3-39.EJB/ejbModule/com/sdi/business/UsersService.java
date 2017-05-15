package com.sdi.business;

import java.util.List;

import com.sdi.infrastructure.exception.ApplicationException;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.User;

public interface UsersService {
	
	/**
	 * Verifica el login y la contraseña de un usuario
	 * @param login propiedad "login" de un usuario
	 * @param pass La contraseña de la cuenta de usuario
	 * @return User el usuario correspondiente con los parametros
	 * @throws BusinessException Si no cumple los asserts
	 */
	public User loginUser(String login, String pass) throws BusinessException;
	/**
	 * Registra a un usuario en la base de datos
	 * @param user el usuario creado para registrarse
	 * @return User el usuario registrado 
	 * @throws BusinessException Si no cumple los asserts
	 */
	public User RegisterUser(User user) throws BusinessException;
	
	/**
	 * Deshabilita un usuario previamente activo
	 * @param userId el id del usuario existente
	 * @return Retorna el propio usuario desactivado
	 * @throws BusinessException Si el usuario no existe o si está inactivo ya
	 */
	public User DisableUser(Long userId) throws BusinessException;
	
	/**
	 * Lista los usuarios que han solicitado plaza en un viaje.
	 * @param trip
	 * @return List<Trip> lista de viajes
	 * @throws BusinessException Si no cumple los asserts
	 */
	public List<User> listApplicants(Long trip) throws BusinessException;
	
	/**
	 * Lista de usuarios en estado pendiente de un viaje
	 * @param trip el viaje
	 * @return lista de usuarios
	 * @throws BusinessException si no cumple los asserts
	 */
	public List<User> listPendingApplicants(Long trip) throws BusinessException;
	
	/**
	 * Lista todos los usuarios del sistema.
	 * @return List<USer> lista de usuarios
	 * @throws BusinessException Si no cumple los asserts
	 */
	public List<User> listUsers() throws BusinessException;
	
	/**
	 * Obtiene el número de viajes de un usuario
	 * @param userId usuario dado
	 * @return int numéro de viajes
	 * @throws ApplicationException 
	 */
	public Integer getNumberOfTrips(Long userId) throws ApplicationException;
	/**
	 * Obtiene el número de viajes en los que a participado
	 * @param userId el usuario dado
	 * @return int número de viajes
	 */
	public Integer getNumberOfSeats(Long userId) throws ApplicationException;

}

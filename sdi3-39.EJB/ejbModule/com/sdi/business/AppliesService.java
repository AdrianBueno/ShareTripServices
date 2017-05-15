package com.sdi.business;

import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Application;
import com.sdi.infrastructure.model.Seat;

public interface AppliesService {
	
	/**
	 * Realiza una solicitud a un viaje
	 * @param trip el viaje sobre el que se hace la petición
	 * @param user el usuario qeu hace la petición
	 * @return Application la petición creada y registrada
	 * @throws BusinessException Si no cumple los asserts
	 */
	public Application newApply(Long trip, Long user) throws BusinessException;
	/**
	 * Acepta una solicitud de un viaje
	 * @param trip el viaje sobre el que se hace la petición
	 * @param user el usuario qeu hace la petición
	 * @return Seat la plaza aceptada
	 * @throws BusinessException Si no cumple los asserts
	 */
	public Seat acceptApply(Long trip, Long user) throws BusinessException;
	/**
	 * Cancela una plaza o petición de un viaje.
	 * @param trip el viaje sobre el que se hace la petición
	 * @param user el usuario qeu hace la petición
	 * @return Seat la plaza aceptada
	 * @throws BusinessException Si no cumple los asserts
	 */
	public Seat cancelApply(Long trip, Long user) throws BusinessException;
	/**
	 * Excluye un asiento
	 * @param trip del asiento
	 * @param user del asiento
	 * @return Seat excluido
	 * @throws BusinessException si no se puede realizar
	 */
	public Seat excludeSeat(Long trip, Long user) throws BusinessException;
}

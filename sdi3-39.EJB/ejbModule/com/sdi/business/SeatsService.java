package com.sdi.business;

import java.util.List;

import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.types.TravelStatus;


public interface SeatsService {

	
	/**
	 * Lista los asientos de un viaje
	 * @param trip viaje fuente
	 * @return List<Seat> lista de plazas
	 * @throws BusinessException Si no cumple los asserts
	 */
	public List<Seat> listSeatsFromTrip(Long trip, TravelStatus status) 
			throws BusinessException;
	/**
	 * Lista los asientos de un usuario
	 * @param user usuario fuente
	 * @return List<Seat> lista de plazas
	 * @throws BusinessException Si no cumple los asserts
	 */
	public List<Seat> listSeatsFromUser(Long user,TravelStatus status) 
			throws BusinessException;
}

package com.sdi.rest.impl;

import java.util.List;

import com.sdi.business.SeatsService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.types.TravelStatus;
import com.sdi.rest.SeatsServiceRest;

public class SeatsServiceRestImpl implements SeatsServiceRest {

	SeatsService service = Factories.services.getSeatsService();

	@Override
	public List<Seat> listSeatsFromTrip(Long trip, TravelStatus status)
			throws BusinessException {
		try{return service.listSeatsFromTrip(trip, status);}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<Seat> listSeatsFromUser(Long user, TravelStatus status)
			throws BusinessException {
		try{return service.listSeatsFromUser(user, status);}
		catch(BusinessException e){return null;}
	}

}

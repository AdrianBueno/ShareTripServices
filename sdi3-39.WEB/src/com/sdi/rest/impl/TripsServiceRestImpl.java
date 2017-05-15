package com.sdi.rest.impl;

import java.util.List;

import com.sdi.business.TripsService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.types.TripStatus;
import com.sdi.rest.TripsServiceRest;

public class TripsServiceRestImpl implements TripsServiceRest {

	TripsService service = Factories.services.getTripsService();

	@Override
	public Trip updateTrip(Trip trip) throws BusinessException {
		try{return service.updateTrip(trip);}
		catch(BusinessException e){return null;}
	}

	@Override
	public Trip registerTrip(Trip trip, Long user) throws BusinessException {
		try{return service.registerTrip(trip, user);}
		catch(BusinessException e){return null;}
	}

	@Override
	public Trip cancelTrip(Long trip) throws BusinessException {
		try{return service.cancelTrip(trip);}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<Trip> listTrips() throws BusinessException {
		try{return service.listTrips();}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<Trip> listTripsByStatus(TripStatus status)
			throws BusinessException {
		try{return service.listTripsByStatus(status);}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<Trip> listAvailablesTrips(Long user) throws BusinessException {
		try{return service.listAvailablesTrips(user);}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<Trip> listTripsByPromoter(Long user) throws BusinessException {
		try{return service.listTripsByPromoter(user);}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<Trip> listApplyTrips(Long user) throws BusinessException {
		try{return service.listApplyTrips(user);}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<Trip> listDoneTrips(Long user) throws BusinessException {
		try{return service.listDoneTrips(user);}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<Trip> listInvolvedTrips(Long user) throws BusinessException {
		try{return service.listInvolvedTrips(user);}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<Trip> listTripsUserAndStatus(Long user, TripStatus status)
			throws Exception {
		try{return service.listTripsByUserAndStatus(user, status);}
		catch(BusinessException e){return null;}
	}

}

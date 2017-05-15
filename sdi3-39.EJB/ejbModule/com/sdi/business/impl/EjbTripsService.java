package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.commands.CommandExecutor;
import com.sdi.business.impl.commands.trips.CancelTrip;
import com.sdi.business.impl.commands.trips.IsUserParticipant;
import com.sdi.business.impl.commands.trips.ListAcceptedAndPromotedTrips;
import com.sdi.business.impl.commands.trips.ListApplyTrips;
import com.sdi.business.impl.commands.trips.ListAvailablesTrips;
import com.sdi.business.impl.commands.trips.ListInvolvedTrips;
import com.sdi.business.impl.commands.trips.ListPromoterTrips;
import com.sdi.business.impl.commands.trips.ListTrips;
import com.sdi.business.impl.commands.trips.ListTripsByUserAndStatus;
import com.sdi.business.impl.commands.trips.RegisterTrip;
import com.sdi.business.impl.commands.trips.UpdateTrip;
import com.sdi.business.impl.face.local.LocalTripsService;
import com.sdi.business.impl.face.remote.RemoteTripsService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.types.TripStatus;

@Stateless
@WebService(name = "TripsService")
public class EjbTripsService implements RemoteTripsService, LocalTripsService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> listTrips() throws BusinessException {
		return (List<Trip>) CommandExecutor.execute(new ListTrips(null));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> listTripsByStatus(TripStatus status) 
			throws BusinessException {
		return (List<Trip>) CommandExecutor.execute(new ListTrips(status));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> listAvailablesTrips(Long u) throws BusinessException {
		return (List<Trip>) CommandExecutor.execute(new ListAvailablesTrips(u));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> listTripsByPromoter(Long u) throws BusinessException {
		return (List<Trip>) CommandExecutor.execute(new ListPromoterTrips(u));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> listApplyTrips(Long user) throws BusinessException {
		return (List<Trip>) CommandExecutor.execute(new ListApplyTrips(user));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> listDoneTrips(Long user) throws BusinessException {
		return (List<Trip>) 
				CommandExecutor
				.execute(new ListTripsByUserAndStatus(user, TripStatus.DONE));
	}

	@Override
	public Trip updateTrip(Trip trip) throws BusinessException {
		return (Trip) CommandExecutor.execute(new UpdateTrip(trip));
	}

	@Override
	public Trip registerTrip(Trip trip, Long user) throws BusinessException {
		return (Trip) CommandExecutor.execute(new RegisterTrip(trip, user));
	}

	@Override
	public Trip cancelTrip(Long trip) throws BusinessException {
		return (Trip) CommandExecutor.execute(new CancelTrip(trip));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Trip> listInvolvedTrips(Long user) throws BusinessException {
		return (List<Trip>) 
				CommandExecutor
				.execute(new ListInvolvedTrips(user));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Trip> listTripsByUserAndStatus(Long user, TripStatus status)
		throws BusinessException{
		return (List<Trip>) 
				CommandExecutor
				.execute(new ListTripsByUserAndStatus(user,status));
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Trip> listAcceptedAndPromotedByUser(Long user)
			throws BusinessException {
		return (List<Trip>) CommandExecutor.execute(new ListAcceptedAndPromotedTrips(user));
	}
	
	@Override
	public boolean isUserParticipant(Long trip, Long user)
			throws BusinessException {
		return (boolean) CommandExecutor.execute(new IsUserParticipant(trip, user));
	}
	
	

	
}

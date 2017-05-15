package com.sdi.business.impl.commands.trips;

import java.util.Date;
import java.util.List;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.SeatFinder;
import com.sdi.persistence.finder.TripFinder;
/**
 * Lista los viajes en los que el usuario está involucrado ocmo
 * participante o promotor
 * @author sdi3-39
 *
 */
public class ListInvolvedTrips implements Command {

	private Long userId;

	public ListInvolvedTrips(Long userId) {
		this.userId = userId;
	}
	
	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(userId);
		User user = Jpa.getManager().find(User.class, userId);
		ApplicationAsserts.assertEntityExist(user);
		List<Trip> myTrips = TripFinder.listTripsByPromoter(user);
		
		List<Trip> applyTrips = TripFinder.listApplyTrips(user);
		for(Trip trip : applyTrips){
			Seat seat = SeatFinder.getSeat(trip, user);
			defineState(trip, seat);	
		}
		mergeTrips(myTrips, applyTrips);
		return myTrips;
	}
	
	private void mergeTrips(List<Trip> myTrips, List<Trip> applyTrips){
		for(Trip trip : myTrips){
			trip.setApplyState("Promotor");
			if(applyTrips.contains(trip))
				applyTrips.remove(trip);
		}
		myTrips.addAll(applyTrips);
	}
	/*
	 * Precalculo un campo para la capa web y evitar acceder al revés 
	 * en el modelo o hacer muchas busquedas desde arriba
	 */
	private void defineState(Trip trip, Seat seat){
		if(seat != null)
			trip.setApplyState(seat.getStatus().toString());
		else{
			if(trip.getClosingDate().before(new Date()))
				trip.setApplyState("WITH OUT SEAT");
			else
				trip.setApplyState("PENDING");
		}
	}
	
	
	
	

	
	

}

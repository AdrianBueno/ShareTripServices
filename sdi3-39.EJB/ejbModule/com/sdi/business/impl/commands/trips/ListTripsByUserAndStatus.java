package com.sdi.business.impl.commands.trips;

import java.util.List;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.ApplicationException;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.model.types.TripStatus;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.SeatFinder;
import com.sdi.persistence.finder.TripFinder;

public class ListTripsByUserAndStatus implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(ListTripsByUserAndStatus.class);
	}
	
	private Long userId;
	private TripStatus status;

	public ListTripsByUserAndStatus(Long userId, TripStatus status){
		this.userId = userId;
		this.status = status;
		log.info(Msg.getStr("info.instanced"));
	}
	
	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(userId);
		ApplicationAsserts.assertValueNotNull(status);
		User user = Jpa.getManager().find(User.class, userId);
		ApplicationAsserts.assertEntityExist(user);
		List<Trip> list =  TripFinder.listTripsByUserAndStatus(user, status);
		aggregateStatus(user, list);
		if(list != null)
			log.info("Número de viajes hechos: "+list.size());
		else
			log.info("No hay viajes hechos");
		return list;
	}
	
	private void aggregateStatus(User user, List<Trip> list) throws ApplicationException{
		for(Trip trip : list){
			Seat seat = SeatFinder.getSeat(trip, user);
			ApplicationAsserts.assertEntityExist(seat);
			trip.setApplyState(seat.getStatus().toString());
		}
	}
}

package com.sdi.business.impl.commands.trips;

import java.util.ArrayList;
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
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.SeatFinder;
import com.sdi.persistence.finder.TripFinder;
/**
 * Esta clase encapsula la lógica de aplicación encargada de encontrar todos
 * los viajes disponibles, es decir susceptibles de solicitar una plaza.
 * @author sdi2-39
 * @version 3 15/06
 */
public class ListAvailablesTrips implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(ListAvailablesTrips.class);
	}

	private Long userId;
	
	public ListAvailablesTrips(Long userId) {
		this.userId = userId;
		log.info(Msg.getStr("info.instanced"));
	}
	
	@Override
	public Object execute() throws BusinessException {
		List<Trip> list = checkTrips();
		if(userId != null){
			User user = Jpa.getManager().find(User.class, userId);
			ApplicationAsserts.assertEntityExist(user);
			list = TripFinder.listAvailableTrips(user);
			aggregateStatus(user, list);
			return list;
		}
		return list;
	}
	
	private List<Trip> checkTrips(){
		List<Trip> list = TripFinder.listAllAvailableTrips();
		if(list == null)
			return new ArrayList<Trip>();
		for(Trip trip : list)
			trip.checkTrip();
		return list;
	}
	
	private void aggregateStatus(User user, List<Trip> list) throws ApplicationException{
		for(Trip trip : list){
			Seat seat = SeatFinder.getSeat(trip, user);
			if(seat != null)
				trip.setApplyState(seat.getStatus().toString());
		}
	}

}

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
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.SeatFinder;
import com.sdi.persistence.finder.TripFinder;
/**
 * Esta clase se encarga de manejar la lógica de aplicación que controla las 
 * solicitudes de viajes realizadas (Viajes solicitados).
 * por un usuario.
 * @author sdi2-39
 * @version 2 05/04
 */
public class ListApplyTrips implements Command {
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(ListApplyTrips.class);
	}
	private Long userId;
	
	public ListApplyTrips(Long userId) {
		this.userId = userId;
		log.info(Msg.getStr("info.instanced"));
	}
	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(userId);
		User user = Jpa.getManager().find(User.class, userId);
		ApplicationAsserts.assertEntityExist(user);
		List<Trip> list =  TripFinder.listApplyTrips(user);
		//Aquí precalculo el dato de estado de asiento
		//en un transient, para saber el estado en realación al viaje.
		aggregateStatus(user, list);
		log.info("Se han listado viajes solicitados");
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

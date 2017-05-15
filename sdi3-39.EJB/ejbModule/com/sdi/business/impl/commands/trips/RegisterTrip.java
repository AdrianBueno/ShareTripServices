package com.sdi.business.impl.commands.trips;



import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.TripFinder;
/**
 * Esta clase encapsula la lógica de aplicación encargada de registrar
 * un viaje nuevo propiedad de un usuario.
 * @author sdi2-39
 * @version 2 06/04
 */
public class RegisterTrip implements Command {
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(RegisterTrip.class);
	}
	
	private Trip trip;
	private Long userId;

	public RegisterTrip(Trip trip, Long userId) {
		this.trip = trip;
		this.userId = userId;
		log.info(Msg.getStr("info.instanced"));
	}

	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(userId); //Ids no nulos
		ApplicationAsserts.assertValueNotNull(trip);
		ApplicationAsserts.assertAlreadyTripExist(trip.getId());
		User user = Jpa.getManager().find(User.class, userId);
		Trip tripEqual = TripFinder.getEqualTrip(trip, user);
		ApplicationAsserts.assertAlreadyTripExist(tripEqual); //No debe existir
		ApplicationAsserts.assertEntityExist(user); //Debe existir
		trip.initialize();
		user.addTrip(trip);
		Seat seat = new Seat(trip, user); //Creamos asiento promotor
		seat.promoterSeat();
		Jpa.getManager().persist(trip);
		Jpa.getManager().persist(seat);
		return trip;
	}
}

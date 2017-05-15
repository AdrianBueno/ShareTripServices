package com.sdi.business.impl.commands.apply;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Application;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.ApplicationFinder;
import com.sdi.persistence.finder.SeatFinder;
/**
 * Esta clase controla la lógica de aplicación encargada de implementar la
 * cancelación de una solicitud de plaza en un viaje.
 * @author sdi-39
 * @version 2 15/06
 *
 */
public class CancelApply implements Command {

	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(CancelApply.class);
	}
	private Long userId;
	private Long tripId;
	
	public CancelApply(Long tripId, Long userId) {
		this.userId = userId;
		this.tripId = tripId;
		log.info(Msg.getStr("info.instanced"));
	}
	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(userId); //Ids no nulos
		ApplicationAsserts.assertValueNotNull(tripId);
		
		User user = Jpa.getManager().find(User.class, userId);
		Trip trip = Jpa.getManager().find(Trip.class, tripId);
		ApplicationAsserts.assertEntityExist(user); //Entidades deben existir
		ApplicationAsserts.assertEntityExist(trip);
		
		Application app = ApplicationFinder.getApplication(trip, user);
		Seat seat = SeatFinder.getSeat(trip, user);
		ApplicationAsserts.assertEntityExist(app); //Debe existir la solicitud
		ApplicationAsserts.assertEntityExist(seat);
		
		seat.cancelSeat(); //Si está aceptada restará plazas
		Jpa.getManager().remove(app);
		Jpa.getManager().remove(seat); //Borramos
		log.info("Se ha cancelado una plaza de viaje");
		return seat;
	}
}

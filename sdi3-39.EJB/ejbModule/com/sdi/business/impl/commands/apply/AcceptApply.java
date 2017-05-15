
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
 * Esta clase encapsula la lógica de aplicación encargada de aceptar una
 * solicitud de viaje
 * @author sdi2-39
 * @version 2 11/06
 */
public class AcceptApply implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(AcceptApply.class);
	}
	private Long userId;
	private Long tripId;
	
	public AcceptApply(Long tripId, Long userId) {
		this.userId = userId;
		this.tripId = tripId;
		log.info(Msg.getStr("info.instanced"));
	}
	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(userId);
		ApplicationAsserts.assertValueNotNull(tripId); //Ids no nulos
		
		User user = Jpa.getManager().find(User.class, userId);
		Trip trip = Jpa.getManager().find(Trip.class, tripId);
		ApplicationAsserts.assertEntityExist(user);
		ApplicationAsserts.assertEntityExist(trip); //Entidades deben existir
		
		Application app = ApplicationFinder.getApplication(trip, user);
		Seat seat = SeatFinder.getSeat(trip, user);
		ApplicationAsserts.assertEntityExist(app); //Debe existir la solicitud
		ApplicationAsserts.assertEntityExist(seat);
		
		seat.acceptSeat(); //Aceptamos asiento lo que restará plazas.
		log.info("Se ha aceptado una plaza de viaje");
		return seat;
	}
}

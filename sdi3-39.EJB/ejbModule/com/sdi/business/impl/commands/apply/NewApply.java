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
 * Esta clase encapsula la lógica de aplicación encargada de solicitar plazas
 * en un vije
 * @author sdi2-39
 * @version 2 11/06
 */
public class NewApply implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(NewApply.class);
	}
	private Long tripId;
	private Long userId;
	
	public NewApply(Long trip, Long user) {
		this.tripId = trip;
		this.userId = user;
		log.info(Msg.getStr("info.instanced"));
	}
	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(userId);//Ids no nulos
		ApplicationAsserts.assertValueNotNull(tripId);
		
		User user = Jpa.getManager().find(User.class, userId);
		Trip trip = Jpa.getManager().find(Trip.class, tripId);
		ApplicationAsserts.assertEntityExist(user); //Entidades deben existir
		ApplicationAsserts.assertEntityExist(trip);
		
		Application app = ApplicationFinder.getApplication(trip, user);
		Seat seat = SeatFinder.getSeat(trip, user);
		ApplicationAsserts.assertAlreadyApplicationExist(app); 
		ApplicationAsserts.assertAlreadySeatExsist(seat);//Solicitud previa
		
		app = new Application(trip, user); //Creamos solicitud y asiento
		seat = new Seat(trip, user);
		seat.pendingSeat();					//Ponemos asiento a pendiente.
		Jpa.getManager().persist(app);
		Jpa.getManager().persist(seat);
		log.info("Se ha solicitado plaza en un viaje");
		return app;
	}
}

package com.sdi.business.impl.commands.trips;

import java.util.List;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.TripFinder;

/**
 * Esta clase lista todos los viajes propiedad de un usuario
 * @author sdi2-39
 * @version 3 15/06
 */
public class ListPromoterTrips implements Command {

	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(ListPromoterTrips.class);
	}
	
	private Long userId;

	public ListPromoterTrips(Long userId){
		this.userId = userId;
		log.info(Msg.getStr("info.instanced"));
	}
	
	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(userId);
		User user = Jpa.getManager().find(User.class, userId);
		ApplicationAsserts.assertEntityExist(user);
		List<Trip> list = TripFinder.listTripsByPromoter(user);
		checkTrips(list);
		//Podemos devolver la lista sin volver a pedirla
		//Pues persiste y arriba debemos mostrarlos todos igualmente
		//Solo cambiará su estado.
		return list;
	}
	
	private void checkTrips(List<Trip> list){
		//Con este código verificamos los viajes.
		//Si alguno debería estar cerrado o realizado se cambiará su estado.
		for(Trip trip : list)
			trip.checkTrip();
	}
}

package com.sdi.business.impl.commands.trips;


import java.util.List;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.SeatFinder;
/**
 * Esta clase encapsula la lógica de aplicación encargada de controlar
 * la actualización de datos de un viaje
 * @author sdi2-39
 * @version 2 07/04
 */
public class UpdateTrip implements Command {
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(UpdateTrip.class);
	}
	
	private Trip trip;

	public UpdateTrip(Trip trip) {
		this.trip = trip;
		log.info(Msg.getStr("info.instanced"));
	}

	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(trip);
		Trip original = Jpa.getManager().find(Trip.class, trip.getId());
		ApplicationAsserts.assertEntityExist(original);
		trip.reInitialize();
		assertTooManyAcceptedSeats(trip, original);
		trip= Jpa.getManager().merge(trip);
		log.info("El viaje ha sido actualizado");
		return trip;
	}

	private void assertTooManyAcceptedSeats(Trip trip, Trip original) 
	throws BusinessException {
		int accepted = 0;
		List<Seat> list = SeatFinder.listSeastAccepted(original);
		if(list != null)
			accepted = list.size();
		/*Si el número de plazas aceptadas en el orginal es mayor que el
		 * nuevo número de plazas máxima, excepción
		 * Si las número de plazas aceptadas, más el nuevo número de disponibles
		 * más 1 es igual o mayor que el nuevo número de plazas máximas
		 * Excepción (esto porque al menos tiene que haber una plaza disponible.
		 */
		if(trip.getMaxPax() < accepted || (accepted+trip.getAvailablePax() >= trip.getMaxPax() ))
			throw new BusinessException("assert.app_triptoomanyaccepted");	
	}

}

package com.sdi.business.impl.commands.trips;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.Jpa;
/**
 * Esta clase se encarga de manejar la lógica de negocio y aplicación para
 * realizar la Cancelación de un viaje.
 * @author sdi-39
 * @version 1 11/06
 */
public class CancelTrip implements Command {

	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(CancelTrip.class);
	}
	
	private Long tripId;

	public CancelTrip(Long tripId) {
		this.tripId = tripId;
		log.info(Msg.getStr("info.instanced"));
	}

	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(tripId);
		Trip trip = Jpa.getManager().find(Trip.class, tripId);
		ApplicationAsserts.assertEntityExist(trip);
		trip.cancelTrip();
		log.info("El viaje ha sido cancelado");
		return trip;
	}
}

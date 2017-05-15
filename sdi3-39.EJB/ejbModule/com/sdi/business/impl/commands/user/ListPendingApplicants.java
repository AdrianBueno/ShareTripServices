package com.sdi.business.impl.commands.user;

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
import com.sdi.persistence.finder.UserFinder;

public class ListPendingApplicants implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(ListPendingApplicants.class);
	}
	private Long tripId;

	public ListPendingApplicants(Long tripId) {
		this.tripId = tripId;
		log.info(Msg.getStr("info.instanced"));
	}

	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(tripId);
		Trip trip = Jpa.getManager().find(Trip.class, tripId);
		ApplicationAsserts.assertEntityExist(trip);
		List<User> list =  UserFinder.listUsersPending(trip);
		if(list != null)
			log.info("Número de solicitudes pendientes: "+list.size());
		else
			log.info("No hay solicitudes pendientes de viaje");
		return list;
	}

}

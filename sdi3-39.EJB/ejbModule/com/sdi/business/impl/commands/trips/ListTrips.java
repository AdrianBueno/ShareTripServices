package com.sdi.business.impl.commands.trips;

import java.util.List;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.types.TripStatus;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.finder.TripFinder;
/**
 * Se encarga de controlar la lógica de aplicación de listar viajes.
 * @author sdi2-39
 * @version 27/03
 */
public class ListTrips implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(ListTrips.class);
	}
	
	private TripStatus status;

	public ListTrips(TripStatus status){
		this.status = status;
		log.info(Msg.getStr("info.instanced_values")+" TravelStatus: "+status);
		log.info(Msg.getStr("info.instanced"));
	}
	
	@Override
	public Object execute() throws BusinessException {
		List<Trip> list =  status != null 
				? TripFinder.listTripsByStatus(status)
				: TripFinder.listTrips();
		if(list != null)
			log.info("Número de viajes listados: "+list.size());
		else
			log.info("No hay viajes que listar");
		return list;
	}

}

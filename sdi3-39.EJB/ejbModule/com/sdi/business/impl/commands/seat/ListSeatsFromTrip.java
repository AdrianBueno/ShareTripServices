package com.sdi.business.impl.commands.seat;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.types.TravelStatus;
import com.sdi.persistence.Jpa;
/**
 * Esta clase se encarga de controlar la lógica de aplicación relacionada
 * con la obtención de las plazas de un determinado viaje
 * @author sdi-39
 * @version 1 15/06
 *
 */
public class ListSeatsFromTrip implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(ListSeatsFromTrip.class);
	}
	
	private Long tripId;
	private TravelStatus status;
	public ListSeatsFromTrip(Long tripId, TravelStatus status){
		this.tripId = tripId;
		this.status = status;
	}
	
	@Override
	public Object execute() throws BusinessException {
		log.info("Intentado listar plazas");
		ApplicationAsserts.assertValueNotNull(tripId);
		Trip trip = Jpa.getManager().find(Trip.class, tripId);
		ApplicationAsserts.assertEntityExist(trip);
		if(status != null)
			return listSeatsByStatus(trip, status);
		else
			return listSeats(trip);
	}
	
	private List<Seat> listSeatsByStatus(Trip trip, TravelStatus status){
		List<Seat> seats = new ArrayList<Seat>();
		for(Seat seat : trip.getSeats()){
			if(seat.getStatus() == status)
				seats.add(seat);
		}
		return seats;
	}
	private List<Seat> listSeats(Trip trip){
		List<Seat> seats = new ArrayList<Seat>();
		for(Seat seat : trip.getSeats()){
			seats.add(seat);
		}
		return seats;
	}

}

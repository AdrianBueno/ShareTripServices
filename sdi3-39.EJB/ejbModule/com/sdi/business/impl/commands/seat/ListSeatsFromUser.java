package com.sdi.business.impl.commands.seat;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.model.types.TravelStatus;
import com.sdi.persistence.Jpa;
/**
 * Esta clase se encarga de controlar la lógica de aplicación relacionada
 * con la obtención de las plazas de un determinado viaje
 * @author sdi-39
 * @version 1 15/06
 *
 */
public class ListSeatsFromUser implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(ListSeatsFromUser.class);
	}
	
	private Long userId;
	private TravelStatus status;
	public ListSeatsFromUser(Long userId, TravelStatus status){
		this.userId = userId;
		this.status = status;
	}
	
	@Override
	public Object execute() throws BusinessException {
		log.info("Listando asientos de usuario");
		ApplicationAsserts.assertValueNotNull(userId);
		User user = Jpa.getManager().find(User.class, userId);
		ApplicationAsserts.assertEntityExist(user);
		if(status != null)
			return listSeatsByStatus(user, status);
		else
			return listSeats(user);
	}
	
	private List<Seat> listSeatsByStatus(User user, TravelStatus status){
		List<Seat> seats = new ArrayList<Seat>();
		for(Seat seat : user.getSeats()){
			if(seat.getStatus() == status)
				seats.add(seat);
		}
		return seats;
	}
	private List<Seat> listSeats(User user){
		List<Seat> seats = new ArrayList<Seat>();
		for(Seat seat : user.getSeats()){
			seats.add(seat);
		}
		return seats;
	}
}

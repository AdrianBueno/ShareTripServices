package com.sdi.business.impl.commands.ratings;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Rating;
import com.sdi.persistence.Jpa;
/**
 * Esta clase se encarga de manejar la lógica de aplicaicón que provee
 * un trip relacionado con un rating en función del tipo de este.
 * @author nardi
 *
 */
public class TripRating implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(TripRating.class);
	}
	public static int ABOUT = 1;
	public static int FROM = 2;
	private Long ratingId;
	private int type;


	public TripRating(Long ratingId, int type) {
		this.ratingId = ratingId;
		this.type = type;
	}
	
	
	@Override
	public Object execute() throws BusinessException {
		log.info("Obteniendo usuario de rating");
		ApplicationAsserts.assertValueNotNull(ratingId);
		Rating rat = Jpa.getManager().find(Rating.class, ratingId);
		ApplicationAsserts.assertEntityExist(rat);
		if(type == ABOUT)
			return rat.getAboutSeat().getTrip();
		if(type == FROM)
			return rat.getFromSeat().getTrip();
		return null;
	}

}

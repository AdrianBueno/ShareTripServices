package com.sdi.business.impl.commands.ratings;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Rating;
import com.sdi.persistence.Jpa;

public class UserRating implements Command {
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(UserRating.class);
	}
	public static int ABOUT = 1;
	public static int FROM = 2;
	private Long ratingId;
	private int type;


	public UserRating(Long ratingId, int type) {
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
			return rat.getAboutSeat().getUser();
		if(type == FROM)
			return rat.getFromSeat().getUser();
		return null;
	}

}

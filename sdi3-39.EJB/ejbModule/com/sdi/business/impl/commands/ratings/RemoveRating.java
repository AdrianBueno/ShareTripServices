package com.sdi.business.impl.commands.ratings;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Rating;
import com.sdi.persistence.Jpa;

public class RemoveRating implements Command {
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(RemoveRating.class);
	}

	private Long ratingId;

	public RemoveRating(Long ratingId) {
		this.ratingId = ratingId;
	}

	@Override
	public Object execute() throws BusinessException {
		Rating rating = Jpa.getManager().find(Rating.class, ratingId);
		if (rating == null)
			throw new BusinessException("No se pudo eliminar la valoración.");
		rating.unlink();
		Jpa.getManager().remove(rating);
		log.info("Eliminado rating [%s]", rating.getId());

		return rating;
	}

}

package com.sdi.business.impl.commands.ratings;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.persistence.finder.RatingFinder;

public class ListLastMonthRatings implements Command {

	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(ListLastMonthRatings.class);
	}
	public ListLastMonthRatings() {
		log.debug(Msg.getStr("info.instanced_values")+" None");
		log.info(Msg.getStr("info.instanced"));
	}
	@Override
	public Object execute() throws BusinessException {
		return RatingFinder.listLastMonthRatings();
	}

}

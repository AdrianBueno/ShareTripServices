package com.sdi.rest.impl;

import com.sdi.business.AppliesService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.Application;
import com.sdi.infrastructure.model.Seat;
import com.sdi.rest.AppliesServiceRest;

public class AppliesServiceRestImpl implements AppliesServiceRest {
	
	AppliesService service = Factories.services.getAppliesService();
	
	@Override
	public Application newApply(Long trip, Long user) throws BusinessException {
		try{return service.newApply(trip, user);}
		catch(BusinessException e){return null;}
	}

	@Override
	public Seat acceptApply(Long trip, Long user) throws BusinessException {
		try{return service.acceptApply(trip, user);}
		catch(BusinessException e){return null;}
	}

	@Override
	public Seat cancelApply(Long trip, Long user) throws BusinessException {
		try{return service.cancelApply(trip, user);}
		catch(BusinessException e){return null;}
	}

	@Override
	public Seat excludeSeat(Long trip, Long user) throws BusinessException {
		try{return service.excludeSeat(trip, user);}
		catch(BusinessException e){return null;}
	}

}

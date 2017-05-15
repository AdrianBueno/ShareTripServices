package com.sdi.rest.impl;

import java.util.List;

import com.sdi.business.RatingsService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.Rating;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.rest.RatingsServiceRest;

public class RatingsServiceRestImpl implements RatingsServiceRest {

	RatingsService  service = Factories.services.getRatingService();
	
	@Override
	public List<Rating> listLastMonthRatings() throws BusinessException {
		try{return service.listLastMonthRatings();}
		catch(BusinessException e){return null;}
	}

	@Override
	public Rating removeRating(Long ratingId) throws BusinessException {
		try{return service.removeRating(ratingId);}
		catch(BusinessException e){return null;}
		
	}

	@Override
	public User getUserAbout(Long ratingId) throws BusinessException {
		try{return service.getUserAbout(ratingId);}
		catch(BusinessException e){return null;}
	}

	@Override
	public User getUserFrom(Long ratingId) throws BusinessException {
		try{return service.getUserFrom(ratingId);}
		catch(BusinessException e){return null;}
	}

	@Override
	public Trip getTripAbout(Long ratingId) throws BusinessException {
		try{return service.getTripAbout(ratingId);}
		catch(BusinessException e){return null;}
	}

	@Override
	public Trip getTripFrom(Long ratingId) throws BusinessException {
		try{return service.getTripFrom(ratingId);}
		catch(BusinessException e){return null;}
	}

}

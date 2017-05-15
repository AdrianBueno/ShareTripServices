package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.commands.CommandExecutor;
import com.sdi.business.impl.commands.ratings.ListLastMonthRatings;
import com.sdi.business.impl.commands.ratings.RemoveRating;
import com.sdi.business.impl.commands.ratings.TripRating;
import com.sdi.business.impl.commands.ratings.UserRating;
import com.sdi.business.impl.face.local.LocalRatingsService;
import com.sdi.business.impl.face.remote.RemoteRatingsService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Rating;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;

@Stateless
@WebService(name = "RatingsService")
public class EjbRatingsService implements RemoteRatingsService,
		LocalRatingsService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Rating> listLastMonthRatings() throws BusinessException {
		return (List<Rating>) CommandExecutor
				.execute(new ListLastMonthRatings());
	}

	@Override
	public Rating removeRating(Long ratingId) throws BusinessException {
		return (Rating) CommandExecutor.execute(new RemoveRating(ratingId));
	}
	
	@Override
	public User getUserAbout(Long ratingId) throws BusinessException {
		return (User) CommandExecutor.execute(new UserRating(ratingId,
				UserRating.ABOUT));
	}

	@Override
	public User getUserFrom(Long ratingId) throws BusinessException {
		return (User) CommandExecutor.execute(new UserRating(ratingId,
				UserRating.FROM));
	}

	@Override
	public Trip getTripAbout(Long ratingId) throws BusinessException {
		return (Trip) CommandExecutor.execute(new TripRating(ratingId,
				UserRating.ABOUT));
	}

	@Override
	public Trip getTripFrom(Long ratingId) throws BusinessException {
		return (Trip) CommandExecutor.execute(new TripRating(ratingId,
				UserRating.FROM));
	}

}

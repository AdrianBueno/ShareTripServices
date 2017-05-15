package com.sdi.business;

import java.util.List;

import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Rating;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;

public interface RatingsService {
	/**
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<Rating> listLastMonthRatings() throws BusinessException;
	/**
	 * 
	 * @param ratingId
	 * @throws BusinessException
	 */
	public Rating removeRating(Long ratingId) throws BusinessException;
	/**
	 * 
	 * @param ratingId
	 * @return
	 * @throws BusinessException
	 */
	public User getUserAbout(Long ratingId) throws BusinessException;
	/**
	 * 
	 * @param ratingId
	 * @return
	 * @throws BusinessException
	 */
	public User getUserFrom(Long ratingId) throws BusinessException;
	/**
	 * 
	 * @param ratingId
	 * @return
	 * @throws BusinessException
	 */
	public Trip getTripAbout(Long ratingId) throws BusinessException;
	/**
	 * 
	 * @param ratingId
	 * @return
	 * @throws BusinessException
	 */
	public Trip getTripFrom(Long ratingId) throws BusinessException;
}

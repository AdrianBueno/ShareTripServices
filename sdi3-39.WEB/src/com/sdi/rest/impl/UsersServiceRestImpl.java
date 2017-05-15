package com.sdi.rest.impl;

import java.util.List;

import com.sdi.business.UsersService;
import com.sdi.infrastructure.exception.ApplicationException;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.User;
import com.sdi.rest.UsersServiceRest;

public class UsersServiceRestImpl implements UsersServiceRest {

	UsersService service = Factories.services.getUsersService();

	@Override
	public User loginUser(String login, String pass) throws BusinessException {
		try{return service.loginUser(login, pass);}
		catch(BusinessException e){return null;}
	}

	@Override
	public User RegisterUser(User user) throws BusinessException {
		try{return service.RegisterUser(user);}
		catch(BusinessException e){return null;}
	}

	@Override
	public User DisableUser(Long userId) throws BusinessException {
		try{return service.DisableUser(userId);}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<User> listApplicants(Long trip) throws BusinessException {
		try{return service.listPendingApplicants(trip);}
		catch(BusinessException e){return null;}
	}

	@Override
	public List<User> listUsers() throws BusinessException {
		try{return service.listUsers();}
		catch(BusinessException e){return null;}
	}

	@Override
	public Integer getNumberOfTrips(Long userId) throws ApplicationException {
		try{return service.getNumberOfTrips(userId);}
		catch(BusinessException e){return null;}
	}

	@Override
	public Integer getNumberOfSeats(Long userId) throws ApplicationException {
		try{return service.getNumberOfSeats(userId);}
		catch(BusinessException e){return null;}
	}

}

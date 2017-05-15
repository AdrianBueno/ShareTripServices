package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.CommandExecutor;
import com.sdi.business.impl.commands.user.DisableUser;
import com.sdi.business.impl.commands.user.ListApplicants;
import com.sdi.business.impl.commands.user.ListPendingApplicants;
import com.sdi.business.impl.commands.user.ListUsers;
import com.sdi.business.impl.commands.user.LoginUser;
import com.sdi.business.impl.commands.user.RegisterUser;
import com.sdi.business.impl.face.local.LocalUsersService;
import com.sdi.business.impl.face.remote.RemoteUsersService;
import com.sdi.infrastructure.exception.ApplicationException;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.model.types.TravelStatus;
import com.sdi.persistence.Jpa;

@Stateless
@WebService(name = "UsersService")
public class EjbUsersService implements LocalUsersService, RemoteUsersService {

	@Override
	public User loginUser(String login, String pass) throws BusinessException {
		return (User) CommandExecutor.execute(new LoginUser(login, pass));
	}

	@Override
	public User RegisterUser(User user) throws BusinessException {
		return (User) CommandExecutor.execute(new RegisterUser(user));

	}

	@Override
	public User DisableUser(Long userId) throws BusinessException {
		return (User) CommandExecutor.execute(new DisableUser(userId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() throws BusinessException {
		return (List<User>) CommandExecutor.execute(new ListUsers());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listApplicants(Long trip) throws BusinessException {
		return (List<User>) CommandExecutor.execute(new ListApplicants(trip));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listPendingApplicants(Long trip) 
			throws BusinessException {
		return (List<User>) CommandExecutor.execute(new ListPendingApplicants(
				trip));
	}

	@Override
	public Integer getNumberOfTrips(Long userId) throws ApplicationException {
		ApplicationAsserts.assertValueNotNull(userId);
		User user = Jpa.getManager().find(User.class, userId);
		ApplicationAsserts.assertEntityExist(user);
		return user.getTrips().size();
	}

	@Override
	public Integer getNumberOfSeats(Long userId) throws ApplicationException {
		ApplicationAsserts.assertValueNotNull(userId);
		User user = Jpa.getManager().find(User.class, userId);
		ApplicationAsserts.assertEntityExist(user);
		int seats = 0;
		for (Seat seat : user.getSeats())
			if (seat.getStatus() == TravelStatus.ACCEPTED)
				seats++;
		return seats;
	}

}

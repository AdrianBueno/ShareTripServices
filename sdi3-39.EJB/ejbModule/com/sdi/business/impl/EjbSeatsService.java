package com.sdi.business.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.commands.CommandExecutor;
import com.sdi.business.impl.commands.seat.ListSeatsFromTrip;
import com.sdi.business.impl.commands.seat.ListSeatsFromUser;
import com.sdi.business.impl.face.local.LocalSeatsService;
import com.sdi.business.impl.face.remote.RemoteSeatsService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.types.TravelStatus;

@Stateless
@WebService(name = "SeatsService")
public class EjbSeatsService implements LocalSeatsService, RemoteSeatsService {

	@SuppressWarnings("unchecked")
	@Override
	public List<Seat> listSeatsFromTrip(Long t,TravelStatus s) 
	throws BusinessException {
		return (List<Seat>) CommandExecutor.execute(new ListSeatsFromTrip(t,s));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Seat> listSeatsFromUser(Long u, TravelStatus s) 
	throws BusinessException {
		return (List<Seat>) CommandExecutor.execute(new ListSeatsFromUser(u,s));
	}
	
}

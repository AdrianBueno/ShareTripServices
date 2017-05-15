package com.sdi.business.impl;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.commands.CommandExecutor;
import com.sdi.business.impl.commands.apply.AcceptApply;
import com.sdi.business.impl.commands.apply.CancelApply;
import com.sdi.business.impl.commands.apply.ExcludeApply;
import com.sdi.business.impl.commands.apply.NewApply;
import com.sdi.business.impl.face.local.LocalAppliesService;
import com.sdi.business.impl.face.remote.RemoteAppliesService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Application;
import com.sdi.infrastructure.model.Seat;

@Stateless
@WebService(name = "AppliesService")
public class EjbAppliesService implements LocalAppliesService,
		RemoteAppliesService {
	
	@Override
	public Application newApply(Long trip, Long user) throws BusinessException {
		return (Application) CommandExecutor.execute(new NewApply(trip,user));
	}
	
	@Override
	public Seat acceptApply(Long trip, Long user) throws BusinessException {
		return (Seat) CommandExecutor.execute(new AcceptApply(trip, user));
	}
	
	@Override
	public Seat cancelApply(Long trip, Long user) throws BusinessException {
		return (Seat) CommandExecutor.execute(new CancelApply(trip, user));
	}

	@Override
	public Seat excludeSeat(Long trip, Long user) throws BusinessException {
		return (Seat) CommandExecutor.execute(new ExcludeApply(trip, user));
	}

}

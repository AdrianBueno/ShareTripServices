package com.sdi.business.impl;

import javax.ejb.Stateless;
import javax.jws.WebService;

import com.sdi.business.impl.commands.CommandExecutor;
import com.sdi.business.impl.commands.admin.ResetDataBase;
import com.sdi.business.impl.face.local.LocalAdminService;
import com.sdi.business.impl.face.remote.RemoteAdminService;
import com.sdi.infrastructure.exception.BusinessException;

@Stateless
@WebService(name = "AdminService")
public class EjbAdminService implements LocalAdminService, RemoteAdminService {

	@Override
	public void resetDataBase() throws BusinessException {
		CommandExecutor.execute(new ResetDataBase());
	}

}

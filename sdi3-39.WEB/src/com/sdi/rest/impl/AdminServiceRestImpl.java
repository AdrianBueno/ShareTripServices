package com.sdi.rest.impl;

import com.sdi.business.AdminService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.rest.AdminServiceRest;

public class AdminServiceRestImpl implements AdminServiceRest {

	AdminService service = Factories.services.getAdminService();
	
	@Override
	public void resetDataBase() throws BusinessException {
		service.resetDataBase();
	}

}

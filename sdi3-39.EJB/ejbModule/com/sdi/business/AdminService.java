package com.sdi.business;

import com.sdi.infrastructure.exception.BusinessException;

public interface AdminService {

	/**
	 * Mantenimietno, resetea base de datos
	 * @throws BusinessException Si no cumple los asserts
	 */
	public void resetDataBase() throws BusinessException;
	
}

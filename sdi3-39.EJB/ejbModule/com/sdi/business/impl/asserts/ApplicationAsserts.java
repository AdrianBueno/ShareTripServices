package com.sdi.business.impl.asserts;

import com.sdi.infrastructure.exception.ApplicationException;

public class ApplicationAsserts {
	
	private ApplicationAsserts() {
	}
	
	public static void assertValueNotNull(Object o) throws ApplicationException{
		if(o == null)
			throw new ApplicationException("assert.app_null");
	}
	
	public static void assertEntityExist(Object o) throws ApplicationException{
		if(o == null)
			throw new ApplicationException("assert.app_entity_unexist");
	}
	
	public static void assertAlreadyExist(Object o) throws ApplicationException{
		if(o != null)
			throw new ApplicationException("assert.app_alreadyexist");
	}
	
	public static void assertAlreadySeatExsist(Object o) 
	throws ApplicationException{
		if(o != null)
			throw new ApplicationException("assert.app_alreadyseatexist");
	}
	public static void assertAlreadyApplicationExist(Object o) 
	throws ApplicationException{
		if(o != null)
			throw new ApplicationException("assert.app_alreadyapplyexist");
	}
	public static void assertAlreadyUserExist(Object o) 
	throws ApplicationException{
		if(o != null)
			throw new ApplicationException("assert.app_alreadyuserexist");
	}
	public static void assertAlreadyTripExist(Object o) 
	throws ApplicationException{
		if(o != null)
			throw new ApplicationException("assert.app_alreadytripexist");
	}
}

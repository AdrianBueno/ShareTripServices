package com.sdi.business.impl.locator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sdi.business.AdminService;
import com.sdi.business.AppliesService;
import com.sdi.business.RatingsService;
import com.sdi.business.SeatsService;
import com.sdi.business.ServicesFactory;
import com.sdi.business.TripsService;
import com.sdi.business.UsersService;

public class RemoteEjbServicesLocator implements ServicesFactory {

	private static final String TRIPS_SERVICE_JNDI_KEY = "sdi3-39/"
			+ "sdi3-39.EJB/" + "EjbTripsService!"
			+ "com.sdi.business.impl.face.remote.RemoteTripsService";
	private static final String USERS_SERVICE_JNDI_KEY = "sdi3-39/"
			+ "sdi3-39.EJB/" + "EjbUsersService!"
			+ "com.sdi.business.impl.face.remote.RemoteUsersService";
	private static final String SEATS_SERVICE_JNDI_KEY = "sdi3-39/"
			+ "sdi3-39.EJB/" + "EjbSeatsService!"
			+ "com.sdi.business.impl.face.remote.RemoteSeatsService";
	private static final String APPLIES_SERVICE_JNDI_KEY = "sdi3-39/"
			+ "sdi3-39.EJB/" + "EjbAppliesService!"
			+ "com.sdi.business.impl.face.remote.RemoteAppliesService";
	private static final String RATINGS_SERVICE_JNDI_KEY = "sdi3-39/"
			+ "sdi3-39.EJB/" + "EjbRatingsService!"
			+ "com.sdi.business.impl.face.remote.RemoteRatingsService";
	private static final String ADMIN_SERVICE_JNDI_KEY = "sdi3-39/"
			+ "sdi3-39.EJB/" + "EjbAdminService!"
			+ "com.sdi.business.impl.face.remote.RemoteAdminService";

	@Override
	public TripsService getTripsService() {
		try {
			Context ctx = new InitialContext();
			return (TripsService) ctx.lookup(TRIPS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public UsersService getUsersService() {
		try {
			Context ctx = new InitialContext();
			return (UsersService) ctx.lookup(USERS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public SeatsService getSeatsService() {
		try {
			Context ctx = new InitialContext();
			return (SeatsService) ctx.lookup(SEATS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public AppliesService getAppliesService() {
		try {
			Context ctx = new InitialContext();
			return (AppliesService) ctx.lookup(APPLIES_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public RatingsService getRatingService() {
		try {
			Context ctx = new InitialContext();
			return (RatingsService) ctx.lookup(RATINGS_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}

	@Override
	public AdminService getAdminService() {
		try {
			Context ctx = new InitialContext();
			return (AdminService) ctx.lookup(ADMIN_SERVICE_JNDI_KEY);
		} catch (NamingException e) {
			throw new RuntimeException("JNDI problem", e);
		}
	}
}

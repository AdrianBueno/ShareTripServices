package com.sdi.business;


public interface ServicesFactory {
	
	TripsService getTripsService();
	UsersService getUsersService();
	SeatsService getSeatsService();
	AppliesService getAppliesService();
	RatingsService getRatingService();
	AdminService getAdminService();
}

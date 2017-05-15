package com.sdi.client.infrastructure.services;

import com.sdi.client.infrastructure.model.User;

public class SessionService {
	
	public static User user;
	public static Long tripId;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		SessionService.user = user;
	}

	public static Long getTripId() {
		return tripId;
	}

	public static void setTripId(Long tripId) {
		SessionService.tripId = tripId;
	}
	
	
	

}

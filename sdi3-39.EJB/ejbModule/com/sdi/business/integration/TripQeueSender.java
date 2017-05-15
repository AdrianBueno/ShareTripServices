package com.sdi.business.integration;

import java.util.List;

import com.sdi.infrastructure.model.Trip;

public interface TripQeueSender {
	public void sendTripList(List<Trip> trips);
}

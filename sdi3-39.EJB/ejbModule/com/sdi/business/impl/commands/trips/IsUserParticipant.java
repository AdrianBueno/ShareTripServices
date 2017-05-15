package com.sdi.business.impl.commands.trips;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.model.types.TravelStatus;
import com.sdi.persistence.Jpa;

public class IsUserParticipant implements Command {

	private Long tripId;
	private Long userId;

	public IsUserParticipant(Long tripId, Long userId) {
		this.tripId = tripId;
		this.userId = userId;
	}

	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(tripId);
		ApplicationAsserts.assertValueNotNull(userId);
		Trip trip = Jpa.getManager().find(Trip.class, tripId);
		User user = Jpa.getManager().find(User.class, userId);
		ApplicationAsserts.assertEntityExist(trip);
		ApplicationAsserts.assertEntityExist(user);

		for (Seat seat : trip.getSeats()) {
			if (seat.getUser().equals(user)
				&& (seat.getStatus().equals(TravelStatus.ACCEPTED) ||
					seat.getStatus().equals(TravelStatus.PROMOTER)))
				return true;
		}
		return false;
	}

}

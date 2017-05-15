package com.sdi.business.impl.commands.trips;

import java.util.List;

import com.sdi.business.impl.asserts.ApplicationAsserts;
import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.TripFinder;

public class ListAcceptedAndPromotedTrips implements Command {

	private Long userId;

	public ListAcceptedAndPromotedTrips(Long userId) {
		this.userId = userId;
	}

	@Override
	public Object execute() throws BusinessException {
		ApplicationAsserts.assertValueNotNull(userId);
		User user = Jpa.getManager().find(User.class, userId);
		ApplicationAsserts.assertEntityExist(user);
		List<Trip> myTrips = TripFinder.listTripsByPromoter(user);
		myTrips.addAll(TripFinder.listAcceptedByUser(user));
		return myTrips;
	}

}

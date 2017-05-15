package com.sdi.infrastructure.model.idclass;

import java.io.Serializable;

public class SeatId implements Serializable  {
	
	private static final long serialVersionUID = -4074403702812409661L;
	
	private Long trip;
	private Long user;
	
	public SeatId(){}

	public Long getTrip() {
		return trip;
	}

	public void setTrip(Long trip) {
		this.trip = trip;
	}

	public Long getUser() {
		return user;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeatId other = (SeatId) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	
	
}

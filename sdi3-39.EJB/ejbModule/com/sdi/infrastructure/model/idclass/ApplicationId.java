package com.sdi.infrastructure.model.idclass;

import java.io.Serializable;

public class ApplicationId implements Serializable {

	private static final long serialVersionUID = -8535048055402064687L;
	
	private Long appliedtrips;
	private Long applicants;
	
	public ApplicationId(){}
	
	public Long getTrip() {
		return appliedtrips;
	}
	public void setTrip(Long trip) {
		this.appliedtrips = trip;
	}
	public Long getUser() {
		return applicants;
	}
	public void setUser(Long user) {
		this.applicants = user;
	}

	public Long getAppliedtrips() {
		return appliedtrips;
	}

	public void setAppliedtrips(Long appliedtrips) {
		this.appliedtrips = appliedtrips;
	}

	public Long getApplicants() {
		return applicants;
	}

	public void setApplicants(Long applicants) {
		this.applicants = applicants;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicants == null) ? 0 : applicants.hashCode());
		result = prime * result
				+ ((appliedtrips == null) ? 0 : appliedtrips.hashCode());
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
		ApplicationId other = (ApplicationId) obj;
		if (applicants == null) {
			if (other.applicants != null)
				return false;
		} else if (!applicants.equals(other.applicants))
			return false;
		if (appliedtrips == null) {
			if (other.appliedtrips != null)
				return false;
		} else if (!appliedtrips.equals(other.appliedtrips))
			return false;
		return true;
	}
}

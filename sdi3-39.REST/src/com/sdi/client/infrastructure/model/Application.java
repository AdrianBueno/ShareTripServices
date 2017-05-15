package com.sdi.client.infrastructure.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta clase pertenece al modelo, representa
 * una petición entre un usuario y un viaje
 * @author sd2-39
 * @version 2
 */
@XmlRootElement(name = "application")
public class Application implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Trip appliedtrips;
	private User applicants;
	
	public Application(){}
	
	public Application(Trip trip, User user){
		setTrip(trip);
		setUser(user);
		
	}

	@XmlElement
	public Trip getTrip() {
		return appliedtrips;
	}

	public void setTrip(Trip trip) {
		this.appliedtrips = trip;
	}

	@XmlElement
	public User getUser() {
		return applicants;
	}

	public void setUser(User user) {
		this.applicants = user;
	}

	public Trip getAppliedtrips() {
		return appliedtrips;
	}

	public void setAppliedtrips(Trip appliedtrips) {
		this.appliedtrips = appliedtrips;
	}

	public User getApplicants() {
		return applicants;
	}

	public void setApplicants(User applicants) {
		this.applicants = applicants;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appliedtrips == null) 
				? 0 : appliedtrips.hashCode());
		result = prime * result + ((applicants == null) 
				? 0 : applicants.hashCode());
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
		Application other = (Application) obj;
		if (appliedtrips == null) {
			if (other.appliedtrips != null)
				return false;
		} else if (!appliedtrips.equals(other.appliedtrips))
			return false;
		if (applicants == null) {
			if (other.applicants != null)
				return false;
		} else if (!applicants.equals(other.applicants))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Application [appliedtrips=" + appliedtrips + ", applicants="
				+ applicants + "]";
	}
}

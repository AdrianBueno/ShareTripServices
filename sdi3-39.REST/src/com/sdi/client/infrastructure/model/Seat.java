package com.sdi.client.infrastructure.model;

import java.io.Serializable;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sdi.client.infrastructure.model.types.TravelStatus;

/**
 * Esta clase representa un asiento con estado de un viaje El estado puede
 * variar de pendiente, sin asiento, excluido o aceptado
 * 
 * @author sdi-39
 * @version 2 15/06
 * @see observer pattern
 */
@XmlRootElement(name = "seat")
public class Seat implements Serializable {

	private static final long serialVersionUID = 1L;

	private Trip trip;
	private User user;

	private TravelStatus status;

	private String comment;

	private Set<Rating> about;

	private Set<Rating> from;

	public Seat() {
	}
	
	@XmlElement
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	@XmlElement
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@XmlElement
	public TravelStatus getStatus() {
		return status;
	}

	public void setStatus(TravelStatus status) {
		this.status = status;
	}
	@XmlElement
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@XmlElement
	public Set<Rating> getAbout() {
		return about;
	}

	public void setAbout(Set<Rating> about) {
		this.about = about;
	}
	@XmlElement
	public Set<Rating> getFrom() {
		return from;
	}

	public void setFrom(Set<Rating> from) {
		this.from = from;
	}


	
	

}

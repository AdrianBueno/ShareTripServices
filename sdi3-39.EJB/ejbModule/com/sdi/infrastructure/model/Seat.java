package com.sdi.infrastructure.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.exception.BusinessModelException;
import com.sdi.infrastructure.model.idclass.SeatId;
import com.sdi.infrastructure.model.types.TravelStatus;

/**
 * Esta clase representa un asiento con estado de un viaje El estado puede
 * variar de pendiente, sin asiento, excluido o aceptado
 * 
 * @author sdi-39
 * @version 2 15/06
 * @see observer pattern
 */
@Entity
@Table(name = "TSEATS")
@IdClass(SeatId.class)
@XmlRootElement(name = "seat")
@XmlAccessorType(XmlAccessType.FIELD)
public class Seat implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@XmlElement
	private Trip trip;
	@Id
	@ManyToOne
	@XmlElement
	private User user;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATUS")
	@XmlElement
	private TravelStatus status;

	@Column(name = "COMMENT")
	@XmlElement
	private String comment;

	@OneToMany(mappedBy = "aboutSeat")
	@XmlTransient
	private Set<Rating> about = new HashSet<Rating>();

	@OneToMany(mappedBy = "fromSeat")
	@XmlTransient
	private Set<Rating> from = new HashSet<Rating>();

	public Seat() {
	}

	public Seat(Trip trip, User user) {
		setUser(user);
		setTrip(trip);
		trip._getSeats().add(this);
		user._getSeats().add(this);

	}

	// Métodos de asociación.

	public void unlink() {
		user._getSeats().remove(this);
		trip._getSeats().remove(this);
		user = null;
		trip = null;
	}

	public Set<Rating> getAbout() {
		return Collections.unmodifiableSet(about);
	}

	Set<Rating> _getAbout() {
		return about;
	}

	void _setAbout(Set<Rating> about) {
		this.about = about;
	}

	public Set<Rating> getFrom() {
		return Collections.unmodifiableSet(from);
	}

	Set<Rating> _getFrom() {
		return from;
	}

	void setFrom(Set<Rating> from) {
		this.from = from;
	}

	// Métodos naturales
	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TravelStatus getStatus() {
		return status;
	}

	public void setStatus(TravelStatus status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/*
	 * Cuando un viaje llega a su fin se marca como sin plaza a todos los que no
	 * la tengan aceptada.
	 */
	void finaliceSeat() {
		if (!isAccepted() && !isPromoter())
			setStatus(TravelStatus.WITHOUT_SEAT);
	}

	/*-----------------------------------------------------------------------
	 * 
	 * 
	 * Lógica de negocio
	 */

	public void acceptSeat() throws BusinessException {
		user.assertUserActive();
		trip.assertIsModifcable();
		this.assertAcceptSeat();
		setStatus(TravelStatus.ACCEPTED); // Esto debe ir antes
		trip.addAcceptedSeat(); // Que esto de aquí
	}

	public void cancelSeat() throws BusinessException {
		trip.assertIsModifcable();
		assertExcludeSeat();
		if (isAccepted())
			trip.removeAcceptedSeat();
		setStatus(TravelStatus.EXCLUDED); // Irrelevante.
		unlink();
	}

	public void excludeSeat() throws BusinessException {
		trip.assertIsModifcable();
		assertExcludeSeat();
		if (isAccepted()) {
			trip.removeAcceptedSeat();
			setStatus(TravelStatus.EXCLUDED);
		}

	}

	public void promoterSeat() throws BusinessException {
		user.assertUserActive();
		trip.assertIsModifcable();
		setStatus(TravelStatus.PROMOTER);
	}

	public void pendingSeat() throws BusinessException {
		user.assertUserActive();
		trip.assertIsModifcable();
		setStatus(TravelStatus.PENDING);
	}

	public boolean isPosibleAccept() {
		return getStatus() == TravelStatus.PENDING;
	}

	public boolean isAccepted() {
		return getStatus() == TravelStatus.ACCEPTED;
	}

	public boolean isPromoter() {
		return getStatus() == TravelStatus.PROMOTER;
	}

	public boolean isPosibleCancel() {
		return getStatus() == TravelStatus.ACCEPTED
				|| getStatus() == TravelStatus.PENDING;
	}

	// Asserts de negocio

	void assertAcceptSeat() throws BusinessException {
		if (!isPosibleAccept())
			throw new BusinessModelException("assert.bis_seatunaceptable");
	}

	void assertExcludeSeat() throws BusinessModelException {
		if (isPromoter())
			throw new BusinessModelException("assert.bis_seatuncacellable");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trip == null) ? 0 : trip.hashCode());
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
		Seat other = (Seat) obj;
		if (trip == null) {
			if (other.trip != null)
				return false;
		} else if (!trip.equals(other.trip))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

}

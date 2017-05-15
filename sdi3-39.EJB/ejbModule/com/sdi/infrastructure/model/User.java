package com.sdi.infrastructure.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.exception.BusinessModelException;
import com.sdi.infrastructure.model.encrypt.AttributeConverterImpl;
import com.sdi.infrastructure.model.types.UserStatus;

@Entity
@Table(name = "TUSERS")
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	private Long id;

	// Campos naturales
	@Column(name = "EMAIL")
	@XmlElement
	private String email;
	// Clave natural
	@Column(name = "LOGIN")
	@XmlElement
	private String login;
	@Column(name = "NAME")
	@XmlElement
	private String name;
	@Column(name = "SURNAME")
	@XmlElement
	private String surname;
	@Convert(converter = AttributeConverterImpl.class)
	@Column(name = "PASSWORD")
	@XmlElement
	private String password;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "STATUS")
	@XmlElement
	private UserStatus status;

	// Campos asociaciones	
	//Añadir , fetch = FetchType.EAGER
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@XmlTransient
	private Set<Trip> trips = new HashSet<Trip>();
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@XmlTransient
	private Set<Seat> seats = new HashSet<Seat>();
	@OneToMany(mappedBy = "applicants", fetch = FetchType.EAGER)
	@XmlTransient
	private Set<Application> applications = new HashSet<Application>();

	public User() {
	}

	public User(UserStatus status) {
		setStatus(status);
	}

	public User(String login, String name, String sname, String email, String p) {
		setLogin(login);
		setName(name);
		setSurname(sname);
		setEmail(email);
		setPassword(p);
		setStatus(UserStatus.CANCELLED);
	}

	// Métodos relación Trip.
	public void addTrip(Trip trip) {
		trip._setUser(this);
		trips.add(trip);
	}

	public void removeTrip(Trip trip) {
		trips.remove(trip);
		trip._setUser(null);
	}

	public Set<Trip> getTrips() {
		return Collections.unmodifiableSet(this.trips);
	}

	Set<Trip> _getTrips() {
		return trips;
	}

	void _setTrips(Set<Trip> trips) {
		this.trips = trips;
	}

	// Métodos relación Seats
	public void addSeat(Seat seat) {
		seat.setUser(this);
		seats.add(seat);
	}

	public void removeSeat(Seat seat) {
		seats.remove(seat);
		seat.setUser(null);
	}

	public Set<Seat> getSeats() {
		return Collections.unmodifiableSet(seats);
	}

	Set<Seat> _getSeats() {
		return seats;
	}

	void _setSeats(Set<Seat> seats) {
		this.seats = seats;
	}

	// Métodos relación Applications
	public void addApplication(Application app) {
		app.setUser(this);
		applications.add(app);
	}

	public void removeApplication(Application app) {
		applications.remove(app);
		app.setUser(null);
	}
	
	public Set<Application> getApplications() {
		return Collections.unmodifiableSet(applications);
	}

	Set<Application> _getApplications() {
		return applications;
	}

	void _setApplications(Set<Application> applications) {
		this.applications = applications;
	}

	// Métodos naturales
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
	/*-----------------------------------------------------------------
	 * ----------------------------------------------------------------
	 */
	// Métodos de lógica de negocio
	public void initialize() {
		setStatus(UserStatus.ACTIVE);
	}

	public void activeUser() throws BusinessException {
		assertUserDisable();
		setStatus(UserStatus.ACTIVE);
	}

	public void disableUser() throws BusinessException {
		assertUserActive();
		setStatus(UserStatus.CANCELLED);
		// Excluimos de los asientos al usuario cancelado.
		for (Seat seat : seats) {
			seat.excludeSeat();
		}
	}

	// Métodos que se pueden llamar desde la interfaz (beans)
	/**
	 * Este método permite obtener un asiento solicitado relacionado con
	 * determinado viaje. Si no existe devolverá un valor null
	 * 
	 * @param Trip
	 *            trip el viaje del cual se quire el asiento.
	 * @return Seat o null si no existe
	 * @throws BusinessException
	 */
	public Seat getSeatFromTrip(Trip trip) throws BusinessException {
		for (Seat seat : seats) {
			if (seat.getTrip().equals(trip))
				return seat;
		}
		throw new BusinessException("assert.trip_unexist");
	}

	public boolean isActive() {
		return getStatus() == UserStatus.ACTIVE;
	}

	// Métodos que se pueden llamar desde la capa de negocio

	public Set<Rating> getReceivedRatings() {
		Set<Rating> result = new HashSet<Rating>();
		for (Seat seat : seats) {
			result.addAll(seat._getAbout());
		}
		return result;
	}

	public void login(String password) throws BusinessException {
		assertUserActive();
		assertUserPass(password);
	}

	// Métodos privados de lógica de aplicación
	void assertUserActive() throws BusinessException {
		if (!isActive())
			throw new BusinessModelException("assert.bis_active");
	}

	void assertUserDisable() throws BusinessException {
		if (isActive())
			throw new BusinessModelException("assert.bis_disable");
	}

	void assertUserPass(String pass) throws BusinessException {
		if (!getPassword().equals(String.valueOf(pass.hashCode())))
			throw new BusinessModelException("assert.bis_userpassword");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname
				+ ", login=" + login + ", email=" + email + ", status="
				+ status + "]";
	}
}

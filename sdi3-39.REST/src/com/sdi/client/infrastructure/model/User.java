package com.sdi.client.infrastructure.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sdi.client.infrastructure.model.types.UserStatus;

@XmlRootElement(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String email;
	private String login;
	private String name;
	private String surname;
	private String password;
	private UserStatus status;

	private Set<Trip> trips = new HashSet<Trip>();
	private Set<Seat> seats = new HashSet<Seat>();
	private Set<Application> applications = new HashSet<Application>();
	
	public User() {
	}

	// Métodos naturales
	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlElement
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@XmlElement
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@XmlElement
	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
	@XmlElement
	public Set<Trip> getTrips() {
		return trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}
	@XmlElement
	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
	}
	@XmlElement
	public Set<Application> getApplications() {
		return applications;
	}

	public void setApplications(Set<Application> applications) {
		this.applications = applications;
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

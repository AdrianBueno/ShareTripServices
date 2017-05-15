package com.sdi.infrastructure.model;

import java.io.Serializable;

import com.sdi.infrastructure.model.types.UserStatus;


public class User implements Serializable {

	private static final long serialVersionUID = 1L;


	private Long id;
	private String email;
	private String login;
	private String name;
	private String surname;
	private String password;
	private UserStatus status;


	//private Set<Trip> trips = new HashSet<Trip>();
	//private Set<Seat> seats = new HashSet<Seat>();
	//private Set<Application> applications = new HashSet<Application>();

	public User() {}


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

	
}

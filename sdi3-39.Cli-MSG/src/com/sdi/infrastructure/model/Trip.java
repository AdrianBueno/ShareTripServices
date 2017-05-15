package com.sdi.infrastructure.model;

import java.io.Serializable;
import java.util.Date;

import com.sdi.infrastructure.model.types.AddressPoint;
import com.sdi.infrastructure.model.types.Waypoint;

/**
 * Esta clase implementa el modelo y la lógica de negocio relacionada con los
 * viajes.
 * 
 * @author sdi-39
 * @version 12 15/06
 */

public class Trip implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date arrivalDate;
	private Date closingDate;
	private Date departureDate;
	private Integer availablePax;
	private Double estimatedCost;
	private Integer maxPax;
	private String status;
	private AddressPoint departureAddress;
	private AddressPoint destinationAddress;
	private String comments;
	//private User user;
	//private Set<Seat> seats = new HashSet<Seat>();
	//private Set<Application> applications = new HashSet<Application>();
	
	public Trip() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Integer getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(Integer availablePax) {
		this.availablePax = availablePax;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AddressPoint getDepartureAddress() {
		if(departureAddress == null)
			initialize();
		return departureAddress;
	}

	public void setDepartureAddress(AddressPoint departureAddress) {
		this.departureAddress = departureAddress;
	}

	public AddressPoint getDestinationAddress() {
		if(destinationAddress == null)
			initialize();
		return destinationAddress;
	}

	public void setDestinationAddress(AddressPoint destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public void loadTrip(String trip){
		System.out.println(trip);
		String[] values = trip.split(":");
		System.out.println(values.length);
		setId(Long.parseLong(values[0]));
		setStatus(values[1].trim());
		getDepartureAddress().setCity(values[2].trim());
		getDestinationAddress().setCity(values[3].trim());
	}
	
	@Override
	public String toString() {
		return "id=" + id + ",arrivalDate=" + arrivalDate
				+ ",closingDate=" + closingDate + ",departureDate="
				+ departureDate + ",availablePax=" + availablePax
				+ ",estimatedCost=" + estimatedCost + ",maxPax=" + maxPax
				+ ",status=" + status
				+ "," + departureAddress.toString()
				+ "," + destinationAddress.toString() + ",comments="
				+ comments;
	}
	
	private void initialize(){
		setDepartureAddress(new AddressPoint());
		getDepartureAddress().setWaypoint(new Waypoint());
		setDestinationAddress(new AddressPoint());
		getDestinationAddress().setWaypoint(new Waypoint());
	}

	
	

}

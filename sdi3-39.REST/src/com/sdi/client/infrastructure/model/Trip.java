package com.sdi.client.infrastructure.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sdi.client.infrastructure.model.types.AddressPoint;
import com.sdi.client.infrastructure.model.types.TripStatus;

/**
 * Esta clase implementa el modelo y la lógica de negocio relacionada con los
 * viajes.
 * 
 * @author sdi-39
 * @version 12 15/06
 */
@XmlRootElement(name = "trip")
public class Trip implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date arrivalDate;
	private Date closingDate;
	private Date departureDate;
	private Integer availablePax;
	private Double estimatedCost;
	private Integer maxPax;
	private TripStatus status;

	
	private AddressPoint departureAddress;
	private AddressPoint destinationAddress;

	private String comments;

	//private User user;
	//private Set<Seat> seats;
	//private Set<Application> applications;

	public Trip() {
	}
	
	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@XmlElement
	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	@XmlElement
	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	@XmlElement
	public Integer getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(Integer availablePax) {
		this.availablePax = availablePax;
	}

	@XmlElement
	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	@XmlElement
	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}

	@XmlElement
	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	@XmlElement
	public AddressPoint getDepartureAddress() {
		return departureAddress;
	}

	public void setDepartureAddress(AddressPoint departureAddress) {
		this.departureAddress = departureAddress;
	}

	@XmlElement
	public AddressPoint getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(AddressPoint destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	@XmlElement
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Trip other = (Trip) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}

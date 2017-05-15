package com.sdi.client.infrastructure.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "rating")
public class Rating implements Serializable {

	private static final long serialVersionUID = 4173965992975438613L;

	private Long id;
	
	private String comment;
	private Integer value;
	private Seat aboutSeat;
	private Seat fromSeat;



	public Long getId() {
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	

	// Métodos relación AboutSeat
	public Seat getAboutSeat() {
		return aboutSeat;
	}



	// Métodos relación FromSeat
	public Seat getFromSeat() {
		return fromSeat;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aboutSeat == null) ? 0 : aboutSeat.hashCode());
		result = prime * result
				+ ((fromSeat == null) ? 0 : fromSeat.hashCode());
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
		Rating other = (Rating) obj;
		if (aboutSeat == null) {
			if (other.aboutSeat != null)
				return false;
		} else if (!aboutSeat.equals(other.aboutSeat))
			return false;
		if (fromSeat == null) {
			if (other.fromSeat != null)
				return false;
		} else if (!fromSeat.equals(other.fromSeat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rating [value=" + value + ", comment=" + comment + "]";
	}
}

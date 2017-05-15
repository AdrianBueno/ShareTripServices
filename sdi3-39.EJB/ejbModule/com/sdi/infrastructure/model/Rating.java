package com.sdi.infrastructure.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "TRATINGS")
@XmlRootElement(name = "rating")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rating implements Serializable {

	private static final long serialVersionUID = 4173965992975438613L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	private Long id;

	@Column(name = "COMMENT")
	@XmlElement
	private String comment;

	@Column(name = "VALUE")
	@XmlElement
	private Integer value;

	@ManyToOne
	@XmlElement
	private Seat aboutSeat;

	@ManyToOne
	@XmlElement
	private Seat fromSeat;

	public Rating() {
	}

	public Rating(String comment, Integer value, Seat aboutSeat, Seat fromSeat) {
		super();
		
		this.comment = comment;
		this.value = value;
		this.aboutSeat = aboutSeat;
		this.fromSeat = fromSeat;
		aboutSeat._getAbout().add(this);
		fromSeat._getFrom().add(this);
	}
	
	public void unlink() {
		aboutSeat._getAbout().remove(this);
		fromSeat._getFrom().remove(this);
		
		this.aboutSeat = null;
		this.fromSeat = null;
	}

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


//	void _setAboutSeat(Seat aboutSeat) {
//		this.aboutSeat = aboutSeat;
//	}

	// Métodos relación FromSeat
	public Seat getFromSeat() {
		return fromSeat;
	}

//	void _setFromSeat(Seat fromSeat) {
//		this.fromSeat = fromSeat;
//	}

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

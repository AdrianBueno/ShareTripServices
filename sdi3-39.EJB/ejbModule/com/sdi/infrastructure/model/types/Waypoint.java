package com.sdi.infrastructure.model.types;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Embeddable
@XmlRootElement(name = "waypoint")
public class Waypoint implements Serializable {
	
	private static final long serialVersionUID = -8851759432075504881L;
	
	private double wptLat;
	private double wptLon;
	
	public Waypoint(){}
	@XmlElement
	public double getWptLat() {
		return wptLat;
	}
	public void setWptLat(double wptLat) {
		this.wptLat = wptLat;
	}
	@XmlElement
	public double getWptLon() {
		return wptLon;
	}
	public void setWptLon(double wptLon) {
		this.wptLon = wptLon;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(wptLat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(wptLon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Waypoint other = (Waypoint) obj;
		if (Double.doubleToLongBits(wptLat) != Double
				.doubleToLongBits(other.wptLat))
			return false;
		if (Double.doubleToLongBits(wptLon) != Double
				.doubleToLongBits(other.wptLon))
			return false;
		return true;
	}
	
	
	
	

}

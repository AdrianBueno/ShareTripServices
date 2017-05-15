package com.sdi.persistence.finder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import com.sdi.infrastructure.model.Rating;
import com.sdi.persistence.Jpa;

public class RatingFinder {
	public RatingFinder() {
	}

	public static List<Rating> listLastMonthRatings() {
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.add(Calendar.MONTH, -1);
		Date pastDate = cal.getTime();
		return Jpa
				.getManager()
				.createNamedQuery("Rating.findRatingsBetweenDates",
						Rating.class)
				.setParameter("startDate", pastDate, TemporalType.TIMESTAMP)
				.setParameter("finishDate", now, TemporalType.TIMESTAMP)
				.getResultList();
	}

	public static List<Rating> listRatings() {
		return  Jpa.getManager()
				.createNamedQuery("Rating.findAll", Rating.class).getResultList();
	}
}

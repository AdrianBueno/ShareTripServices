package com.sdi.infrastructure.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.sdi.infrastructure.model.types.AddressPoint;
import com.sdi.infrastructure.model.types.TravelStatus;
import com.sdi.infrastructure.model.types.TripStatus;
import com.sdi.infrastructure.model.types.UserStatus;
import com.sdi.infrastructure.model.types.Waypoint;

public class ResetJpa {
	private static int NUM_USERS = 4;
	private static int NUM_DONE = 5;
	private static int NUM_OPEN = 5;
	private static int AVAILABLE_PAX = 2;
	private static String[] COMMENTS = {"Good","Meh","nice", "Shit"};
	
	private Calendar cal;
	
	private List<User> users = new ArrayList<User>();
	public ResetJpa() {
		cal = Calendar.getInstance();
		
	}
	
	public List<User> createBaseModel(){
		createUsers();
		createTripsDone();
		createTripsOpen();
		createSeats();
		createRating();
		return users; //Para recorrer y persistir
	}
	
	private void createUsers() {
		for(int i = 1; i < NUM_USERS+1; i++){
			User user = new User();
			user.setLogin("usuario"+i);
			user.setName("name"+i);
			user.setPassword("usuario"+i);
			user.setEmail("usuario"+i+"@email");
			user.setSurname("surname"+i);
			user.setStatus(UserStatus.ACTIVE);
			users.add(user);
		}
		
	}

	private void createTripsDone() {
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.MAY);
		cal.set(Calendar.DATE, 1);
		for(User user : users){
			for(int i = 0; i < NUM_DONE; i++){
				Trip trip = new Trip();
				trip.setDepartureAddress(createAddressPoint());
				trip.setDestinationAddress(createAddressPoint());
				
				cal.add(Calendar.DATE, (int)(Math.random()*2)+1);
				trip.setClosingDate(cal.getTime());
				
				cal.add(Calendar.DATE, (int)(Math.random()*2)+1);
				trip.setDepartureDate(cal.getTime());
				
				cal.add(Calendar.HOUR_OF_DAY, (int)(Math.random()*12)+1);
				cal.add(Calendar.MINUTE, i+1); //Esto debería evitar conflicto
				trip.setArrivalDate(cal.getTime());
				
				trip.setMaxPax(AVAILABLE_PAX+1);
				trip.setAvailablePax(0);
				int cost = (int)(Math.random()*100)+5;
				trip.setEstimatedCost((double)cost);
				trip.setStatus(TripStatus.DONE);
				
				user.addTrip(trip);
			}
		}		
	}
	private void createTripsOpen() {
		cal.set(Calendar.YEAR, 2016);
		cal.set(Calendar.MONTH, Calendar.JULY);
		cal.set(Calendar.DATE, 5);
		for(User user : users){
			for(int i = 0; i < NUM_OPEN; i++){
				Trip trip = new Trip();
				trip.setDepartureAddress(createAddressPoint());
				trip.setDestinationAddress(createAddressPoint());
				
				cal.add(Calendar.DATE, (int)(Math.random()*2)+1);
				trip.setClosingDate(cal.getTime());
				
				cal.add(Calendar.DATE, (int)(Math.random()*2)+1);
				trip.setDepartureDate(cal.getTime());
				
				cal.add(Calendar.HOUR_OF_DAY, (int)(Math.random()*12)+1);
				cal.add(Calendar.MINUTE, i+1); //Esto debería evitar conflicto
				trip.setArrivalDate(cal.getTime());
				
				trip.setMaxPax(AVAILABLE_PAX+1);
				trip.setAvailablePax(AVAILABLE_PAX);
				int cost = (int)(Math.random()*100)+5;
				trip.setEstimatedCost((double)cost);
				trip.setStatus(TripStatus.OPEN);
				
				user.addTrip(trip);
			}
		}		
	}
	
	
	private void createSeats(){
		for(User user : users){ //Por cada usuario
			for(Trip trip : user.getTrips()){ //Por cada viaje
				Seat promoter = new Seat(trip, user);
				promoter.setStatus(TravelStatus.PROMOTER); //Creamos seat base
				int seats = 0;
				if(trip.getStatus() == TripStatus.DONE)
					for(User applicant : users){ // Por cada usuario
						if(!user.equals(applicant)){ //Que no sea el promotor
							new Application(trip, applicant);
							Seat seat = new Seat(trip, applicant); //Creamos aply
							if(seats < AVAILABLE_PAX && Math.random()*10 > 4){
								seat.setStatus(TravelStatus.ACCEPTED);
								System.err.println("ACCEPTED");
								seats++;
							}else
								seat.setStatus(TravelStatus.WITHOUT_SEAT);
						}
				}
			}
		}
	}
	
	private void createRating(){
		for(User user : users){
			for(Trip trip : user.getTrips()){
				if(trip.getStatus() == TripStatus.DONE)
					for(Seat seatAbout : trip.getSeats()){
						if(seatAbout.getStatus() == TravelStatus.ACCEPTED 
								|| seatAbout.getStatus() == TravelStatus.PROMOTER){
							for(Seat seatFrom : trip.getSeats()){
								if((seatAbout.getStatus() == TravelStatus.ACCEPTED 
										|| seatAbout.getStatus() == TravelStatus.PROMOTER) 
										&& !seatAbout.equals(seatFrom))
									new Rating(COMMENTS[(int)(Math.random()*3)],
											(int)Math.random()*6,
											seatAbout,
											seatFrom);
							}
						}
					}
				}
		}
	}

	private AddressPoint createAddressPoint(){
		AddressPoint ap = new AddressPoint();
		ap.setAddress("address");
		ap.setCity("city");
		ap.setState("state");
		ap.setCountry("country");
		ap.setWaypoint(new Waypoint());
		ap.setZipCode("zipcode");
		return ap;
	}
	

}

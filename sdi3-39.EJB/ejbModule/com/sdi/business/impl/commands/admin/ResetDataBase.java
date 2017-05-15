package com.sdi.business.impl.commands.admin;

import java.util.List;

import com.sdi.business.impl.commands.Command;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Application;
import com.sdi.infrastructure.model.Rating;
import com.sdi.infrastructure.model.ResetJpa;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.persistence.Jpa;
import com.sdi.persistence.finder.ApplicationFinder;
import com.sdi.persistence.finder.RatingFinder;
import com.sdi.persistence.finder.SeatFinder;
import com.sdi.persistence.finder.TripFinder;
import com.sdi.persistence.finder.UserFinder;
/**
 * 
 * @author sdi3-39
 *
 */
public class ResetDataBase implements Command {

	
	private List<Trip> allTrips;
	private List<User> allUsers;
	private List<Application> allApplies;
	private List<Seat> allSeats;
	private List<Rating> allRatings;
	
	
	
	
	@Override
	public Object execute() throws BusinessException {
		
		allTrips = TripFinder.listTrips();
		allUsers = UserFinder.listUsers();
		allApplies = ApplicationFinder.listApplications();
		allSeats = SeatFinder.listSeast();
		allRatings = RatingFinder.listRatings();
		removeAll();
		
		
		List<User> users = new ResetJpa().createBaseModel();
		persistAll(users);

		return null;
	}

	private void persistAll(List<User> users){
		for(User user : users){
			Jpa.getManager().persist(user);
		}
		for(User user : users){
			for(Trip trip : user.getTrips()){
				Jpa.getManager().persist(trip);
			}
			for(Trip trip : user.getTrips()){
				for(Application apply : trip.getApplications()){
					Jpa.getManager().persist(apply);
				}
				for(Seat seat : trip.getSeats()){
					Jpa.getManager().persist(seat);
					System.err.println(seat.getStatus());
				}
				for(Seat seat : trip.getSeats()){
					System.err.println(seat.getStatus());
					for(Rating rat : seat.getAbout()){
						Jpa.getManager().persist(rat);
					}
				}
				
			}
		}
		
	}
	
	
	private void removeAll(){
		for(Rating rat : allRatings)
			Jpa.getManager().remove(rat);
		
		for(Application apply : allApplies)
			Jpa.getManager().remove(apply);
		
		for(Seat seat : allSeats)
			Jpa.getManager().remove(seat);
		
		for(Trip trip : allTrips)
			Jpa.getManager().remove(trip);
		
		for(User user : allUsers)
			Jpa.getManager().remove(user);
	}
	

}

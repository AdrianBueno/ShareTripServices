package com.sdi.persistence.finder;

import java.util.List;

import com.sdi.infrastructure.model.Application;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.persistence.Jpa;
/**
 * Esta clase se encarga de acceder a los datos de la BD de peticiones de plaza
 * @author sdi-39
 * @version 2 15/06
 */
public class ApplicationFinder {
	
	private ApplicationFinder(){}
	
	/**
	 * Obtiene una determinada petición
	 * @param trip fuente
	 * @param user fuente
	 * @return Application la petición
	 */
	public static Application getApplication(Trip trip, User user){
		List<Application> list = Jpa.getManager()
		.createNamedQuery("Application.findApply",Application.class)
		.setParameter(1, trip)
		.setParameter(2,user).getResultList();
		return list.size() > 0 ? list.get(0) : null;
	}
	/**
	 * Lista las peticiones de la bd
	 * @return Lista de peticiones
	 */
	public static List<Application> listApplications(){
		return  Jpa.getManager()
		.createNamedQuery("Application.findAll",Application.class)
		.getResultList();
	}

}

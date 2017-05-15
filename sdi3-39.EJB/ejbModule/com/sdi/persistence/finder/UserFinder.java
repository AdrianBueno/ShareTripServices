package com.sdi.persistence.finder;

import java.util.List;

import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.persistence.Jpa;

/**
 * Se encarga de acceder a los usuarios del modelo de dominio
 * @author sdi2-39
 */
public class UserFinder {
	
	private UserFinder(){}
	
	/**
	 * Lista todos los usuarios de la BD.
	 * @return List<User> Interfaz List con los usuarios
	 */
	public static List<User> listUsers(){
		return Jpa.getManager()
				.createNamedQuery("User.findAll",User.class)
				.getResultList();
	}
	
	/**
	 * Devuelve el usuario con el login dado.
	 * @param String Login del usuario
	 * @return User Usuario al que corresponde el login
	 */
	public static User getUserByLogin(String str){
		List<User> list = Jpa.getManager()
				.createNamedQuery("User.findByLogin",User.class)
				.setParameter(1, str)
				.getResultList();
		return list.size() > 0 ? list.get(0) : null;
	}
	
	/**
	 * Devuelve el usuario con el email dado.
	 * @param String Email del usuario
	 * @return User Usuario al que corresponde el Email
	 */
	public static User getUserByEmail(String str){
		List<User> list = Jpa.getManager()
				.createNamedQuery("User.findByEmail",User.class)
				.setParameter(1, str)
				.getResultList();
		return list.size() > 0 ? list.get(0) : null;
	}
	/**
	 * 
	 * @param Trip trip con el viaje del que se solicitan los aplicantes.
	 * @return List<User> Lista con los usuarios que han pedido unirse al viaje
	 */
	public static List<User> getApplicants(Trip trip){
		return Jpa.getManager()
				.createNamedQuery("User.findApplicants",User.class)
				.setParameter(1, trip)
				.getResultList();
		
	}
	/**
	 * Lista de usuarios pendientes de un viaje
	 * @return Lista de plazas
	 */
	public static List<User> listUsersPending(Trip trip){
		return (List<User>) Jpa.getManager()
				.createNamedQuery("User.findPending",User.class)
				.setParameter(1, trip).getResultList();	
	}
	
	

}

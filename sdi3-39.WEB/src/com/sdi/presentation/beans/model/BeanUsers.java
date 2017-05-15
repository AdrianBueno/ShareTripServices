package com.sdi.presentation.beans.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.UsersService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.presentation.beans.support.BeanMessages;
import com.sdi.presentation.beans.support.BeanSession;

/* Este ManagedBean está retirado en la funcionalidad de la práctica 2
 * Pues serviría para manejar el conjunto de usuarios del sistema (borrar,
 * actualizar, activar) acciones realizables por un administrador.
 */

/**
 * Esta clase es una managedBean encarga de manejar la lista de usuarios del 
 * sistema en la relación con la vista
 * @author sdi-39
 * @version 3 15/06
 */
//@ManagedBean(name = "beanUsers")
//@SessionScoped
public class BeanUsers implements Serializable {
	private static final long serialVersionUID = -5958138068114142788L;
	private static final UsersService us = Factories.services.getUsersService();
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(BeanUsers.class);
	}
	@ManagedProperty("#{beanSession}")
	private BeanSession beanSession;

	@ManagedProperty("#{beanMessages}")
	private BeanMessages beanMessages;
	
	@ManagedProperty("#{beanUser}")
	private BeanUser beanUser;
	
	private List<User> users;

	public BeanUsers() {
		log.info(Msg.getStr("info.instanced"));
	}
	
	public void updateUsers(){
		log.info("Actualizando lista de usuarios");
		try{
			users = us.listUsers();
		} catch(BusinessException e){
			createExceptionMessages(e);
		}
	}
	
	private void createExceptionMessages(BusinessException e){
		String msg = beanMessages.getMessage("msgs", e.getMessage());
		log.warn(msg);
		beanMessages.addWarnMsg(msg, beanSession.getCurrent());
	}

	public BeanSession getBeanSession() {
		return beanSession;
	}

	public void setBeanSession(BeanSession beanSession) {
		this.beanSession = beanSession;
	}
	
	public BeanMessages getBeanMessages() {
		return beanMessages;
	}

	public void setBeanMessages(BeanMessages beanMessages) {
		this.beanMessages = beanMessages;
	}
	
	public BeanUser getBeanUser() {
		return beanUser;
	}

	public void setBeanUser(BeanUser beanUser) {
		this.beanUser = beanUser;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}	

}

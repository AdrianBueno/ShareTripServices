package com.sdi.presentation.beans.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.UsersService;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.model.types.UserStatus;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.presentation.beans.support.BeanMessages;
import com.sdi.presentation.beans.support.BeanSession;

/**
 * 
 * Esta clase es un ManagedBean que se encarga de manejar las acciones
 * realizadas desde la vista sobre un usuario del sistema.
 * @author sdi-39
 * @version 2 16/06
 *
 */
@ManagedBean(name = "beanUser")
@SessionScoped
public class BeanUser implements Serializable {
	private static final long serialVersionUID = -5385330324737073736L;
	@SuppressWarnings("unused")
	private static final UsersService us = Factories.services.getUsersService();
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(BeanUser.class);
	}
	
	@ManagedProperty("#{beanSession}")
	private BeanSession beanSession;
	
	@ManagedProperty("#{beanMessages}")
	private BeanMessages beanMessages;

	private User selectedUser;

	public BeanUser() {
		log.info(Msg.getStr("info.instanced"));
	}
	public void newUser(){
		selectedUser = new User(UserStatus.CANCELLED);
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User user) {
		log.info("Se ha cambiado el usuario seleccionado");
		this.selectedUser = user;
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
	
}

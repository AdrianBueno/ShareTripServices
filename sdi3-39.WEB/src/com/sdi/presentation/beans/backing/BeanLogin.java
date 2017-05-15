package com.sdi.presentation.beans.backing;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Size;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.UsersService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.presentation.beans.support.BeanMessages;
import com.sdi.presentation.beans.support.BeanSession;
import com.sdi.presentation.navigation.NavAction;

@ManagedBean(name = "beanLogin")
@RequestScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 5821489968790717402L;
	private static final UsersService us = Factories.services.getUsersService();
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(BeanLogin.class);
	}
	@ManagedProperty("#{beanSession}")
	private BeanSession beanSession;
	
	@ManagedProperty("#{beanMessages}")
	private BeanMessages beanMessages;
	
	@Size(min= 1, max=20)
	private String login;
	@Size(min= 1, max=20)
	private String pass;

	public BeanLogin() {
		log.info(Msg.getStr("info.instanced"));
	}

	
	public String dologin(){
		log.info("Intentando iniciar sesión");
		if(beanSession.isSessionInit())
			return NavAction.TRIPS;
		try {
			User user = us.loginUser(getLogin(), getPass());
			beanSession.loginUser(user);
			return NavAction.TRIPS;
		} catch (BusinessException e) {
			createExceptionMessages(e);
			return NavAction.NO_MOVE;
		}
	}
	
	public String logout(){
		log.info("Intentando cerrar sesión");
		if(beanSession.isSessionOut())
			return NavAction.LOGOUT;
		beanSession.logoutUser();
		beanMessages.addInfoMsg(
				beanMessages.getMessage("msgs", "msg_logoff"), 
				beanSession.getCurrent());
		return NavAction.LOGOUT;
	}
	


	
	private void createExceptionMessages(BusinessException e){
		String msg = beanMessages.getMessage("msgs", e.getMessage());
		log.warn(msg);
		beanMessages.addWarnMsg(msg, beanSession.getCurrent());
	}
	

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass1) {
		this.pass = pass1;
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

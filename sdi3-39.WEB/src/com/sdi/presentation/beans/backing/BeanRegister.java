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

@ManagedBean(name = "beanRegister")
@RequestScoped
public class BeanRegister implements Serializable {
	private static final long serialVersionUID = 4510625541895574688L;
	private static final UsersService us = Factories.services.getUsersService();
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(BeanRegister.class);
	}
	
	@ManagedProperty("#{beanSession}")
	private BeanSession beanSession;
	
	@ManagedProperty("#{beanMessages}")
	private BeanMessages beanMessages;
	
	private User newUser;
	@Size(min=1, max=200)
	private String pass1;
	@Size(min=1, max=200)
	private String pass2;
	
	public BeanRegister(){
		newUser = new User();
		log.info(Msg.getStr("info.instanced"));
	}
	
	public String register(){
		log.info("Intentando registrar usuario");
		if(beanSession.isSessionInit())
			return NavAction.NO_MOVE;
		try {
			newUser.setPassword(pass1);
			us.RegisterUser(newUser);
			beanMessages.addInfoMsg(
					beanMessages.getMessage("msgs", "msg_register_confirm"), 
					beanSession.getCurrent());
			newUser = new User();
			return NavAction.NO_MOVE;
		} catch (BusinessException e) {
			createExceptionMessages(e);
			return NavAction.NO_MOVE;
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

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public String getPass1() {
		return pass1;
	}

	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	
	
	
	
	
	

}

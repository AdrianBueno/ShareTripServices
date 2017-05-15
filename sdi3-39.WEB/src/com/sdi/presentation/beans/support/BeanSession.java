package com.sdi.presentation.beans.support;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.services.io.Msg;

/**
 * Esta clase hará de controlador para gestionar la sesión de usuario actual.
 * @author sdi2-39
 * @version 27/03
 */
@ManagedBean(name = "beanSession")
@SessionScoped
public class BeanSession implements Serializable {
	private static final long serialVersionUID = -8312080973434488099L;
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(BeanSession.class);
	}

	private ExternalContext externalContext;
	private Map<String, Object> sessionMap;
	private Boolean sessionInit = false;

	public BeanSession() {
		externalContext = getCurrent().getExternalContext();
		sessionMap = externalContext.getSessionMap();
		sessionMap.put("beanSession", this);
		log.info(Msg.getStr("info.instanced"));
	}

	public void loginUser(User user){
		log.info("Se ha solicitado iniciar la sesión");
		sessionMap.put("usuario", user);
		sessionInit = true;
	}
	public void logoutUser(){
		log.info("Se ha solicitado cerrar la sesión");
		sessionMap.put("usuario", null);
		sessionInit = false;
	}
	
	public User getCurrentUser(){
		return (User)sessionMap.get("usuario");
	}

	public Boolean isSessionInit() {
		return sessionInit;
	}
	public Boolean isSessionOut(){
		return !sessionInit;
	}

	public void setSessionInit(Boolean sessionInit) {
		this.sessionInit = sessionInit;
	}

	public ExternalContext getExternalContext() {
		return externalContext;
	}

	public FacesContext getCurrent() {
		return FacesContext.getCurrentInstance();
	}
	
	void _setExternalContext(ExternalContext externalContext) {
		this.externalContext = externalContext;
	}

	public Boolean getSessionInit() {
		return sessionInit;
	}

	

}

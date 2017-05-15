package com.sdi.presentation.beans.support;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Esta clase es una ManagedBean encargado de manejar los errores
 * de la aplicación en modo debug y de usuario.
 * @author sdi-39
 * @version 1
 */
@ManagedBean(name = "beanErrorHandler")
@RequestScoped
public class BeanErrorHandler implements Serializable {
	private static final long serialVersionUID = 5074016429755846596L;

	public String getStatusCode() {
		String str = String.valueOf((Integer) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap()
				.get("javax.servlet.error.status_code"));
		if(str == null)
			str = "";
		return str;
	}

	public String getMessage() {
		String str = (String) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap()
				.get("javax.servlet.error.message");
		if(str == null)
			str = "";
		return str;
	}

	public String getExceptionType() {
		String str = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get("javax.servlet.error.exception_type")
				.toString();
		if(str == null)
			str = "";
		return str;
	}

	public String getException() {
		String str = (String) ((Exception) FacesContext.getCurrentInstance()
				.getExternalContext().getRequestMap()
				.get("javax.servlet.error.exception")).toString();
		if(str == null)
			str = "";
		return str;
	}

	public String getRequestURI() {
		String str = (String) FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get("javax.servlet.error.request_uri");
		if(str == null)
			str = "";
		return str;
	}

	public String getServletName() {
		String str =  (String)FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().get("javax.servlet.error.servlet_name");
		if(str == null)
			str = "";
		return str;
	}

}

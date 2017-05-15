package com.sdi.presentation.beans.support;

import java.io.Serializable;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.sdi.infrastructure.io.Msg;

/**
 * Esta managedBean se encarga de obtener los diferentes mensajes usados en la
 * vista.
 * @author sdi-39
 * @version 2 10/06
 *
 */
@ManagedBean(name = "beanMessages")
@ApplicationScoped
public class BeanMessages implements Serializable {
	
	private static final long serialVersionUID = -8901731368036984762L;
	private static List<String> cities;
	private static List<String> provinces;
	private static List<String> countries;
	private static FacesMessage info;
	private static FacesMessage warning;
	private static FacesMessage error;
	private static FacesMessage fatal;
	
	static{
		cities = Msg.getCities();
		provinces = Msg.getProvinces();
		countries = Msg.getCountries();
		info = new FacesMessage();
		warning = new FacesMessage();
		error = new FacesMessage();
		fatal = new FacesMessage();
		info.setSeverity(FacesMessage.SEVERITY_INFO);
		warning.setSeverity(FacesMessage.SEVERITY_WARN);
		error.setSeverity(FacesMessage.SEVERITY_ERROR);
		fatal.setSeverity(FacesMessage.SEVERITY_FATAL);
		
	}
	/**
	 * Añade al facesContext un mensaje enviado como parametro
	 * de tipo info
	 * @param msg String el mensaje enviado
	 * @param fc El faceContext asociado
	 */
	public synchronized void  addInfoMsg(String msg, FacesContext fc){
		info.setDetail(msg);
		
		fc.addMessage(null, info);
	}
	/**
	 * Añade al facesContext un mensaje enviado como parametro
	 * de tipo warn
	 * @param msg String el mensaje enviado
	 * @param fc El faceContext asociado
	 */
	public synchronized void addWarnMsg(String msg, FacesContext fc){
		warning.setDetail(msg);
		fc.addMessage(null, warning);
	}
	/**
	 * Añade al facesContext un mensaje enviado como parametro
	 * de tipo error
	 * @param msg String el mensaje enviado
	 * @param fc El faceContext asociado
	 */
	public synchronized void addErrorMsg(String msg, FacesContext fc){
		error.setDetail(msg);
		fc.addMessage(null, error);
	}
	/**
	 * Añade al facesContext un mensaje enviado como parametro
	 * de tipo fatal
	 * @param msg String el mensaje enviado
	 * @param fc El faceContext asociado
	 */
	public synchronized void addFatalMsg(String msg, FacesContext fc){
		fatal.setDetail(msg);
		fc.addMessage(null, fatal);
	}
	
	/**
	 * Obtieen un mensaje del resourceBundle dado por dos String
	 * @param name el resource bundle
	 * @param key la key correspondiente a una entrada
	 * @return String el parámetro
	 * @throws MissingResourceException si no se encuentra el recurso
	 */
	public synchronized String getMessage(String name, String key) throws MissingResourceException{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext
				.getApplication()
				.getResourceBundle(facesContext, "msgs");
		if(bundle.containsKey(key))
			return bundle.getString(key);
		else
			return "";
	}
	
	/**
	 * Devuelve una lista de ciudades
	 * @return List<String>
	 */
	public List<String> getCities(){
		return cities;
	}
	/**
	 * Devuelve una lista de provincias
	 * @return List<String>
	 */
	public List<String> getProvinces(){
		return provinces;
	}
	/**
	 * Devuelve una lista de paises
	 * @return List<String>
	 */
	public List<String> getCountries(){
		return countries;
	}
	
	
}


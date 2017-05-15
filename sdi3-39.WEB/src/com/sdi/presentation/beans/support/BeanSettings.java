package com.sdi.presentation.beans.support;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.i18n.ILanguageService;
import com.sdi.infrastructure.i18n.LanguageService;
import com.sdi.infrastructure.services.io.Msg;
/**
 * Este managedBean se encarga de controlar las opciones
 * como el idioma asociado a una sesión.
 * @author sdi-39
 * @version 2 5/04
 *
 */
@ManagedBean(name = "settings")
@SessionScoped
public class BeanSettings implements Serializable {
	private static final long serialVersionUID = -818367645190405771L;
	private static final ILanguageService languageService;
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(BeanSettings.class);
		languageService = new LanguageService();
	}	
	
	private Map<String,Locale> localeMap;
	private Set<String> localeNames;
	private Locale locale;
	private String selected;
	
	public BeanSettings(){
		localeMap = languageService.getLocaleMap();
		localeNames = localeMap.keySet();
		locale = languageService.getDefaultLocale();
		selected = locale.getDisplayName();
		log.info("Idioma por defecto: " + selected);
		log.info(Msg.getStr("info.instanced")); //$NON-NLS-1$
	}
	
	public void changeLanguage(){
		log.info("Intentando cambiar el lenguaje a: "+selected); //$NON-NLS-1$
		if(selected != null && localeMap.keySet().contains(selected)){
			locale = localeMap.get(selected);
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			log.info("Lenguaje cambiado con éxito"); //$NON-NLS-1$
		}else
			log.error("Lenguaje nulo o incorrecto"); //$NON-NLS-1$
	}
	
	public void resetDataBase(){
		try {
			Factories.services.getAdminService().resetDataBase();
		} catch (BusinessException e) {
			log.info("ERror al resetear Database");
		}
	}

	public Map<String, Locale> getLocaleMap() {
		return localeMap;
	}

	public void setLocaleMap(Map<String, Locale> localeMap) {
		this.localeMap = localeMap;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		if(!this.selected.equals(selected)){
			this.selected = selected;
			changeLanguage();
		}
	}

	public Locale getLocale() {
		
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Set<String> getLocaleNames() {
		return localeNames;
	}

	public void setLocaleNames(Set<String> localeNames) {
		this.localeNames = localeNames;
	}
	
	
}

package com.sdi.presentation.beans.backing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.infrastructure.services.io.Msg;
import com.sdi.presentation.beans.support.BeanMessages;

/**
 * Esta clase es un managedBean que se encarga de proveer la funcionalidad
 * de autocompletado a diversos campos de la vista
 * @author sdi-39
 * @version 1 14/06
 */
@ManagedBean(name = "beanAutocomplete")
@RequestScoped
public class BeanAutocomplete implements Serializable {

	private static final long serialVersionUID = 7562347562884723396L;
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(BeanAutocomplete.class);
	}
	
	
	@ManagedProperty("#{beanMessages}")
	private BeanMessages beanMessages;
	
	
	public BeanAutocomplete() {
		log.info(Msg.getStr("info.instanced"));
	}
	
	public List<String> filterCityQuery(String query){
		List<String> filter = new ArrayList<String>();
		
		for(String city : beanMessages.getCities()){
			if(city.toLowerCase().startsWith(query))
				filter.add(city);
		}
		return filter;
	}
	
	public List<String> filterStateQuery(String query){
		List<String> filter = new ArrayList<String>();
		
		for(String city : beanMessages.getProvinces()){
			if(city.toLowerCase().startsWith(query))
				filter.add(city);
		}
		return filter;
	}
	
	public List<String> filterCountryQuery(String query){
		List<String> filter = new ArrayList<String>();
		
		for(String city : beanMessages.getCountries()){
			if(city.toLowerCase().startsWith(query))
				filter.add(city);
		}
		return filter;
	}


	public BeanMessages getBeanMessages() {
		return beanMessages;
	}


	public void setBeanMessages(BeanMessages beanMessages) {
		this.beanMessages = beanMessages;
	}

}

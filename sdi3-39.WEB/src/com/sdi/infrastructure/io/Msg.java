package com.sdi.infrastructure.io;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class Msg {
	private static final ResourceBundle RESOURCE_LOG;
	private static final ResourceBundle RESOURCE_AUTO_CITY;
	private static final ResourceBundle RESOURCE_AUTO_PROV;
	private static final ResourceBundle RESOURCE_AUTO_COUNT;
	static{
		RESOURCE_LOG = ResourceBundle.getBundle("log_msg");
		RESOURCE_AUTO_CITY = ResourceBundle.getBundle("auto_cities_msg");
		RESOURCE_AUTO_PROV = ResourceBundle.getBundle("auto_provinces_msg");
		RESOURCE_AUTO_COUNT = ResourceBundle.getBundle("auto_countries_msg");
	}
	
	
	
	
	private Msg() {
	}

	public static String getStr(String key) {
		try {
			return RESOURCE_LOG.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	public static List<String> getCities(){
		List<String> list = new ArrayList<String>();
		try {
			for(String key: RESOURCE_AUTO_CITY.keySet())
				list.add(RESOURCE_AUTO_CITY.getString(key));
			return list;
		} catch (MissingResourceException e) {
			throw e;
		}
	}
	public static List<String> getProvinces(){
		List<String> list = new ArrayList<String>();
		try {
			for(String key: RESOURCE_AUTO_PROV.keySet())
				list.add(RESOURCE_AUTO_PROV.getString(key));
			return list;
		} catch (MissingResourceException e) {
			throw e;
		}
	}
	public static List<String> getCountries(){
		List<String> list = new ArrayList<String>();
		try {
			for(String key: RESOURCE_AUTO_COUNT.keySet())
				list.add(RESOURCE_AUTO_COUNT.getString(key));
			return list;
		} catch (MissingResourceException e) {
			throw e;
		}
	}
	
	
	/**
	 * Obtieen un mensaje del resourceBundle dado por dos String
	 * @param name el resource bundle
	 * @param key la key correspondiente a una entrada
	 * @return String el parámetro
	 * @throws MissingResourceException si no se encuentra el recurso
	 */
	public static synchronized String getMessage(String name, String key) 
			throws MissingResourceException{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext
				.getApplication()
				.getResourceBundle(facesContext, name);
		if(bundle.containsKey(key))
			return bundle.getString(key);
		else
			return "";
	}
}

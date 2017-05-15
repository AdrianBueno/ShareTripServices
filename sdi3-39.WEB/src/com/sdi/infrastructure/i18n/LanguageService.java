package com.sdi.infrastructure.i18n;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

public class LanguageService implements ILanguageService {
	private static Map<String, Locale> languages;
	private static Locale def;
	private static final Logger log;
	static{
		log = Log4jLogger.getLogger(LanguageService.class);
		languages = new HashMap<String,Locale>();
	}

	public LanguageService(){
		def = FacesContext
				.getCurrentInstance()
				.getApplication()
				.getDefaultLocale();
		Iterator<Locale> it = FacesContext
				.getCurrentInstance()
				.getApplication()
				.getSupportedLocales();
		while(it.hasNext()){
			Locale locale = it.next();
			log.info("Cargando idioma: "+locale.getDisplayName());
			languages.put(locale.getDisplayName(), locale);
		}
		languages.put(def.getDisplayName(), def);
		log.info("Cargando idioma: "+def.getDisplayName());
	}
	@Override
	public Map<String, Locale> getLocaleMap() {
		return languages;
	}
	@Override
	public Locale getDefaultLocale() {
		return def;
	}

}


package com.sdi.infrastructure.i18n;

import java.util.Locale;
import java.util.Map;

public interface ILanguageService {
	
	public Map<String,Locale> getLocaleMap();
	public Locale getDefaultLocale();

}

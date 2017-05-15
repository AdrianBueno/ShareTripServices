package com.sdi.infrastructure.services.io;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Msg {
	private static final ResourceBundle RESOURCE_LOG;

	static{
		RESOURCE_LOG = ResourceBundle.getBundle("log_msg");

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
	
}

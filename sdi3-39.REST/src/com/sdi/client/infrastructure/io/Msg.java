package com.sdi.client.infrastructure.io;

import java.util.MissingResourceException;
import java.util.ResourceBundle;


public class Msg {
	private static final ResourceBundle RESOURCE_MSG;
	static{
		RESOURCE_MSG = ResourceBundle.getBundle("messages_es");

	}
	
	
	
	
	private Msg() {
	}

	public static String getStr(String key) {
		try {
			return RESOURCE_MSG.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

}

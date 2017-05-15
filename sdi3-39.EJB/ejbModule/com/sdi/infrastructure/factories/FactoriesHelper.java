package com.sdi.infrastructure.factories;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FactoriesHelper {

	private FactoriesHelper(){}
	
	static Object createFactory(String file, String factoryType) {
		String className = loadProperty(file, factoryType);
		try {
			Class<?> clazz = Class.forName(className);
			return clazz.newInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	static String loadProperty(String file, String property) {
		Properties p = loadPropertiesFile(file);
		String value = p.getProperty(property);
		if (value == null) {
			throw new RuntimeException("Property not found in " + file);
		}
		return value;
	}

	private static Properties loadPropertiesFile(String file) {
		Properties p = new Properties();
		try {
			InputStream is = Factories.class.getResourceAsStream(file);
			p.load(is);
			is.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return p;
	}

}

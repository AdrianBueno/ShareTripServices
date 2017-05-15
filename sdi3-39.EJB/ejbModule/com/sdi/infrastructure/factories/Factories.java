package com.sdi.infrastructure.factories;

import com.sdi.business.ServicesFactory;

public class Factories {
	private static String CONFIG_FILE = "/factories.properties";
	private Factories() {}
	
	public static ServicesFactory services = 
			(ServicesFactory) FactoriesHelper
			.createFactory(CONFIG_FILE, "SERVICES_FACTORY");
	

}

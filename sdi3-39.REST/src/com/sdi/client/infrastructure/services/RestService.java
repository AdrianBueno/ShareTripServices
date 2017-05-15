package com.sdi.client.infrastructure.services;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import com.sdi.client.infrastructure.faces.AppliesServiceRest;
import com.sdi.client.infrastructure.faces.SeatsServiceRest;
import com.sdi.client.infrastructure.faces.TripsServiceRest;
import com.sdi.client.infrastructure.faces.UsersServiceRest;
import com.sdi.client.infrastructure.filter.AddAuthHeadersRequestFilter;

public class RestService {
	
	private static UsersServiceRest usr;
	private static TripsServiceRest tsr;
	private static SeatsServiceRest ssr;
	private static AppliesServiceRest asr;
	private static AddAuthHeadersRequestFilter auth;
	
	private RestService() {}
	
	private static void initializeService(){
		usr =  new ResteasyClientBuilder().build()
				.register(auth)
				.target("http://localhost:8280/sdi3-39.Web/rest/")
				.proxy(UsersServiceRest.class);
		
		tsr = new ResteasyClientBuilder().build()
				.register(auth)
				.target("http://localhost:8280/sdi3-39.Web/rest/")
				.proxy(TripsServiceRest.class);
		asr = new ResteasyClientBuilder().build()
				.register(auth)
				.target("http://localhost:8280/sdi3-39.Web/rest/")
				.proxy(AppliesServiceRest.class);
	}
	
	public static void setAddAuthHeader(AddAuthHeadersRequestFilter auth){
		RestService.auth =auth;
		initializeService();
	}
	
	
	public static UsersServiceRest getUsr() {
		return usr;
	}

	public static TripsServiceRest getTsr() {
		return tsr;
	}

	public static SeatsServiceRest getSsr() {
		return ssr;
	}

	public static AppliesServiceRest getAsr() {
		return asr;
	}

	public static void setAsr(AppliesServiceRest asr) {
		RestService.asr = asr;
	}
	
	
	

}

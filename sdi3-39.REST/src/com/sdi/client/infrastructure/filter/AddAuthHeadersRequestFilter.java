package com.sdi.client.infrastructure.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.apache.commons.codec.binary.Base64;

public class AddAuthHeadersRequestFilter implements ClientRequestFilter {

	private  String login;
	private  String password;

	public AddAuthHeadersRequestFilter(String login, String password) {
		this.login = login;
		this.password = password;
	}

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		String credentials = login + ":" + password;
		String encryptedCredentials = Base64.encodeBase64String(credentials
				.getBytes(StandardCharsets.UTF_8));
		requestContext.getHeaders()
				.add("Authorization", "Basic " + encryptedCredentials);
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
}

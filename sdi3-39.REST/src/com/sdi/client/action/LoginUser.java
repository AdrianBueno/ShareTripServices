package com.sdi.client.action;

import com.sdi.client.infrastructure.filter.AddAuthHeadersRequestFilter;
import com.sdi.client.infrastructure.model.User;
import com.sdi.client.infrastructure.services.RestService;
import com.sdi.client.infrastructure.services.SessionService;
import com.sdi.console.Console;
import com.sdi.menu.Action;

public class LoginUser implements Action {
	@Override
	public void execute() throws Exception {
		String login = Console.readString("Login");
		String password = Console.readString("Password");
		RestService
		.setAddAuthHeader(new AddAuthHeadersRequestFilter(login, password));
		User user =  RestService.getUsr().loginUser(login, password);
		SessionService.setUser(user);
	}

}

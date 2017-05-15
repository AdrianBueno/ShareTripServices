package com.sdi.client;

import javax.jms.JMSException;

import com.sdi.client.action.ChatClient;
import com.sdi.client.services.Services;
import com.sdi.infrastructure.util.console.Console;

public class Main{
	
	private Services s;

	public Main() {
		this.s = Services.getInstance();
		try {
			new ChatClient(s).execute();
		} catch (Exception e) {
			Console.print("Hubo un problema en el cliente de mensajería");
		}
	}

	public static void main(String[] args) throws JMSException {
		new Main();
	}
}

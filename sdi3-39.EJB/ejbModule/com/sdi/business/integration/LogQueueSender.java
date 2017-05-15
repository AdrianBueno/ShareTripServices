package com.sdi.business.integration;

public interface LogQueueSender {
	public void sendLogMessage(String userLogin, String tripId, String message);
}

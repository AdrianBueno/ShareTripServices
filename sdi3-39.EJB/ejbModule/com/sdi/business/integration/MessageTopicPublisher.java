package com.sdi.business.integration;

import javax.jms.MapMessage;

public interface MessageTopicPublisher {
	public void publishMessage(MapMessage map);
}

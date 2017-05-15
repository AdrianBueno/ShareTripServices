package com.sdi.business.integration.mdb;

import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.TripsService;
import com.sdi.business.UsersService;
import com.sdi.business.impl.face.local.LocalTripsService;
import com.sdi.business.impl.face.local.LocalUsersService;
import com.sdi.business.integration.LogQueueSender;
import com.sdi.business.integration.MessageTopicPublisher;
import com.sdi.business.integration.TripQeueSender;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;

@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/TripChatQueue") })
public class TripChatListener implements MessageListener {
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(TripChatListener.class);
	}

	@EJB(beanInterface = LocalUsersService.class)
	private UsersService usersService;

	@EJB(beanInterface = LocalTripsService.class)
	private TripsService tripsService;

	@EJB
	private TripQeueSender qeueTripSender;

	@EJB
	private MessageTopicPublisher topicPublisher;
	
	@EJB
	private LogQueueSender logQueueSender;

	// @EJB private ChatResponseSender topicSender;

	@Override
	public void onMessage(Message msg) {
		log.info("Message Received.");
		try {
			process(msg);
		} catch (JMSException je) {
			log.info(je.getMessage());
		} catch (BusinessException be) {
			log.info(be.getMessage());
		}

	}

	private void process(Message m) throws BusinessException, JMSException {
		if (!messageOfExpectedType(m)) {
			log.info("Not of expected type " + m);
			return;
		}
		MapMessage msg = (MapMessage) m;
		switch (msg.getString("COMMAND")) {
		case "GET_TRIPS":
			doFindTrips(msg);
			break;
		case "SEND_MESSAGE":
			doPublishMessage(msg);
			break;
		default:
			break;
		}
	}

	private void doPublishMessage(MapMessage msg) throws BusinessException,
			JMSException {
		User user = doLogin(msg);
		boolean validSender = tripsService.isUserParticipant(
				Long.parseLong(msg.getString("TRIP_ID")), user.getId());
		if (validSender)
			topicPublisher.publishMessage(msg);
		else
			logQueueSender.sendLogMessage(msg.getString("LOGIN"), msg.getString("TRIP_ID"), msg.getString("CHAT"));
	}

	private void doFindTrips(MapMessage msg) throws BusinessException,
			JMSException {
		User user = doLogin(msg);
		List<Trip> trips = tripsService.listInvolvedTrips(user.getId());
		qeueTripSender.sendTripList(trips);
	}

	private User doLogin(MapMessage msg) throws BusinessException, JMSException {
		String login = msg.getString("LOGIN");
		String password = msg.getString("PASS");
		return usersService.loginUser(login, password);
	}

	private boolean messageOfExpectedType(Message msg) {
		return msg instanceof MapMessage;
	}
}

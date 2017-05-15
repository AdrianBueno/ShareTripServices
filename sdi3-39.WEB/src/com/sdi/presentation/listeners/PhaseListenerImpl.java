package com.sdi.presentation.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

public class PhaseListenerImpl implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(PhaseListenerImpl.class);
	}
	
	@Override
	public void afterPhase(PhaseEvent event) {
		log.debug("After life cycle phase: " + event.getPhaseId());

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
		log.debug("Before life cycle phase: " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.ANY_PHASE;
	}

}

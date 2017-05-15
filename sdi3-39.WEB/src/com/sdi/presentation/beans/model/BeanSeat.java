package com.sdi.presentation.beans.model;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.AppliesService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.presentation.beans.support.BeanMessages;
import com.sdi.presentation.beans.support.BeanSession;
/**
 * Esta clase es una managegBean encargado de manejar las acciones sobre
 * asientos realizadas desde la vista.
 * de un viaje para la vista
 * @author sdi-39
 * @version 2 15/06
 */
@ManagedBean(name= "beanSeat")
@SessionScoped
public class BeanSeat implements Serializable {

	private static final long serialVersionUID = -2720767877180504372L;
	private static final AppliesService as = 
			Factories.services.getAppliesService();
	
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(BeanSeat.class);
	}
	
	@ManagedProperty("#{beanSession}")
	private BeanSession beanSession;
	
	@ManagedProperty("#{beanMessages}")
	private BeanMessages beanMessages;

	//Necesitamos beanTrip para saber que viaje hay seleccionado
	@ManagedProperty("#{beanTrip}")
	private BeanTrip beanTrip;
	
	@ManagedProperty("#{beanUser}")
	private BeanUser beanUser;
	
	private Seat selectedSeat;
	
	public BeanSeat() {
		log.info(Msg.getStr("info.instanced"));
	}
	
	/**
	 * Realiza una solicitud de plaza.
	 */
	public void requestPlace(){
		log.info("Solicitando plaza para Trip seleccionado");
		try {
			as.newApply(beanTrip.getSelectedTrip().getId(),
					beanSession.getCurrentUser().getId());
			beanMessages.addInfoMsg( //Desplegamos mensaje de confirmación
					beanMessages.getMessage("msgs", "msg_request_seat"),
					beanSession.getCurrent());
		} catch (BusinessException e) {
			createExceptionMessages(e);
		}
	}
	/**
	 * Realizamos una aceptación de una plaza
	 */
	public void acceptPlace(){
		log.info("Aceptando plaza del Trip seleccionado");
		try {
			as.acceptApply(beanTrip.getSelectedTrip().getId()
					,beanUser.getSelectedUser().getId());
			beanMessages.addInfoMsg(
					beanMessages.getMessage("msgs", "msg_accept_seat"),
					beanSession.getCurrent());
		} catch (BusinessException e) {
			createExceptionMessages(e);
		}
	}
	/**
	 * Cancelamos  una plaza del viaje
	 */
	public void excludePlace(){
		log.info(" cancelando plaza del Trip seleccionado");
		try {
			as.excludeSeat(beanTrip.getSelectedTrip().getId(),
					beanUser.getSelectedUser().getId());
			beanMessages.addInfoMsg(
					beanMessages.getMessage("msgs", "msg_cancel_seat"),
					beanSession.getCurrent());
		} catch (BusinessException e) {
			createExceptionMessages(e);
		}
	}
	
	/**
	 * Cancelamos nuestra petición de plaza
	 */
	public void cancelPlace(){
		log.info(" cancelando plaza del Trip seleccionado");
		try {
			as.cancelApply(beanTrip.getSelectedTrip().getId(),
					beanSession.getCurrentUser().getId());
			beanMessages.addInfoMsg(
					beanMessages.getMessage("msgs", "msg_cancel_seat"),
					beanSession.getCurrent());
		} catch (BusinessException e) {
			createExceptionMessages(e);
		}
	}
	
	private void createExceptionMessages(BusinessException e){
		String msg = beanMessages.getMessage("msgs", e.getMessage());
		log.warn(msg);
		beanMessages.addWarnMsg(msg, beanSession.getCurrent());
	}
	
	//Setters and getters

	public BeanSession getBeanSession() {
		return beanSession;
	}

	public void setBeanSession(BeanSession beanSession) {
		this.beanSession = beanSession;
	}

	public BeanMessages getBeanMessages() {
		return beanMessages;
	}

	public void setBeanMessages(BeanMessages beanMessages) {
		this.beanMessages = beanMessages;
	}

	public BeanTrip getBeanTrip() {
		return beanTrip;
	}

	public void setBeanTrip(BeanTrip beanTrip) {
		this.beanTrip = beanTrip;
	}

	

	public BeanUser getBeanUser() {
		return beanUser;
	}

	public void setBeanUser(BeanUser beanUser) {
		this.beanUser = beanUser;
	}

	public Seat getSelectedSeat() {
		return selectedSeat;
	}

	public void setSelectedSeat(Seat selectedSeat) {
		this.selectedSeat = selectedSeat;
	}
	
}

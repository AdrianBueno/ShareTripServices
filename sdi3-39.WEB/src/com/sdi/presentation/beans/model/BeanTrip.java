package com.sdi.presentation.beans.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.business.TripsService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.types.AddressPoint;
import com.sdi.infrastructure.model.types.TripStatus;
import com.sdi.infrastructure.model.types.Waypoint;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.presentation.beans.support.BeanMessages;
import com.sdi.presentation.beans.support.BeanSession;
import com.sdi.presentation.navigation.NavAction;
/**
 * Esta clase es un managedBean que se encarga de controlar las acciones
 * realizadas sobre un determinado viaje desde la vista.
 * (seleccionar viaje, crear, editar, cancelar...)
 * @author sdi-39
 * @version 3 14/06
 */
@ManagedBean(name = "beanTrip")
@SessionScoped 
public class BeanTrip implements Serializable {
	
	private static final long serialVersionUID = -6283148030775995714L;
	/* Para ahorrar código de la vista se elige que snippet se va a incluir
	 * de forma dinámica según el parámetro actionTrip
	 * una variable corresponde a mostrar el formulario para registrar
	 * y la otra para actualizar un viaje.
	 */
	private static final String SRC_NEW = "/html/snippets/trip_form.xhtml";
	private static final String SRC_EDIT = "/html/snippets/trip_form.xhtml";
	private static final TripsService ts = Factories.services.getTripsService();
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(BeanTrip.class);
	}
	
	@ManagedProperty("#{beanSession}")
	private BeanSession beanSession;
	
	@ManagedProperty("#{beanMessages}")
	private BeanMessages beanMessages;

	private Trip selectedTrip;
	private Integer actionTrip = 0; //El valor del parámetro de url.
	private Integer numberOfRepeats=0;
	private Integer frequencyRepeat=1;
	
	public BeanTrip() {
		log.info(Msg.getStr("info.instanced"));
		blankTrip(); //Necesitamos selected Trip instanciado.
	}
	/* Este método es llamado por un evento en 
	 * (page restricted: trip_action.xhtml)
	 * su objetivo es reinstanciar el trip seleccionado a la hora de registrar
	 * uno nuevo. pero en caso de que se pretenda editar uno,
	 * que corresponde con el parámetro "actionTrip = 2" no debe ejecutarse
	 */
	/**
	 * Reinstancia el campo selectedTrip de la clase.
	 */
	public void blankTrip() {
		if(actionTrip != 2){
			selectedTrip = new Trip(TripStatus.OPEN);
			selectedTrip.setDepartureAddress(new AddressPoint());
			selectedTrip.getDepartureAddress().setWaypoint(new Waypoint());
			selectedTrip.setDestinationAddress(new AddressPoint());
			selectedTrip.getDestinationAddress().setWaypoint(new Waypoint());
		}
	}
	/**
	 * Ejecuta la acción apropiada según el campo del atributo actionTrip
	 * @return String El outcome que podrá usarse para la navegación.
	 */
	public String executeAction(){
		if(actionTrip == 1)
			return save();
		else
			return edit();
	}
	/*
	 * Registra un nuevo viaje.
	 */
	private String save() {
		log.info("Se va registrar un nuevo Trip");
		try {
			if(numberOfRepeats > 0)
				multipleSave();
			else
				ts.registerTrip(instanceTrip(),
						beanSession.getCurrentUser().getId());
			return NavAction.TRIPS;
		} catch (BusinessException e) {
			createExceptionMessages(e);
			return NavAction.NO_MOVE;
		}
	}
	
	private Trip instanceTrip(){
		Trip trip = new Trip();
		trip.setArrivalDate(selectedTrip.getArrivalDate());
		trip.setDepartureDate(selectedTrip.getDepartureDate());
		trip.setClosingDate(selectedTrip.getClosingDate());
		trip.setDepartureAddress(selectedTrip.getDepartureAddress());
		trip.setDestinationAddress(selectedTrip.getDestinationAddress());
		trip.setEstimatedCost(selectedTrip.getEstimatedCost());
		trip.setAvailablePax(selectedTrip.getAvailablePax());
		trip.setMaxPax(selectedTrip.getMaxPax());
		trip.setStatus(selectedTrip.getStatus());
		trip.setComments(selectedTrip.getComments());
		return trip;
	}
	private void multipleSave() throws BusinessException{
		for(int i = 0; i < numberOfRepeats; i++){
			ts.registerTrip(instanceTrip(),
					beanSession.getCurrentUser().getId());
			addDaysToTrip();
		}
	}
	/*
	 * Actualiza un viaje existente que esté en estado abierto.
	 */
	private String edit() {
		log.info("Se va a actualizar el Trip con id: "+selectedTrip.getId());
		try {
			log.info(selectedTrip.getComments());
			selectedTrip = ts.updateTrip(selectedTrip);
			return NavAction.TRIPS;
		} catch (BusinessException e) {
			createExceptionMessages(e);
			return NavAction.NO_MOVE;
		}
	}
	/**
	 * Cancela el viaje que esté en el atributo selectedTrip
	 * cambiará su estado a CANCELLED.
	 */
	public void cancelTrip(){
		log.info("Se va a cancelar el Trip con id: "+selectedTrip.getId());
		try{
			ts.cancelTrip(selectedTrip.getId());
			String info = beanMessages.getMessage("msgs", "msg_cancel_trip");
			beanMessages.addInfoMsg(info, beanSession.getCurrent());
		} catch (BusinessException e){
			createExceptionMessages(e);
		}
	}
	
	private void createExceptionMessages(BusinessException e){
		String msg = beanMessages.getMessage("msgs", e.getMessage());
		log.warn(msg);
		beanMessages.addWarnMsg(msg, beanSession.getCurrent());
	}
	
	private void addDaysToTrip(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(selectedTrip.getArrivalDate());
		cal.add(Calendar.DATE, frequencyRepeat);
		selectedTrip.setArrivalDate(cal.getTime());
		cal.setTime(selectedTrip.getDepartureDate());
		cal.add(Calendar.DATE, frequencyRepeat);
		selectedTrip.setDepartureDate(cal.getTime());
		cal.setTime(selectedTrip.getClosingDate());
		cal.add(Calendar.DATE, frequencyRepeat);
		selectedTrip.setClosingDate(cal.getTime());
	}
	
	public void setEditMode(){
		actionTrip = 2;
	}
	public void setRegisterMode(){
		actionTrip = 1;
	}


	public Trip getSelectedTrip() {
		return selectedTrip;
	}

	public void setSelectedTrip(Trip trip) {
		log.info("Se ha cambiado el trip seleccionado");
		this.selectedTrip = trip;
	}

	public Integer getActionTrip() {
		return actionTrip;
	}

	public void setActionTrip(Integer actionTrip) {
		if(beanSession.isSessionInit() 
				&& ((actionTrip == 1) || (actionTrip == 2)))
			this.actionTrip = actionTrip;
		else
			beanMessages.addErrorMsg(beanMessages
					.getMessage("msgs", "msg_error_acces"),
					beanSession.getCurrent());
	}
	
	public String getSrc(){
		return actionTrip == 1 ? SRC_NEW : SRC_EDIT;
	}

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
	public Integer getNumberOfRepeats() {
		return numberOfRepeats;
	}
	public void setNumberOfRepeats(Integer numberOfRepeats) {
		this.numberOfRepeats = numberOfRepeats;
	}
	public Integer getFrequencyRepeat() {
		return frequencyRepeat;
	}
	public void setFrequencyRepeat(Integer frequencyRepat) {
		this.frequencyRepeat = frequencyRepat;
	}
	
	
}

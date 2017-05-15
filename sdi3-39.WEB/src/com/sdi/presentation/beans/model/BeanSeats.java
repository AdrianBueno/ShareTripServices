package com.sdi.presentation.beans.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;
import org.primefaces.component.datatable.DataTable;

import com.sdi.business.SeatsService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.Seat;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.presentation.beans.support.BeanMessages;
import com.sdi.presentation.beans.support.BeanSession;
/**
 * Esta clase es una mangedBean que se encarga de controlar la lista de
 * plazas disponibles en un viaje.
 * @author sdi-39
 * @version 2 15/06
 */
@ManagedBean(name = "beanSeats")
@SessionScoped
public class BeanSeats implements Serializable {
	
	private static final long serialVersionUID = -8676633061298136128L;
	private static final SeatsService ss = Factories.services.getSeatsService();
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(BeanSeats.class);
	}
	@ManagedProperty("#{beanSession}")
	private BeanSession beanSession;

	@ManagedProperty("#{beanMessages}")
	private BeanMessages beanMessages;
	
	@ManagedProperty("#{beanTrip}")
	private BeanTrip beanTrip; //Necesitamos conocer el viaje seleccionado
	
	/* Mediante el atributo correspondiente
	 * la dataTable encargada de los asientos
	 * está enlazada con este atributo.
	 */
	private DataTable dataTable;

	private List<Seat> seats;
	private List<Seat> selectedSeats;
	private List<Seat> filteredSeats;

	public BeanSeats() {
		log.info(Msg.getStr("info.instanced"));
	}
	/*
	 * Esta función es para ver las peticiones de un viaje
	 * (page, restricted: list_seats.xhtml)
	 */
	/**
	 * Actualizamos la lista de plazas del viaje seleccionado
	 */
	public void updateSeats(){
		if(!beanSession.isSessionInit())
			return;
		log.info("Actualizando Seats del trip seleccionado");
		try{
			seats = ss.listSeatsFromTrip(beanTrip.getSelectedTrip().getId(),
					null);
			/* Actualizamos los valores a mano para asegurarnos que el
			 * componente los renderiza.
			 */
			dataTable.updateValue(seats);
		} catch(BusinessException e){
			createExceptionMessages(e);
		}
	}
	
	/* Esta función es para ver mis peticiones a viajes
	 * (snippet: applies_form.xhtml)
	 */
	/**
	 * Actualizamos la lista de plazas con las plazas del usuario
	 * en sesión.
	 */
	public void updateMySeats(){
		if(!beanSession.isSessionInit())
			return;
		log.info("Actualizando Seats del usuario en sesión");
		try{
			seats = ss.listSeatsFromUser(beanSession.getCurrentUser().getId(),
					null);
		} catch(BusinessException e){
			createExceptionMessages(e);
		}
	}

	private void createExceptionMessages(BusinessException e){
		String msg = beanMessages.getMessage("msgs", e.getMessage());
		log.warn(msg);
		beanMessages.addWarnMsg(msg, beanSession.getCurrent());
	}

	// Getters and Setters
	
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

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public List<Seat> getSelectedSeats() {
		return selectedSeats;
	}

	public void setSelectedSeats(List<Seat> selectedSeats) {
		this.selectedSeats = selectedSeats;
	}

	public List<Seat> getFilteredSeats() {
		return filteredSeats;
	}

	public void setFilteredSeats(List<Seat> filteredSeats) {
		this.filteredSeats = filteredSeats;
	}

	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}
	
	
}
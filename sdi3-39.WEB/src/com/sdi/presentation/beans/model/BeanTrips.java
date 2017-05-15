package com.sdi.presentation.beans.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;
import org.primefaces.component.datatable.DataTable;

import com.sdi.business.TripsService;
import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.factories.Factories;
import com.sdi.infrastructure.model.Trip;
import com.sdi.infrastructure.model.User;
import com.sdi.infrastructure.services.io.Msg;
import com.sdi.presentation.beans.support.BeanMessages;
import com.sdi.presentation.beans.support.BeanSession;
import com.sdi.presentation.navigation.NavAction;
/**
 * Esta clase es una managedBean que se encarga de controlar la vista de los
 * diferentes tipos de trips.
 * @author sdi-39
 * @version 5 15/06
 */
@ManagedBean(name = "beanTrips")
@SessionScoped
public class BeanTrips implements Serializable {
	
	private static final long serialVersionUID = 5754692548431690216L;
	
	/* Para ahorrar código de la vista se elige que snippet se va a incluir
	 * de forma dinámica según el parámetro typeTrip el cual tiene 4 posibles 
	 * valores (Mostrar los viajes abiertos de otros usuario, mostrar los viajes
	 * propios, mostrar los viajes realizados y mostrar los viajes a los que se
	 * ha solicitado plaza) para los 3 primeros se usa la primera constante
	 * SRC_TRIPS para el 4º la segunda, SRC_APPLY, esto es debido a que la 
	 * datatable para los 3 primeros es la misma, pero para ver las solicitudes
	 * no, porque aunque debo mostrar viajes necesito el estado del asiento
	 * del usuario de ese viaje lo que me obliga a cambiar la fuente del 
	 * datatable.
	 */
	private static final String SRC_TRIPS = "/html/snippets/trips_form.xhtml";
	private static final String SRC_APPLY = "/html/snippets/applies_form.xhtml";
	private static final TripsService ts = Factories.services.getTripsService();
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(BeanTrips.class);
	}

	@ManagedProperty("#{beanSession}")
	private BeanSession beanSession;

	@ManagedProperty("#{beanMessages}")
	private BeanMessages beanMessages;
	
	@ManagedProperty("#{beanSeats}")
	private BeanSeats beanSeats;
	
	@ManagedProperty("#{beanTrip}")
	private BeanTrip beanTrip;
	
	private DataTable dataTable;

	private List<Trip> trips;
	private List<Trip> selectedTrips;
	private List<Trip> filteredTrips;
	private Integer typeTrip; //El parámetro de la url
	
	public BeanTrips() {
		selectedTrips = new LinkedList<Trip>();
		filteredTrips = new LinkedList<Trip>();
		typeTrip = 1;
		log.info(Msg.getStr("info.instanced"));
	}
	
	@PostConstruct
	public void init(){
		//update();
	}
	public String viewTrips(){
		return NavAction.TRIPS;
	}
	
	/** Actualiza la lista de viajes en función del campo typeTrip
	 * usado como parámetro en la url.
	 * Tras llamar a este método se actualizará la dataTatable enlazada
	 * en este objeto.
	 */
	public void update(){
		/*Ignoro las peticiones ajax
		 * El objetivo es que el valor de la lista interna no se actualice
		 * es decir no llame a la capa de negocio con una petición ajax.
		 * Si no por ejemplo al filtrar tardaría mucho más por cada letra. 
		 */
		if(beanSession.getCurrent().getPartialViewContext().isAjaxRequest()){
			log.info("Petición ajax a la tabla de Trips");
		}else if(typeTrip == 1)
			updateTrips();
		else if(typeTrip == 2)
			updateMyTrips();
		else if(typeTrip == 3)
			updateDoneTrips();
		else if(typeTrip == 4)
			beanSeats.updateMySeats();
		else if(typeTrip == 5)
			updateInvolvedTrips();
		
		/* ¿Por qué igualo la lista de trips a la lista de trips filtrados?
		 * Esto es para la primera ejecución, al parecer necesita tener 
		 * su lista de filtrados inicializada, si no lo hago yo lo hará él
		 * cuándo intente filtrar, pero entonces se producirá un problema 
		 * interno en primefaces dificil de detectar que impiden la ejecución
		 * de los actions yactions listeners dentro de la datatable
		 * Creo que es porque no superan la fase de validación del LCJFS.
		 */
		filteredTrips = trips; 
		/* Ahora actualizo los valroes de la tabla manualmente, esto lo hago
		 * porque si no en según que peticiones si los detecta, pero no los 
		 * renderiza.
		 */
		if(dataTable!=null)
			dataTable.updateValue(trips);
	}
	
	/*
	 * Los siguientes métodos privados
	 * realizan una actualización en la lista de viajes
	 * Según la consulta necesaria.
	 */
	private void updateTrips(){
		try{
			log.info("Intentando actualizar viajes");
			User user = beanSession.getCurrentUser();
			if(user == null)
				trips =  ts.listAvailablesTrips(null);
			else
				trips = ts.listAvailablesTrips(user.getId());
			String info;
			if(beanSession.isSessionInit())
				info = beanMessages.getMessage("msgs", "msg_viewtrip");
			else
				info = beanMessages.getMessage("msgs", "msg_viewtrip_session");
			beanMessages.addInfoMsg(info,beanSession.getCurrent());
		}catch(BusinessException e){
			createExceptionMessages(e);
			trips = null;
		}
	}
	
	private void updateMyTrips(){
		if(beanSession.isSessionOut())
			return;
		try{
			log.info("Intentando actualizar viajes del promotor");
			trips = ts.listTripsByPromoter(
					beanSession.getCurrentUser().getId());
		}catch(BusinessException e){
			createExceptionMessages(e);
			trips = null;
		}
	}
	
	private void updateDoneTrips(){
		if(beanSession.isSessionOut())
			return;
		try{
			log.info("Intentando actualizar viajes realizados");
			trips = ts.listDoneTrips(beanSession.getCurrentUser().getId());
		}catch(BusinessException e){
			createExceptionMessages(e);
			trips = null;
		}
	}
	
	private void updateInvolvedTrips(){
		if(beanSession.isSessionOut())
			return;
		try{
			log.info("Intentando actualizar viajes realizados");
			trips = ts.listInvolvedTrips(beanSession.getCurrentUser().getId());
		}catch(BusinessException e){
			createExceptionMessages(e);
			trips = null;
		}
	}
	
	/*
	 * Recorre la lista de trips que han sido seleccionados
	 * los asigna al beanTrip que maneja la lógica de Trips individuales
	 * y le envía una petición de cancelado, .
	 * Finalmente actualiza la lista.
	 */
	public void cancelTrips(){
		log.info("Cancelando viajes seleccionados.");
		for(Trip trip : selectedTrips){
			beanTrip.setSelectedTrip(trip);
			beanTrip.cancelTrip();
		}
	}
	
	/*
	 * Para mostrar mensajes.
	 */
	private void createExceptionMessages(BusinessException e){
		String msg = beanMessages.getMessage("msgs", e.getMessage());
		log.warn(msg);
		beanMessages.addWarnMsg(msg, beanSession.getCurrent());
	}
	
	
	//Getters y Setters
	
	public String getSrc() {
		if(typeTrip == 4)
			return SRC_APPLY;
		else
			return SRC_TRIPS;
	}
	
	public Integer getTypeTrip() {
		return typeTrip;
	}

	public void setTypeTrip(Integer typeTrip) {
		if((beanSession.isSessionInit() && typeTrip > 0 && typeTrip < 6) || (typeTrip == 1) )
			this.typeTrip = typeTrip;
		else
			beanMessages.addErrorMsg(beanMessages
					.getMessage("msgs", "msg_error_acces"),
					beanSession.getCurrent());
	}

	public BeanSession getBeanSession() {
		return beanSession;
	}


	public void setBeanSession(BeanSession beanSession) {
		this.beanSession = beanSession;
	}
	
	

	public BeanSeats getBeanSeats() {
		return beanSeats;
	}

	public void setBeanSeats(BeanSeats beanSeats) {
		this.beanSeats = beanSeats;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	public List<Trip> getSelectedTrips() {
		return selectedTrips;
	}

	public List<Trip> getFilteredTrips() {
		return filteredTrips;
	}

	public void setFilteredTrips(List<Trip> filteredTrips) {
		this.filteredTrips = filteredTrips;
	}

	public void setSelectedTrips(List<Trip> selectedTrips) {
		this.selectedTrips = selectedTrips;
	}

	public Trip getSelectedTrip() {
		return beanTrip.getSelectedTrip();
	}


	public void setSelectedTrip(Trip selectedTrip) {
		log.info("Se ha seleccionado un viaje diferente");
		beanTrip.setSelectedTrip(selectedTrip);
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

	public DataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(DataTable dataTable) {
		this.dataTable = dataTable;
	}
}

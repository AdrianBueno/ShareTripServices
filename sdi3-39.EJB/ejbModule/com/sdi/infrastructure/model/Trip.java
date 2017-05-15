package com.sdi.infrastructure.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.logging.impl.Log4jLogger;

import com.sdi.infrastructure.exception.BusinessException;
import com.sdi.infrastructure.exception.BusinessModelException;
import com.sdi.infrastructure.model.types.AddressPoint;
import com.sdi.infrastructure.model.types.TravelStatus;
import com.sdi.infrastructure.model.types.TripStatus;

/**
 * Esta clase implementa el modelo y la lógica de negocio relacionada con los
 * viajes.
 * 
 * @author sdi-39
 * @version 12 15/06
 */
@Entity
@Table(name = "TTRIPS")
@XmlRootElement(name = "trip")
@XmlAccessorType(XmlAccessType.FIELD)
public class Trip implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log;
	static {
		log = Log4jLogger.getLogger(Trip.class);
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	private Long id;

	@Column(name = "ARRIVALDATE")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement
	private Date arrivalDate;

	@Column(name = "CLOSINGDATE")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement
	private Date closingDate;

	@Column(name = "DEPARTUREDATE")
	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement
	private Date departureDate;

	@Column(name = "AVAILABLEPAX")
	@XmlElement
	private Integer availablePax;

	@Column(name = "ESTIMATEDCOST")
	@XmlElement
	private Double estimatedCost;

	@Column(name = "MAXPAX")
	@XmlElement
	private Integer maxPax;

	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	@XmlElement
	private TripStatus status;

	@Transient
	@XmlTransient
	private String applyState;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "address", column = @Column(name = "DEPARTURE_ADDRESS")),
			@AttributeOverride(name = "city", column = @Column(name = "DEPARTURE_CITY")),
			@AttributeOverride(name = "state", column = @Column(name = "DEPARTURE_STATE")),
			@AttributeOverride(name = "country", column = @Column(name = "DEPARTURE_COUNTRY")),
			@AttributeOverride(name = "zipCode", column = @Column(name = "DEPARTURE_ZIPCODE")),
			@AttributeOverride(name = "waypoint.wptLat", column = @Column(name = "DEPARTURE_WPT_LAT")),
			@AttributeOverride(name = "waypoint.wptLon", column = @Column(name = "DEPARTURE_WPT_LON")) })
	@XmlElement
	private AddressPoint departureAddress;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "address", column = @Column(name = "DESTINATION_ADDRESS")),
			@AttributeOverride(name = "city", column = @Column(name = "DESTINATION_CITY")),
			@AttributeOverride(name = "state", column = @Column(name = "DESTINATION_STATE")),
			@AttributeOverride(name = "country", column = @Column(name = "DESTINATION_COUNTRY")),
			@AttributeOverride(name = "zipCode", column = @Column(name = "DESTINATION_ZIPCODE")),
			@AttributeOverride(name = "waypoint.wptLat", column = @Column(name = "DESTINATION_WPT_LAT")),
			@AttributeOverride(name = "waypoint.wptLon", column = @Column(name = "DESTINATION_WPT_LON")) })
	@XmlElement
	private AddressPoint destinationAddress;

	@Column(name = "COMMENTS")
	@XmlElement
	private String comments;

	@ManyToOne
	@JoinColumn(name = "PROMOTER_ID")
	@XmlTransient
	private User user;
	@OneToMany(mappedBy = "trip")
	@XmlTransient
	private Set<Seat> seats = new HashSet<Seat>();
	@OneToMany(mappedBy = "appliedtrips")
	@XmlTransient
	private Set<Application> applications = new HashSet<Application>();

	public Trip() {
	}

	public Trip(TripStatus status) {
		setStatus(status);
		log.info("Trip ha sido instanciado");
	}


	// Métodos relación User
	public User getUser() {
		return user;
	}

	void _setUser(User user) {
		this.user = user;
	}

	public Set<Seat> getSeats() {
		return Collections.unmodifiableSet(seats);
	}

	Set<Seat> _getSeats() {
		return seats;
	}
	
	// Métodos relación Application

	public Set<Application> getApplications() {
		return Collections.unmodifiableSet(applications);
	}

	Set<Application> _getApplications() {
		return applications;
	}


	// métodos naturales
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Integer getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(Integer availablePax) {
		this.availablePax = availablePax;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public AddressPoint getDepartureAddress() {
		return departureAddress;
	}

	public void setDepartureAddress(AddressPoint departureAddress) {
		this.departureAddress = departureAddress;
	}

	public AddressPoint getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(AddressPoint destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	// Métodos que se pueden llamar desde la interfaz

	public String getFechaSalida() {
		return getFecha(this.departureDate);
	}

	public String getFechaLlegada() {
		return getFecha(this.arrivalDate);
	}

	public String getFechaCierre() {
		return getFecha(this.closingDate);
	}

	public int getNumberApplies() {
		return applications.size();
	}

	/**
	 * Consulta si existe alguna plaza disponible en el viaje
	 * 
	 * @return boolean
	 */
	public boolean existPlace() {
		return getAvailablePax() > 0;
	}

	/**
	 * Consulta si el viaje es o no modificable.
	 * 
	 * @return boolean
	 */
	public boolean isModificable() {
		// Si está abierto y la fecha de cierre es despues que hoy.
		return (status == TripStatus.OPEN)
				&& (getClosingDate().after(new Date()));
	}

	// Métodos exclusivos de la capa de negocio
	/**
	 * Comprobamos que los valores sean correctos e inicializamos el estado del
	 * viaje
	 * 
	 * @throws BusinessException
	 */
	public void initialize() throws BusinessException {
		assertInitialize();
		setStatus(TripStatus.OPEN);
	}

	/**
	 * Comprobamos que los valroes sean correcots
	 * 
	 * @throws BusinessException
	 */
	public void reInitialize() throws BusinessException {
		assertIsModifcable();
		assertInitialize();
	}

	/**
	 * Este método cambiará el estado del viaje a CANCELLED. Eliminará todas las
	 * solicitudes de plaza asociadas a este viaje, además, pasará el estado de
	 * los asientos asociados al viaje a EXCLUDED.
	 * 
	 * @throws BusinessException
	 */
	public void cancelTrip() throws BusinessException {
		assertIsPosibleCancell();
		for (Seat seat : seats)
			if (seat.getStatus() != TravelStatus.PROMOTER)
				seat.excludeSeat();
		setStatus(TripStatus.CANCELLED);
	}

	/**
	 * Este método chequea sin un viaje ha de finalizarse debido a la fecha.
	 */
	public void checkTrip() {
		if (status == TripStatus.OPEN && closingDate.before(new Date()))
			finalizeTrip();
	}

	// Métodos exclusivos del modelo

	/*
	 * Añade un asiento aceptado, es decir reduce las plazas disponibles
	 */
	void addAcceptedSeat() throws BusinessException {
		assertExistPlace();
		setAvailablePax(getAvailablePax() - 1);
		if (!existPlace())
			finalizeTrip(); // Cuidado, que seat ya esté como accepted,
	}

	/*
	 * Elimina un aiento aceptado aumenta las plazas disponibles
	 */
	void removeAcceptedSeat() throws BusinessException {
		assertCanBeMorePlaces();
		setAvailablePax(getAvailablePax() + 1);
	}

	// Lógica de negocio privada

	/*
	 * Método para finalizar el viaje, es llamado automáticamente cuándo el
	 * viaje se queda sin plazas o ha pasado la fecha de cierre.
	 */
	private void finalizeTrip() {
		// Recorremos todos los seats y los finalizamos
		for (Seat seat : seats)
			seat.finaliceSeat();
		// Ponemos el estado del viaje a cerrado o hecho según proceda.
		if (arrivalDate.before(new Date()))
			setStatus(TripStatus.DONE);
		else
			setStatus(TripStatus.CLOSED);
		log.info("Se ha finalizado un viaje");
	}

	private String getFecha(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		StringBuilder str = new StringBuilder();
		str.append(calendar.get(Calendar.DAY_OF_MONTH));
		str.append("/");
		str.append(calendar.get(Calendar.MONTH) + 1);
		str.append("/");
		str.append(calendar.get(Calendar.YEAR));
		return str.toString();
	}

	// Asserts
	void assertIsModifcable() throws BusinessException {
		if (!isModificable())
			throw new BusinessModelException("assert.bis_tripunmodificable");
	}

	void assertIsPosibleCancell() throws BusinessException {
		if (!isModificable())
			throw new BusinessModelException("assert.bis_tripnotopen");
	}

	void assertExistPlace() throws BusinessException {
		if (!existPlace())
			throw new BusinessModelException("assert.bis_tripwithoutseats");
	}

	void assertCanBeMorePlaces() throws BusinessException {
		if (getAvailablePax() + 1 >= getMaxPax())
			throw new BusinessModelException("assert.bis_tripmaxavailable");
	}

	void assertDates() throws BusinessException {
		if (getClosingDate().before(new Date()))
			throw new BusinessModelException("assert.bis_closingdate");
		if (getDepartureDate().before(getClosingDate()))
			throw new BusinessModelException("assert.bis_departuredate");
		if (getArrivalDate().before(getDepartureDate()))
			throw new BusinessModelException("assert.bis_arrivaldate");
	}

	void assertAvailablePlaces() throws BusinessException {
		// Las plazas disponibles deben ser menor que las máximas.
		// Además el número mínimo de plazas máximas debe ser 2 siempre
		if ((getAvailablePax() >= getMaxPax()) || getMaxPax() <= 1)
			throw new BusinessModelException("assert.bis_tripmaxavailable");
	}

	void assertInitialize() throws BusinessException {
		assertAvailablePlaces();
		assertDates();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trip other = (Trip) obj;
		if (arrivalDate == null) {
			if (other.arrivalDate != null)
				return false;
		} else if (!arrivalDate.equals(other.arrivalDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id=" + id + ",arrivalDate=" + arrivalDate
				+ ",closingDate=" + closingDate + ",departureDate="
				+ departureDate + ",availablePax=" + availablePax
				+ ",estimatedCost=" + estimatedCost + ",maxPax=" + maxPax
				+ ",status=" + status + ",applyState=" + applyState
				+ "," + departureAddress.toString()
				+ "," + destinationAddress.toString() + ",comments="
				+ comments;
	}

}

package com.mindtree.adminmicroservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fId;
	@NotNull(message = "Flight must have a name")
	private String name;
	@Min(value = 25,message="Minimum 25 seats required for a flight")
	private int totalNoOfSeats;
	private int tottalNoOfFirstClassSeats;
	private int availableNoOfFirstClassSeats;
	private double priceFirstClass;
	private int totalNoOfEconomyClassSeats;
	private int availableNoOfEconomyClassSeats;
	private double priceEconomyClass;
	private int totalNoOfBusinessClassSeats;
	private int avaialbleNoOfBusinessClassSeats;
	private double priceBusinessClass;
	@NotNull(message = "Source cannot be null")
	private String source;
	@NotNull(message = "Destination cannot be null")
	private String destination;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateOfDeparture;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date estimatedDepartureTime;

	public Flight() {
		super();
	}

	public Flight(String name, int totalNoOfSeats, int tottalNoOfFirstClassSeats, int availableNoOfFirstClassSeats,
			double priceFirstClass, int totalNoOfEconomyClassSeats, int availableNoOfEconomyClassSeats,
			double priceEconomyClass, int totalNoOfBusinessClassSeats, int avaialbleNoOfBusinessClassSeats,
			double priceBusinessClass, String source, String destination, Date dateOfDeparture,
			Date estimatedDepartureTime) {
		super();
		this.name = name;
		this.totalNoOfSeats = totalNoOfSeats;
		this.tottalNoOfFirstClassSeats = tottalNoOfFirstClassSeats;
		this.availableNoOfFirstClassSeats = availableNoOfFirstClassSeats;
		this.priceFirstClass = priceFirstClass;
		this.totalNoOfEconomyClassSeats = totalNoOfEconomyClassSeats;
		this.availableNoOfEconomyClassSeats = availableNoOfEconomyClassSeats;
		this.priceEconomyClass = priceEconomyClass;
		this.totalNoOfBusinessClassSeats = totalNoOfBusinessClassSeats;
		this.avaialbleNoOfBusinessClassSeats = avaialbleNoOfBusinessClassSeats;
		this.priceBusinessClass = priceBusinessClass;
		this.source = source;
		this.destination = destination;
		this.dateOfDeparture = dateOfDeparture;
		this.estimatedDepartureTime = estimatedDepartureTime;
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalNoOfSeats() {
		return totalNoOfSeats;
	}

	public void setTotalNoOfSeats(int totalNoOfSeats) {
		this.totalNoOfSeats = totalNoOfSeats;
	}

	public int getTottalNoOfFirstClassSeats() {
		return tottalNoOfFirstClassSeats;
	}

	public void setTottalNoOfFirstClassSeats(int tottalNoOfFirstClassSeats) {
		this.tottalNoOfFirstClassSeats = tottalNoOfFirstClassSeats;
	}

	public int getAvailableNoOfFirstClassSeats() {
		return availableNoOfFirstClassSeats;
	}

	public void setAvailableNoOfFirstClassSeats(int availableNoOfFirstClassSeats) {
		this.availableNoOfFirstClassSeats = availableNoOfFirstClassSeats;
	}

	public double getPriceFirstClass() {
		return priceFirstClass;
	}

	public void setPriceFirstClass(double priceFirstClass) {
		this.priceFirstClass = priceFirstClass;
	}

	public int getTotalNoOfEconomyClassSeats() {
		return totalNoOfEconomyClassSeats;
	}

	public void setTotalNoOfEconomyClassSeats(int totalNoOfEconomyClassSeats) {
		this.totalNoOfEconomyClassSeats = totalNoOfEconomyClassSeats;
	}

	public int getAvailableNoOfEconomyClassSeats() {
		return availableNoOfEconomyClassSeats;
	}

	public void setAvailableNoOfEconomyClassSeats(int availableNoOfEconomyClassSeats) {
		this.availableNoOfEconomyClassSeats = availableNoOfEconomyClassSeats;
	}

	public double getPriceEconomyClass() {
		return priceEconomyClass;
	}

	public void setPriceEconomyClass(double priceEconomyClass) {
		this.priceEconomyClass = priceEconomyClass;
	}

	public int getTotalNoOfBusinessClassSeats() {
		return totalNoOfBusinessClassSeats;
	}

	public void setTotalNoOfBusinessClassSeats(int totalNoOfBusinessClassSeats) {
		this.totalNoOfBusinessClassSeats = totalNoOfBusinessClassSeats;
	}

	public int getAvaialbleNoOfBusinessClassSeats() {
		return avaialbleNoOfBusinessClassSeats;
	}

	public void setAvaialbleNoOfBusinessClassSeats(int avaialbleNoOfBusinessClassSeats) {
		this.avaialbleNoOfBusinessClassSeats = avaialbleNoOfBusinessClassSeats;
	}

	public double getPriceBusinessClass() {
		return priceBusinessClass;
	}

	public void setPriceBusinessClass(double priceBusinessClass) {
		this.priceBusinessClass = priceBusinessClass;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(Date dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public Date getEstimatedDepartureTime() {
		return estimatedDepartureTime;
	}

	public void setEstimatedDepartureTime(Date estimatedDepartureTime) {
		this.estimatedDepartureTime = estimatedDepartureTime;
	}
	
}

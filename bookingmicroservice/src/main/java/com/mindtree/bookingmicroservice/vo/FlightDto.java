package com.mindtree.bookingmicroservice.vo;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {

	private int fId;

	private String name;

	private int seatNo;

	private String seatType;

	private double farePrice;

	private String source;

	private String destination;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateOfDeparture;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp estimatedDepartureTime;

	public FlightDto() {
		super();
	}

	public FlightDto(int fId, String name, int seatNo, String seatType, double farePrice, String source,
			String destination, Date dateOfDeparture, Timestamp estimatedDepartureTime) {
		super();
		this.fId = fId;
		this.name = name;
		this.seatNo = seatNo;
		this.seatType = seatType;
		this.farePrice = farePrice;
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

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
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

	public Timestamp getEstimatedDepartureTime() {
		return estimatedDepartureTime;
	}

	public void setEstimatedDepartureTime(Timestamp estimatedDepartureTime) {
		this.estimatedDepartureTime = estimatedDepartureTime;
	}

	public double getFarePrice() {
		return farePrice;
	}

	public void setFarePrice(double farePrice) {
		this.farePrice = farePrice;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

}

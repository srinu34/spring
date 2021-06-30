package com.mindtree.bookingmicroservice.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
	private int bookingId;
	private User user;
	private FlightDto flight;

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public FlightDto getFlight() {
		return flight;
	}

	public void setFlight(FlightDto flight) {
		this.flight = flight;
	}

}

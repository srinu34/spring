package com.mindtree.bookingmicroservice.service;

import java.util.List;
import java.util.Map;

import com.mindtree.bookingmicroservice.exception.serviceexception.ServiceException;
import com.mindtree.bookingmicroservice.vo.Flight;
import com.mindtree.bookingmicroservice.vo.UserVo;

public interface BookFlightService {

	public UserVo bookFlight(int uId, int fId, String seatType) throws ServiceException;
	public Map<String, Object> getAllUsers(int fId)  throws ServiceException;
	public void cancelBooking(int bId)  throws ServiceException;
	public List<Flight> searchFlights(String source, String destination)  throws ServiceException;
	public List<UserVo> yourBookings(int uId)  throws ServiceException;
}

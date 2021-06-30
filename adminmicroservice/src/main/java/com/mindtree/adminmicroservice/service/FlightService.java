package com.mindtree.adminmicroservice.service;

import java.util.List;

import com.mindtree.adminmicroservice.exceptions.ServiceException;
import com.mindtree.adminmicroservice.model.Flight;

public interface FlightService {
	public Flight addFlight(Flight flight) throws ServiceException;
	public Flight getFlightDetails(int fId) throws ServiceException;
	public void deleteFlight(int fId) throws ServiceException;
	public Flight updateFlightName(int fId, String newName) throws ServiceException;
	public List<Flight> getAllFlights() throws ServiceException;
	public List<Flight> searchFlights(String source, String destination) throws ServiceException;
	public Flight updateFlightSource(int fId, String newSource) throws ServiceException;
	public Flight updateFlightDestination(int fId, String newDestination) throws ServiceException;
	
}

package com.mindtree.adminmicroservice.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindtree.adminmicroservice.exceptions.ServiceException;
import com.mindtree.adminmicroservice.model.Flight;
import com.mindtree.adminmicroservice.repostory.FlightRepository;
import com.mindtree.adminmicroservice.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepo;

	@Override
	public Flight addFlight(Flight flight) throws ServiceException {
		return flightRepo.save(flight);
	}

	@Override
	public Flight getFlightDetails(int fId) throws ServiceException {
		try {
			return flightRepo.findById(fId).orElseThrow(() -> new ServiceException("No Such Flight Id Exist"));
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void deleteFlight(int fId) throws ServiceException {
		try {
			Flight flight = flightRepo.findById(fId).orElseThrow(() -> new ServiceException("No Such Flight Id Exist"));
			flightRepo.delete(flight);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public Flight updateFlightName(int fId, String newName) throws ServiceException {
		Flight flight = null;
		try {
			flight = flightRepo.findById(fId).orElseThrow(() -> new ServiceException("No Such Flight Id Exist"));
			flight.setName(newName);
			flight = flightRepo.save(flight);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
		return flight;
	}

	@Override
	public List<Flight> getAllFlights() throws ServiceException {
		List<Flight> flights = flightRepo.findAll();
		try {
			if (flights.isEmpty()) {
				throw new ServiceException("No flights data available");
			}
			return flights;
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Flight> searchFlights(String source, String destination) throws ServiceException {
		List<Flight> flights = null;
		try {

			flights = flightRepo.searchFlights(source, destination);
			if (flights.isEmpty()) {
				throw new ServiceException("No flights available with the given source and destination");
			}
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
		return flights;
	}

	@Override
	public Flight updateFlightDestination(int fId, String newDestination) throws ServiceException {
		Flight flight = null;
		try {
			flight = flightRepo.findById(fId).orElseThrow(() -> new ServiceException("No Such Flight Id Exist"));
			flight.setDestination(newDestination);
			flight = flightRepo.save(flight);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
		return flight;
	}

	@Override
	public Flight updateFlightSource(int fId, String newSource) throws ServiceException {
		Flight flight = null;
		try {
			flight = flightRepo.findById(fId).orElseThrow(() -> new ServiceException("No Such Flight Id Exist"));
			flight.setSource(newSource);
			flight = flightRepo.save(flight);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
		return flight;
	}
}

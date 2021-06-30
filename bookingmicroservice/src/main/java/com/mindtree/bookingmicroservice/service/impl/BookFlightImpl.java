package com.mindtree.bookingmicroservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mindtree.bookingmicroservice.client.FlightClient;
import com.mindtree.bookingmicroservice.client.UserClient;
import com.mindtree.bookingmicroservice.exception.serviceexception.ServiceException;
import com.mindtree.bookingmicroservice.model.BookFlight;
import com.mindtree.bookingmicroservice.repository.*;
import com.mindtree.bookingmicroservice.service.BookFlightService;
import com.mindtree.bookingmicroservice.vo.Flight;
import com.mindtree.bookingmicroservice.vo.FlightDto;
import com.mindtree.bookingmicroservice.vo.User;
import com.mindtree.bookingmicroservice.vo.UserVo;

@Service
public class BookFlightImpl implements BookFlightService {

	@Autowired
	private BookFlightRepo fr;

	@Autowired
	private UserClient userClient;

	@Autowired
	private FlightClient flightClient;

	static ModelMapper mapper = new ModelMapper();

	/*
	 * @Autowired private RestTemplate resttemplate;
	 */

	@Override
	public UserVo bookFlight(int uId, int fId, String seatType) throws ServiceException {
		UserVo vo = new UserVo();
		
		try {
		Flight flight = flightClient.getFlight(fId);
		FlightDto flightDto = mapper.map(flight, FlightDto.class);
		if (seatType.equalsIgnoreCase("business")) {
			checkAvailability(flight.getAvaialbleNoOfBusinessClassSeats());
			flightDto.setFarePrice(flight.getPriceBusinessClass());
			int seatNo = flight.getTotalNoOfSeats()-flight.getTotalNoOfBusinessClassSeats()+(flight.getTotalNoOfBusinessClassSeats()-flight.getAvaialbleNoOfBusinessClassSeats())+1;
			flightDto.setSeatNo(seatNo);
			flightDto.setSeatType("Business");
			flight.setAvaialbleNoOfBusinessClassSeats(flight.getAvaialbleNoOfBusinessClassSeats() - 1);
			flightClient.updateFlight(flight);
			
		} else if (seatType.equalsIgnoreCase("economy")) {
			checkAvailability(flight.getAvailableNoOfEconomyClassSeats());
			flightDto.setFarePrice(flight.getPriceEconomyClass());
			int seatNo = flight.getTotalNoOfSeats()-flight.getTotalNoOfBusinessClassSeats()-flight.getTotalNoOfEconomyClassSeats()+(flight.getTotalNoOfEconomyClassSeats()-flight.getAvailableNoOfEconomyClassSeats())+1;
			flightDto.setSeatNo(seatNo);
			flightDto.setSeatType("Economy");
			flight.setAvailableNoOfEconomyClassSeats(flight.getAvailableNoOfEconomyClassSeats() - 1);
			flightClient.updateFlight(flight);

		} else if (seatType.equalsIgnoreCase("firstclass")) {
			checkAvailability(flight.getAvailableNoOfFirstClassSeats());
			flightDto.setFarePrice(flight.getPriceFirstClass());
			int seatNo = flight.getTotalNoOfSeats()-flight.getTotalNoOfBusinessClassSeats()-flight.getTotalNoOfEconomyClassSeats()-flight.getTottalNoOfFirstClassSeats() +(flight.getTottalNoOfFirstClassSeats()-flight.getAvailableNoOfFirstClassSeats())+1;
			flightDto.setSeatNo(seatNo);
			flightDto.setSeatType("FirstClass");
			flight.setAvailableNoOfFirstClassSeats(flight.getAvailableNoOfFirstClassSeats() - 1);
			flightClient.updateFlight(flight);

		} else {
			throw new ServiceException("No such class for seat");
		}
		
		User user = userClient.getUsers(uId).getBody();
		if(user == null) {
			throw new ServiceException("No such user Id exist : " + uId);
		}

		vo.setUser(user);
		vo.setFlight(flightDto);
		BookFlight booking = new BookFlight();
		booking.setfId(fId);
		booking.setuId(uId);
		booking.setSeatNo(flightDto.getSeatNo());
		booking.setSeatType(flightDto.getSeatType());
		booking.setFarePrice(flightDto.getFarePrice());
		booking = fr.save(booking);
		vo.setBookingId(booking.getbId()); 
		}catch(DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}catch(Exception e1) {
			throw new ServiceException(e1.getMessage());
		}
		return vo;
	}

	private void checkAvailability(int noOfClassSeats) throws ServiceException {

		if (noOfClassSeats == 0) {
			throw new ServiceException("seats for this class not available");
		}
	}

	@Override
	public Map<String, Object> getAllUsers(int fId) throws ServiceException {
		Map<String, Object> flightdata = new HashMap<>();
		try {
		Flight flight = flightClient.getFlight(fId);
		flightdata.put("flight", flight);
		List<Integer> uIds = fr.getUids(fId);
		List<User> users = uIds.stream().map(uId -> {
			// User u = resttemplate.getForObject("http://ZUULSERVICE/app/users/user/get/" +
			// uId, User.class);
			User u = userClient.getUsers(uId).getBody();
			
			return u;
		}).collect(Collectors.toList());

		flightdata.put("passangers", users);
		}catch(DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}catch(Exception e1) {
			throw new ServiceException(e1.getMessage());
		}

		return flightdata;
	}

	@Override
	public void cancelBooking(int bId) throws ServiceException {
		try {
		BookFlight booking = fr.findById(bId).orElseThrow(() -> new ServiceException("NO Such booking Id is there"));
		Flight flight = flightClient.getFlight(booking.getfId());
		if (booking.getSeatType().equalsIgnoreCase("business")) {
			flight.setAvaialbleNoOfBusinessClassSeats(flight.getAvaialbleNoOfBusinessClassSeats()+1);
		}
		else if (booking.getSeatType().equalsIgnoreCase("economy")) {
			flight.setAvailableNoOfEconomyClassSeats(flight.getAvailableNoOfEconomyClassSeats()+1);
		}
		else {
			flight.setAvailableNoOfFirstClassSeats(flight.getAvailableNoOfFirstClassSeats()+1);
		}
		flightClient.updateFlight(flight);
		fr.delete(booking);
		}catch(DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}catch(Exception e1) {
			throw new ServiceException(e1.getMessage());
		}

		
	}

	@Override
	public List<Flight> searchFlights(String source, String destination) {
		List<Flight> flights = flightClient.searchFlights(source, destination);
		return flights;
	}

	@Override
	public List<UserVo> yourBookings(int uId) throws ServiceException {
		List<UserVo> bookings = new ArrayList<UserVo>();
		try {
		List<BookFlight> bookedFlight =  fr.getBookings(uId);
		User user = userClient.getUsers(uId).getBody();
		if(user == null) {
			throw new ServiceException("No such user Id exist : " + uId);
		}
		if(bookedFlight.isEmpty()) {
			throw new ServiceException("---- You have No Booking History ----");
		}
		for (BookFlight bookFlight : bookedFlight) {
			Flight flight = flightClient.getFlight(bookFlight.getfId());
			FlightDto flightDto = mapper.map(flight,FlightDto.class);
			flightDto.setFarePrice(bookFlight.getFarePrice());
			flightDto.setSeatNo(bookFlight.getSeatNo());
			flightDto.setSeatType(bookFlight.getSeatType());
			UserVo vo = new UserVo();
			vo.setUser(user);
			vo.setBookingId(bookFlight.getbId());
			vo.setFlight(flightDto);
			bookings.add(vo);
		}
		
		}catch(DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}catch(Exception e1) {
			throw new ServiceException(e1.getMessage());
		}
		
		return bookings;
	}

}

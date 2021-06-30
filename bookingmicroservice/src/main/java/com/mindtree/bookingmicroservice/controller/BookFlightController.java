package com.mindtree.bookingmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.bookingmicroservice.exception.controllerexception.ControllerException;
import com.mindtree.bookingmicroservice.exception.serviceexception.ServiceException;
import com.mindtree.bookingmicroservice.service.BookFlightService;
import com.mindtree.bookingmicroservice.service.impl.BookFlightImpl;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/booking")
public class BookFlightController {

	@Autowired
	private BookFlightService fs = new BookFlightImpl();

	@CrossOrigin
	@RequestMapping("/searchFlight/{source}/{destination}")
	public ResponseEntity<?> searchFlights(@PathVariable("source") String source,
			@PathVariable("destination") String destination) throws ControllerException {
		try {
			return ResponseEntity.ok().body(fs.searchFlights(source, destination));
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping("/getall/{fId}")
	public ResponseEntity<?> getAllUsers(@PathVariable int fId) throws ControllerException {
		try {
			return ResponseEntity.ok().body(fs.getAllUsers(fId));
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
	@CrossOrigin
	@RequestMapping("/book-flight/{uId}/{fId}/{seatType}")
	public ResponseEntity<?> bookFlight(@PathVariable("uId") int uId, @PathVariable("fId") int fId,
			@PathVariable("seatType") String seatType) throws ControllerException {
		try {
			return ResponseEntity.ok().body(fs.bookFlight(uId, fId, seatType));
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	@CrossOrigin
	@RequestMapping("/yourBookings/{uId}")
	public ResponseEntity<?> yourBookings(@PathVariable("uId") int uId) throws ControllerException {
		try {
			return ResponseEntity.ok().body(fs.yourBookings(uId));
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
	@DeleteMapping("/cancel-booking/{bId}")
	public ResponseEntity<?> cancelBooking(@PathVariable("bId") int bId) throws ControllerException {
		try {
			fs.cancelBooking(bId);
			return new ResponseEntity<>("Deleted Successfull",HttpStatus.OK);
		} catch (ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}

}

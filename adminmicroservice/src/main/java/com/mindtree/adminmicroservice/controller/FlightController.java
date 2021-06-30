package com.mindtree.adminmicroservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.adminmicroservice.exceptions.ControllerException;
import com.mindtree.adminmicroservice.exceptions.ServiceException;
import com.mindtree.adminmicroservice.model.Flight;
import com.mindtree.adminmicroservice.service.FlightService;
import com.mindtree.adminmicroservice.service.implementation.FlightServiceImpl;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/flight")
public class FlightController {
	
	
	@Autowired
	private FlightService flightService = new FlightServiceImpl();
	@PostMapping("/add")
	public ResponseEntity<?> addFlight(@Valid @RequestBody Flight flight) throws ControllerException {
		try {
		return ResponseEntity.ok().body(flightService.addFlight(flight));
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	@CrossOrigin
	@GetMapping("/getdetails/{fId}")
	public ResponseEntity<?> getFlightDetails(@PathVariable int fId) throws ControllerException {
		try {
		return ResponseEntity.ok().body(flightService.getFlightDetails(fId));
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	@DeleteMapping("/delete/{fId}")
	public ResponseEntity<?> deleteFlight(@PathVariable int fId) throws ControllerException {
		try {
			flightService.deleteFlight(fId);
			return new ResponseEntity<>("Deleted Successfull",HttpStatus.OK);
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	@CrossOrigin
	@PutMapping("/update-Name/{fId}/{newName}")
	public ResponseEntity<?> updateName(@PathVariable int fId, @PathVariable String newName) throws ControllerException {
		try {
		return ResponseEntity.ok().body(flightService.updateFlightName(fId, newName));
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
	@CrossOrigin
	@GetMapping("/getAllFlights")
	public ResponseEntity<?> getAllFlights() throws ControllerException {
		try {
		return ResponseEntity.ok().body(flightService.getAllFlights());
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
	@CrossOrigin
	@GetMapping("/searchFlights/{source}/{destination}")
	public ResponseEntity<?> searchFlights(@PathVariable("source") String source,@PathVariable("destination") String destination) throws ControllerException {
		try {
		return ResponseEntity.ok().body(flightService.searchFlights(source,destination));
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
	@PutMapping("/update-source/{fId}/{newSource}")
	public ResponseEntity<?> updateSource(@PathVariable int fId, @PathVariable String newSource)throws ControllerException {
		try {
		return ResponseEntity.ok().body(flightService.updateFlightSource(fId, newSource));
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
	@PutMapping("/updateFlight")
	public ResponseEntity<?> updateFlight(@RequestBody Flight flight)throws ControllerException  {
		try {
		return ResponseEntity.ok().body(flightService.addFlight(flight));
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
	@RequestMapping("/update-destination/{fId}/{newDestination}")
	public ResponseEntity<?> updateDestination(@PathVariable int fId, @PathVariable String newDestination) throws ControllerException {
		try {
		return ResponseEntity.ok().body(flightService.updateFlightDestination(fId, newDestination));
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
}

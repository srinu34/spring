package com.mindtree.bookingmicroservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.bookingmicroservice.vo.Flight;

@FeignClient(url = "http://localhost:8989/app/flights/flight", name = "FLIGHT-CLIENT")
public interface FlightClient {

	@GetMapping("/getdetails/{fId}")
	Flight getFlight(@PathVariable("fId") int fId);
	
	@PutMapping("/updateFlight")
	void updateFlight(@RequestBody Flight flight);
	
	@GetMapping("/searchFlights/{source}/{destination}")
	List<Flight> searchFlights(@PathVariable String source,@PathVariable String destination);
}

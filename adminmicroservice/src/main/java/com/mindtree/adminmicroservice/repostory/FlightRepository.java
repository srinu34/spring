package com.mindtree.adminmicroservice.repostory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.adminmicroservice.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer>{

	@Query(value = "Select f from Flight f where f.source = ?1 and f.destination = ?2")
	public List<Flight> searchFlights(String source, String destination);

}

package com.mindtree.bookingmicroservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mindtree.bookingmicroservice.vo.User;

@FeignClient(url = "http://localhost:8989/app/users/user", name = "USER-CLIENT")
public interface UserClient {

	@GetMapping("/get/{uId}")
	public ResponseEntity<User> getUsers(@PathVariable("uId") int uId);

}

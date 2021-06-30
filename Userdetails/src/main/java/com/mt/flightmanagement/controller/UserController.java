package com.mt.flightmanagement.controller;



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

import com.mt.flightmanagement.controllerexception.ControllerException;
import com.mt.flightmanagement.entity.User;
import com.mt.flightmanagement.serviceexception.ServiceException;
import com.mt.flightmanagement.serviceimpl.UserServiceImpl;
import com.mt.flightmanagement.userservice.UserService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService us = new UserServiceImpl();
	
    @CrossOrigin
	@GetMapping("/get/{uId}")
	public ResponseEntity<?> getUser(@PathVariable int uId) throws ControllerException {
		try {
		return ResponseEntity.ok().body(us.getUserById(uId));
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
    @CrossOrigin
   	@GetMapping("/getByName/{name}")
   	public ResponseEntity<?> getUserByName(@PathVariable String name) throws ControllerException {
   		try {
   		return ResponseEntity.ok().body(us.findByName(name));
   		}catch(ServiceException e) {
   			throw new ControllerException(e.getMessage());
   		}
   	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAllUser() throws ControllerException {
		try {
		return ResponseEntity.ok().body(us.getUsers());
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	@PostMapping("/add")
	public ResponseEntity<?> addUser( @RequestBody User user) throws ControllerException{
		try {
		user=us.addUser(user);
		return new ResponseEntity<>(user,HttpStatus.OK);
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
		}
	@DeleteMapping("/delete/{uId}")
	public ResponseEntity<?> deleteUserById(@PathVariable int uId) throws ControllerException{
		try {
		return ResponseEntity.ok().body(us.deleteUser(uId));
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
	
	@PutMapping("/update-name/{uId}/{newName}")
	public ResponseEntity<?> updateNameById(@PathVariable int uId, @PathVariable String newName) throws ControllerException{
		try {
		return ResponseEntity.ok().body(us.updateName(uId, newName));
		}catch(ServiceException e) {
			throw new ControllerException(e.getMessage());
		}
	}
}

package com.mt.flightmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.mt.flightmanagement.entity.User;



@Repository
public interface UserDetailsRepo extends JpaRepository<User, Integer>{
	
	List<User> findByname(String name);

}
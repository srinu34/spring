package com.mindtree.bookingmicroservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class BookflightApplication {



	public static void main(String[] args) {
		SpringApplication.run(BookflightApplication.class, args);
		
	}


}

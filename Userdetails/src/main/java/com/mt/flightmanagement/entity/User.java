package com.mt.flightmanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uId;
	@NotNull(message = "user name is required")
	private String name;
	@Positive(message="Age must be a positive number")
	@Min(value = 5,message="If You are under 5 yrs old dont need a ticket")
	private int age;
	@NotNull(message="gender must be specified")
	private String gender;
	private String password;

	public User() {
		super();
	}
	

	public User(int uId, String name, int age, String gender) {
		super();
		this.uId = uId;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	

}

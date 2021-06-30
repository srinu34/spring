package com.mt.flightmanagement.userservice;

import java.util.List;

import com.mt.flightmanagement.entity.User;
import com.mt.flightmanagement.serviceexception.ServiceException;

public interface UserService {
	public User addUser(User user)throws ServiceException;
	public String deleteUser(int uId) throws ServiceException;
	public User updateName(int uId, String newName) throws ServiceException;
	public User getUserById(int uId) throws ServiceException;
	public List<User> findByName(String name) throws ServiceException;
	public List<User> getUsers()throws ServiceException;
}

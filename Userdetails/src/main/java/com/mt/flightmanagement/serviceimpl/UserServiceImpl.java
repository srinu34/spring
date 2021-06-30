
package com.mt.flightmanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mt.flightmanagement.entity.User;
import com.mt.flightmanagement.repository.UserDetailsRepo;
import com.mt.flightmanagement.serviceexception.ServiceException;
import com.mt.flightmanagement.userservice.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsRepo ur;

//	org.jboss.logging.Logger logger = LoggerFactory.logger(UserServiceImpl.class);

	@Override
	public User addUser(User user) throws ServiceException {
		try {
			return ur.save(user);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String deleteUser(int uId) throws ServiceException {
		User user = null;
		try {
//			user = ur.findById(uId).orElse(null);
			user = ur.findById(uId).orElseThrow(() -> new ServiceException("No Such User Id Exist"));
			ur.delete(user);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
		return "User : " + user.getName() + " Deleted Successfully";
	}

	@Override
	public User updateName(int uId, String newName) throws ServiceException {
		User user = null;
		try {
//			user = ur.findById(uId).orElse(null);
			user = ur.findById(uId).orElseThrow(() -> new ServiceException("No Such User Id Exist"));
			user.setName(newName);
			user = ur.save(user);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
		return user;
	}

	@Override
	public User getUserById(int uId) throws ServiceException {
		User user = null;
		try {
//			user = ur.findById(uId).orElse(null);
			user = ur.findById(uId).orElseThrow(() -> new ServiceException("No Such User Id Exist"));
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
		return user;
	}

	@Override
	public List<User> getUsers() throws ServiceException {
		List<User> users = ur.findAll();
		try {
			if (users.isEmpty()) {
				throw new ServiceException("No users exists ");
			}
			return users;
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<User> findByName(String name) throws ServiceException {

		try {
			if (name.isEmpty()) {
				throw new ServiceException("No Such User Name Exist");
			}
			return ur.findByname(name);
		} catch (DataAccessException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}

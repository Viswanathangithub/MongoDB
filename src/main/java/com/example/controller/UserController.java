package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserDao;
import com.example.model.UserDetails;
import com.example.repository.UserRepository;

@RestController
@RequestMapping(value="/users")
public class UserController implements ErrorController{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDao userDaoImpl;

	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<UserDetails> getUsers() {
		List<UserDetails> userList = userRepository.findAll();
		LOGGER.info("User List", userList);
		return userList;
	}
	
	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	public String getNameById(@PathVariable (required=true) String userId) {
		return userRepository.findNameById(userId);
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public UserDetails saveUser(@RequestBody UserDetails user) {
		return userRepository.save(user);
	}
	
	@RequestMapping(value="/address/{addressName}",method=RequestMethod.GET)
	public List<UserDetails> saveUser(@PathVariable (required=true) String addressName) {
		return userDaoImpl.getUserByAddress(addressName);
	}
	

	@Override
	public String getErrorPath() {
		LOGGER.info("Error Page");
		return "Error Path";
	}
}

package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.UserDao;
import com.example.exception.UserException;
import com.example.model.UserDetails;
import com.example.repository.UserRepository;

@RestController
@RefreshScope
@RequestMapping(value="/users")
public class UserController implements ErrorController{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDao userDaoImpl;

	@Value("${message: Config Server not working}")
	private String message;

	@RequestMapping(value="/message", method=RequestMethod.GET)
	public String getMessage() {
		return message;
	}

	@RequestMapping(value="/all", method=RequestMethod.GET)
	@Cacheable(value="findAllCache")
	public List<UserDetails> getUsers() {
		List<UserDetails> userList = userRepository.findAll();
		if(userList.size()<0) {
			throw new UserException("No Users Available");
		} else {
			LOGGER.info("User List" +userList.toString());
			return userList;
		}
	}

	@RequestMapping(value="/{userId}", method=RequestMethod.GET)
	public String getNameById(@PathVariable (required=true) String userId) {
		return userRepository.findNameById(userId);
	}

	@RequestMapping(value="/addUser",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public UserDetails saveUser(@RequestBody UserDetails user) {
		return userRepository.save(user);
	}

	@RequestMapping(value="/address",method=RequestMethod.GET)
	public List<UserDetails> getByAddress(@RequestParam (required=true) String addressName) {
		return userDaoImpl.getUserByAddress(addressName);
	}


	@Override
	public String getErrorPath() {
		LOGGER.info("Error Page");
		return "Error Path";
	}
}

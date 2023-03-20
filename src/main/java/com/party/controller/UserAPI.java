package com.party.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.party.dto.UserDTO;
import com.party.exception.PartyException;
import com.party.service.UserService;
import com.party.service.UserServiceImpl;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Validated
public class UserAPI {
	public static final Log LOGGER = LogFactory.getLog(UserAPI.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	Environment environment;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UserDTO user) throws PartyException{
//		LOGGER.info(user);
		String ret = userService.addUser(user);
		return new ResponseEntity<> (ret, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody UserDTO user ) throws PartyException {
//		LOGGER.info(user);
		String ret = userService.updateUser(user);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteUser/{userId}")
	public void deleteUser(@PathVariable int userId) throws PartyException{
		userService.deleteUser(userId);
	}
	
	@GetMapping("getUser/{userId}")
	public ResponseEntity<UserDTO> getById(@PathVariable int userId) throws PartyException{
		UserDTO ret = userService.getUserById(userId);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("getAllUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers() throws PartyException {
		List<UserDTO> ret = userService.getUsers();
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("getUser/{userName}/{password}")
	public ResponseEntity<UserDTO> getByUserNameAndPassword(@PathVariable String userName, @PathVariable String password) throws PartyException{
		UserDTO ret = userService.getUserByUserNameAndPassword(userName, password);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}

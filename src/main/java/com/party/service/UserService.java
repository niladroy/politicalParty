package com.party.service;

import java.util.List;

import com.party.dto.UserDTO;
import com.party.exception.PartyException;

public interface UserService {
	/**
	 * Add user to database if already present then throws PartyException
	 * @param user
	 * @return String
	 * @throws PartyException
	 */
	public String addUser(UserDTO user) throws PartyException;
	
	/**
	 * update user in database
	 * @param user
	 * @return String
	 * @throws PartyException
	 */
	public String updateUser(UserDTO user) throws PartyException;
	
	/**
	 * Delete user in database
	 * @param userId
	 * @throws PartyException
	 */
	public void deleteUser(int userId) throws PartyException;
	
	/**
	 * get user based on user ID
	 * @param userId
	 * @return UserDTO
	 * @throws PartyException
	 */
	public UserDTO getUserById(int userId) throws PartyException;
	
	/**
	 * returns all users present in database
	 * @return List<UserDTO>
	 * @throws PartyException
	 */
	public List<UserDTO> getUsers() throws PartyException;
	
	/**
	 * Gives user based on userName and password if credentials are correct
	 * @param userName
	 * @param password
	 * @return UserDTO
	 * @throws PartyException
	 */
	public UserDTO getUserByUserNameAndPassword(String userName, String password) throws PartyException;
}

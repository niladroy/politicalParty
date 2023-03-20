package com.party;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.party.dto.UserDTO;
import com.party.entity.Users;
import com.party.exception.PartyException;
import com.party.repository.UserRepository;
import com.party.service.UserService;
import com.party.service.UserServiceImpl;

@SpringBootTest
public class UserTest {
	@Mock 
	UserRepository userRepository;
	
	@Mock
	ModelMapper modelMapper;
	
	@InjectMocks
	UserService userService = new UserServiceImpl();
	
	public static UserDTO userDTO() {
		UserDTO user = new UserDTO();
		user.setUserId(1);
		user.setUserName("user");
		user.setPassword("pwd");
		return user;
	}
	
	public static Users users() {
		Users user = new Users();
		user.setUserId(1);
		user.setUserName("user");
		user.setPassword("pwd");
		
		return user;
	}
	
	@Test
	void validUserAddition() throws PartyException {
		UserDTO user = UserTest.userDTO();
		Mockito.when(modelMapper.map(userDTO(),Users.class)).thenReturn(users());
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());
		Assertions.assertEquals(userService.addUser(user), "Saved");
	}
	
	@Test
	void invalidUserAddition() throws PartyException {
		UserDTO user = UserTest.userDTO();
		Mockito.when(modelMapper.map(userDTO(), Users.class)).thenReturn(users());
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(users()));
		PartyException ex = Assertions.assertThrows(PartyException.class, () -> userService.addUser(user));
		Assertions.assertEquals(ex.getMessage(), "Service.USER_ALREADY_EXISTS");
	}
	

	@Test
	void validUserUpdate() throws PartyException {
		UserDTO user = UserTest.userDTO();

		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(users()));
		Assertions.assertEquals(userService.updateUser(user), "Updated");
	}
	
	@Test
	void invalidUserUpdate() throws PartyException {
		UserDTO user = UserTest.userDTO();

		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());
		PartyException ex = Assertions.assertThrows(PartyException.class, () -> userService.updateUser(user));
		Assertions.assertEquals(ex.getMessage(), "Service.USER_NOT_FOUND");
	}
}

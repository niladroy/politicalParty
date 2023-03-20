package com.party.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.party.dto.ContactDTO;
import com.party.dto.EventDTO;
import com.party.dto.MerchandiseDTO;
import com.party.dto.UserDTO;
import com.party.entity.Contact;
import com.party.entity.Event;
import com.party.entity.Merchandise;
import com.party.entity.Users;
import com.party.exception.PartyException;
import com.party.repository.EventRepository;
import com.party.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	public static final Log LOGGER = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String addUser(UserDTO user) throws PartyException {
//		LOGGER.info(user);
//		modelMapper.getConfiguration()
//		  .setMatchingStrategy(MatchingStrategies.LOOSE);
		Users userEntity =  modelMapper.map(user, Users.class);
		Optional<Users> fromRepo = userRepository.findById(user.getUserId());
		if(fromRepo.isPresent()) {
			throw new PartyException("Service.USER_ALREADY_EXISTS");
		}
		userRepository.save(userEntity);
		return "Saved";
	}

	@Override
	public String updateUser(UserDTO user) throws PartyException {
		Optional<Users> fromRepo = userRepository.findById(user.getUserId());
		Users userFromRepo = fromRepo.orElseThrow(() -> new PartyException("Service.USER_NOT_FOUND"));
		userFromRepo.setUserName(user.getUserName());
		userFromRepo.setPassword(user.getPassword());
		userFromRepo.setRole(user.getRole());
		userFromRepo.setFirstName(user.getFirstName());
		userFromRepo.setLastName(user.getLastName());
		List<ContactDTO> contactDTO = user.getContacts();
//		LOGGER.info(dtoList);
		List<Contact> contacts = new ArrayList<>();
		if(contactDTO != null) {
			contactDTO.forEach((dto)->{
				contacts.add(modelMapper.map(dto, Contact.class));
			});
			userFromRepo.setContacts(contacts);
		}
		List<EventDTO> eventDTO = user.getEvents();
//		LOGGER.info(eventDTO);
		List<Event> events = new ArrayList<>();
		if(eventDTO != null) {
			eventDTO.forEach((dto) -> {
				events.add(modelMapper.map(dto, Event.class));
			});
			userFromRepo.setEvents(events);
		}
		
		List<MerchandiseDTO> merchDTO = user.getMerchandises();
		List<Merchandise> merch = new ArrayList<>();
		if(merchDTO != null) {
			merchDTO.forEach((dto) -> {
				merch.add(modelMapper.map(dto, Merchandise.class));
			});
			userFromRepo.setMerchandises(merch);
		}
		return "Updated";
		
	}

	@Override
	public void deleteUser(int userId) throws PartyException {
		Optional<Users> fromRepo = userRepository.findById(userId);
		Users user = fromRepo.orElseThrow(() -> new PartyException("Service.USER_NOT_FOUND"));
		userRepository.delete(user);
		
	}

	@Override
	public UserDTO getUserById(int userId) throws PartyException {
		Optional<Users> fromRepo = userRepository.findById(userId);
		Users user = fromRepo.orElseThrow(() -> new PartyException("Service.USER_NOT_FOUND"));
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO getUserByUserNameAndPassword(String userName, String password) throws PartyException {
		Optional<Users> fromRepo = userRepository.findByUserName(userName);
		Users user = fromRepo.orElseThrow(() -> new PartyException("Service.USER_NOT_FOUND"));
		if(!user.getPassword().equals(password)) {
			throw new PartyException("Service.INCORRECT_PASSWORD");
		}
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getUsers() throws PartyException {
		List<Users> users = userRepository.findAll();
		List<UserDTO> ret = new ArrayList<>();
		users.forEach((user) -> {
			ret.add(modelMapper.map(user, UserDTO.class));
		});
		return ret;
	}

}


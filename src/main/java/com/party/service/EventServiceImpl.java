package com.party.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.party.dto.EventDTO;
import com.party.entity.Event;
import com.party.exception.PartyException;
import com.party.repository.EventRepository;

@Service
@Transactional
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public String addEvent(EventDTO event) throws PartyException {
		Event eventEntity =  modelMapper.map(event, Event.class);
		Optional<Event> fromRepo = eventRepository.findById(event.getEventId());
		if(fromRepo.isPresent()) {
			throw new PartyException("Service.EVENT_ALREADY_EXISTS");
		}
		eventRepository.save(eventEntity);
		return "Saved";
	}

	@Override
	public String updateEvent(EventDTO event) throws PartyException {
		Optional<Event> fromRepo = eventRepository.findById(event.getEventId());
		Event eventFromRepo = fromRepo.orElseThrow(() -> new PartyException("Service.EVENT_NOT_FOUND"));
		eventFromRepo.setEventName(event.getEventName());
		eventFromRepo.setDescription(event.getDescription());
		eventFromRepo.setDate(event.getDate());
		eventFromRepo.setLocation(event.getLocation());
		eventFromRepo.setStartTime(event.getStartTime());
		eventFromRepo.setEndTime(event.getEndTime());
		return "Updated";
	}

	@Override
	public void deleteEvent(int eventId) throws PartyException {
		Optional<Event> fromRepo = eventRepository.findById(eventId);
		Event event = fromRepo.orElseThrow(() -> new PartyException("Service.EVENT_NOT_FOUND"));
		eventRepository.delete(event);
	}

	@Override
	public EventDTO getEventById(int eventId) throws PartyException {
		Optional<Event> fromRepo = eventRepository.findById(eventId);
		Event event = fromRepo.orElseThrow(() -> new PartyException("Service.EVENT_NOT_FOUND"));
		EventDTO eventDTO = modelMapper.map(event, EventDTO.class);
		return eventDTO;
	}

	@Override
	public List<EventDTO> getEvents() throws PartyException {
		List<Event> events = eventRepository.findAll();
		List<EventDTO> ret = new ArrayList<>();
		events.forEach((event) -> {
			ret.add(modelMapper.map(event, EventDTO.class));
		});
		return ret;
	}

}

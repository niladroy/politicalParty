package com.party;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.party.dto.EventDTO;
import com.party.entity.Event;
import com.party.exception.PartyException;
import com.party.repository.EventRepository;
import com.party.service.EventService;
import com.party.service.EventServiceImpl;

@SpringBootTest
public class EventTest {
	
	@Mock 
	EventRepository eventRepository;
	
	@Mock
	ModelMapper modelMapper;
	
	@InjectMocks
	EventService eventService = new EventServiceImpl();
	
	public static EventDTO eventDTO() {
		EventDTO event = new EventDTO();
		event.setEventId(1);
		event.setEventName("event");
		event.setDescription("event description");
		event.setDate(LocalDate.of(2022, 10, 10));
		event.setLocation("India");
		event.setStartTime(LocalTime.of(10, 30));
		event.setEndTime(LocalTime.of(11, 30));
		return event;
	}
	
	public static Event event() {
		Event event = new Event();
		event.setEventId(1);
		event.setEventName("event");
		event.setDescription("event description");
		event.setDate(LocalDate.of(2022, 10, 10));
		event.setLocation("India");
		event.setStartTime(LocalTime.of(10, 30));
		event.setEndTime(LocalTime.of(11, 30));
		return event;
	}
	
	@Test
	void validEventAddition() throws PartyException {
		EventDTO event = EventTest.eventDTO();
		Mockito.when(modelMapper.map(eventDTO(), Event.class)).thenReturn(event());
		Mockito.when(eventRepository.findById(event.getEventId())).thenReturn(Optional.empty());
		Assertions.assertEquals(eventService.addEvent(event), "Saved");
	}
	
	@Test
	void invalidEventAddition() throws PartyException {
		EventDTO event = EventTest.eventDTO();
		Mockito.when(modelMapper.map(eventDTO(), Event.class)).thenReturn(event());
		Mockito.when(eventRepository.findById(event.getEventId())).thenReturn(Optional.of(event()));
		PartyException ex = Assertions.assertThrows(PartyException.class, () -> eventService.addEvent(event));
		Assertions.assertEquals(ex.getMessage(), "Service.EVENT_ALREADY_EXISTS");
	}
	
	@Test
	void validEventUpdate() throws PartyException {
		EventDTO event = EventTest.eventDTO();
		Mockito.when(eventRepository.findById(event.getEventId())).thenReturn(Optional.of(event()));
		Assertions.assertEquals(eventService.updateEvent(event), "Updated");
	}
	
	@Test
	void invalidEventUpdate() throws PartyException {
		EventDTO event = EventTest.eventDTO();
		Mockito.when(eventRepository.findById(event.getEventId())).thenReturn(Optional.empty());
		PartyException ex = Assertions.assertThrows(PartyException.class, () -> eventService.updateEvent(event));
		Assertions.assertEquals(ex.getMessage(), "Service.EVENT_NOT_FOUND");
	}
	
}

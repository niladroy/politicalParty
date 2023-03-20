package com.party.service;

import java.util.List;

import com.party.dto.EventDTO;
import com.party.exception.PartyException;

public interface EventService {
	public String addEvent(EventDTO event) throws PartyException;
	public String updateEvent(EventDTO event) throws PartyException;
	public void deleteEvent(int eventId) throws PartyException;
	public EventDTO getEventById(int eventId) throws PartyException;
	public List<EventDTO> getEvents() throws PartyException;
}

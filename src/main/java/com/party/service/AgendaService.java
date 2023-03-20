package com.party.service;

import java.util.List;

import com.party.dto.AgendaDTO;
import com.party.exception.PartyException;

public interface AgendaService {
	/**
	 * Add agenda to database
	 * @param agenda
	 * @return String
	 * @throws PartyException
	 */
	public String addAgenda(AgendaDTO agenda) throws PartyException;
	
	/**
	 * Update agenda present in database
	 * @param agenda
	 * @return String
	 * @throws PartyException
	 */
	public String updateAgenda(AgendaDTO agenda) throws PartyException;
	
	/**
	 * Delete Agenda present in database
	 * @param id
	 * @throws PartyException
	 */
	public void deleteAgenda(int id) throws PartyException;
	
	/**
	 * get Agenda based on ID
	 * @param id
	 * @return AgendaDTO
	 * @throws PartyException
	 */
	public AgendaDTO getAgendaById(int id) throws PartyException;
	
	/**
	 * Get all agendas present in database
	 * @return List<AgendaDTO>
	 * @throws PartyException
	 */
	public List<AgendaDTO> getAgendas() throws PartyException;
}

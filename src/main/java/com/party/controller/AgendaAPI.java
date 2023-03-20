package com.party.controller;

import java.util.List;

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

import com.party.dto.AgendaDTO;
import com.party.exception.PartyException;
import com.party.service.AgendaService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AgendaAPI {
	
	@Autowired
	AgendaService agendaService;
	
	@Autowired
	Environment environment;
	
	/**
	 * Add a new agenda
	 * @param agenda
	 * @return ResponseEntity<String>
	 * @throws PartyException
	 */
	@PostMapping("/addAgenda")
	public ResponseEntity<String> addAgenda(@RequestBody AgendaDTO agenda) throws PartyException {
		String ret = agendaService.addAgenda(agenda);
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	
	/**
	 * Update existing agenda
	 * @param agenda
	 * @return ResponseEntity<String>
	 * @throws PartyException
	 */
	@PutMapping("/updateAgenda")
	public ResponseEntity<String> updateAgenda(@RequestBody AgendaDTO agenda) throws PartyException {
		String ret = agendaService.updateAgenda(agenda);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/**
	 * Delete Agenda
	 * @param agendaId
	 * @throws PartyException
	 */
	@DeleteMapping("/deleteAgenda/{agendaId}")
	public void deleteAgenda(@PathVariable int agendaId) throws PartyException{
		agendaService.deleteAgenda(agendaId);
	}
	
	/**
	 * Get agenda based on agenda ID
	 * @param agendaId
	 * @return ResponseEntity<AgendaDTO>
	 * @throws PartyException
	 */
	@GetMapping("/getAgenda/{agendaId}")
	public ResponseEntity<AgendaDTO> getAgendaByID(@PathVariable int agendaId) throws PartyException{
		AgendaDTO ret = agendaService.getAgendaById(agendaId);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	/**
	 * Get All agendas
	 * @return ResponseEntity<List<AgendaDTO>>
	 * @throws PartyException
	 */
	@GetMapping("/getAgendas")
	public ResponseEntity<List<AgendaDTO>> getAgendas() throws PartyException {
		List<AgendaDTO> ret = agendaService.getAgendas();
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}

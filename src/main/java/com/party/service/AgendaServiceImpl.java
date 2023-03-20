package com.party.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.party.dto.AgendaDTO;
import com.party.entity.Agenda;
import com.party.exception.PartyException;
import com.party.repository.AgendaRepository;


@Transactional
@Service
public class AgendaServiceImpl implements AgendaService {
	
	@Autowired
	AgendaRepository agendaRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public String addAgenda(AgendaDTO agenda) throws PartyException {
		Agenda agendaEntity = modelMapper.map(agenda, Agenda.class);
		Optional<Agenda> fromRepo = agendaRepository.findById(agenda.getAgendaId());
		if(fromRepo.isPresent()) {
			throw new PartyException("Service.AGENDA_ALREADY_EXISTS");
		}
		agendaRepository.save(agendaEntity);
		return "Saved";
	}

	@Override
	public String updateAgenda(AgendaDTO agenda) throws PartyException {
		Optional<Agenda> fromRepo = agendaRepository.findById(agenda.getAgendaId());
		Agenda agendaFromRepo = fromRepo.orElseThrow(() -> new PartyException("Service.AGENDA_NOT_FOUND"));
		agendaFromRepo.setDescription(agenda.getDescription());
		return "Updated";
	}

	@Override
	public void deleteAgenda(int id) throws PartyException {
		Optional<Agenda> fromRepo = agendaRepository.findById(id);
		Agenda agenda = fromRepo.orElseThrow(() -> new PartyException("Service.AGENDA_NOT_FOUND"));
		agendaRepository.delete(agenda);
	}

	@Override
	public AgendaDTO getAgendaById(int id) throws PartyException {
		Optional<Agenda> fromRepo = agendaRepository.findById(id);
		Agenda agenda = fromRepo.orElseThrow(() -> new PartyException("Service.AGENDA_NOT_FOUND"));
		return modelMapper.map(agenda, AgendaDTO.class);
	}

	@Override
	public List<AgendaDTO> getAgendas() throws PartyException {
		List<Agenda> agendas = agendaRepository.findAll();
		List<AgendaDTO> ret = new ArrayList<>();
		agendas.forEach((agenda) -> {
			ret.add(modelMapper.map(agenda, AgendaDTO.class));
		});
		return ret;
	}

}

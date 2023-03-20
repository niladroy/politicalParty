package com.party;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.party.dto.AgendaDTO;
import com.party.entity.Agenda;
import com.party.exception.PartyException;
import com.party.repository.AgendaRepository;
import com.party.service.AgendaService;
import com.party.service.AgendaServiceImpl;

@SpringBootTest
public class AgendaTest {

	@Mock
	AgendaRepository agendaRepository;
	
	@Mock
	ModelMapper modelMapper;
	
	@InjectMocks
	AgendaService agendaService = new AgendaServiceImpl();
	
	public static AgendaDTO agendaDTO() {
		AgendaDTO agenda = new AgendaDTO();
		agenda.setAgendaId(1);
		agenda.setDescription("agenda");
		return agenda;
	}
	
	public static Agenda agenda() {
		Agenda agenda = new Agenda();
		agenda.setAgendaId(1);
		agenda.setDescription("agenda");
		return agenda;
	}
	
	@Test
	void validaAgendaAddition() throws PartyException {
		AgendaDTO agenda = AgendaTest.agendaDTO();
		Mockito.when(modelMapper.map(agendaDTO(), Agenda.class)).thenReturn(agenda());
		Mockito.when(agendaRepository.findById(agenda.getAgendaId())).thenReturn(Optional.empty());
		Assertions.assertEquals(agendaService.addAgenda(agenda), "Saved");
	}
	
	@Test
	void invalidAgendaAddition() throws PartyException {
		AgendaDTO agenda = AgendaTest.agendaDTO();
		Mockito.when(modelMapper.map(agendaDTO(), Agenda.class)).thenReturn(agenda());
		Mockito.when(agendaRepository.findById(agenda.getAgendaId())).thenReturn(Optional.of(agenda()));
		PartyException ex = Assertions.assertThrows(PartyException.class, () -> agendaService.addAgenda(agenda));
		Assertions.assertEquals(ex.getMessage(), "Service.AGENDA_ALREADY_EXISTS");
	}
	
	@Test
	void validAgendaUpdate() throws PartyException {
		AgendaDTO agenda = AgendaTest.agendaDTO();
		Mockito.when(agendaRepository.findById(agenda.getAgendaId())).thenReturn(Optional.of(agenda()));
		Assertions.assertEquals(agendaService.updateAgenda(agenda), "Updated");
	}
	
	@Test
	void invalidAgendaUpdate() throws PartyException {
		AgendaDTO agenda = AgendaTest.agendaDTO();
		Mockito.when(agendaRepository.findById(agenda.getAgendaId())).thenReturn(Optional.empty());
		PartyException ex = Assertions.assertThrows(PartyException.class, () -> agendaService.updateAgenda(agenda));
		Assertions.assertEquals(ex.getMessage(), "Service.AGENDA_NOT_FOUND");
	}
}

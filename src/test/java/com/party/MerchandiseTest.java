package com.party;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.party.dto.EventDTO;
import com.party.dto.MerchandiseDTO;
import com.party.entity.Event;
import com.party.entity.Merchandise;
import com.party.exception.PartyException;
import com.party.repository.EventRepository;
import com.party.repository.MerchandiseRepository;
import com.party.service.EventService;
import com.party.service.EventServiceImpl;
import com.party.service.MerchandiseService;
import com.party.service.MerchandiseServiceImpl;

@SpringBootTest
public class MerchandiseTest {
	
	@Mock 
	MerchandiseRepository merchandiseRepository;
	
	@Mock
	ModelMapper modelMapper;
	
	@InjectMocks
	MerchandiseService merchandiseService = new MerchandiseServiceImpl();
	
	public static MerchandiseDTO merchandiseDTO() {
		MerchandiseDTO merchandise = new MerchandiseDTO();
		merchandise.setItemId(1);
		merchandise.setItemName("merchandise name");
		merchandise.setPrice(100);
		return merchandise;
	}
	
	public static Merchandise merchandise() {
		Merchandise merchandise = new Merchandise();
		merchandise.setItemId(1);
		merchandise.setItemName("merchandise name");
		merchandise.setPrice(100);
		
		return merchandise;
	}
	
	@Test
	void validMerchandiseAddition() throws PartyException {
		MerchandiseDTO merchandise = MerchandiseTest.merchandiseDTO();
		Mockito.when(modelMapper.map(merchandiseDTO(), Merchandise.class)).thenReturn(merchandise());
		Mockito.when(merchandiseRepository.findById(merchandise.getItemId())).thenReturn(Optional.empty());
		Assertions.assertEquals(merchandiseService.addItem(merchandise), "Saved");
	}
	
	@Test
	void invalidMerchandiseAddition() throws PartyException {
		MerchandiseDTO merchandise = MerchandiseTest.merchandiseDTO();
		Mockito.when(modelMapper.map(merchandiseDTO(), Merchandise.class)).thenReturn(merchandise());
		Mockito.when(merchandiseRepository.findById(merchandise.getItemId())).thenReturn(Optional.of(merchandise()));
		PartyException ex = Assertions.assertThrows(PartyException.class, () -> merchandiseService.addItem(merchandise));
		Assertions.assertEquals(ex.getMessage(), "Service.MERCH_ALREADY_PRESENT");
	}
	
	@Test
	void validMerchandiseUpdate() throws PartyException {
		MerchandiseDTO merchandise = MerchandiseTest.merchandiseDTO();
		Mockito.when(merchandiseRepository.findById(merchandise.getItemId())).thenReturn(Optional.of(merchandise()));
		Assertions.assertEquals(merchandiseService.updateItem(merchandise), "Updated");
	}
	
	@Test
	void invalidEventUpdate() throws PartyException {
		MerchandiseDTO merchandise = MerchandiseTest.merchandiseDTO();
		Mockito.when(merchandiseRepository.findById(merchandise.getItemId())).thenReturn(Optional.empty());
		PartyException ex = Assertions.assertThrows(PartyException.class, () -> merchandiseService.updateItem(merchandise));
		Assertions.assertEquals(ex.getMessage(), "Service.MERCH_NOT_PRESENT");
	}
	
//	@Test
//	void validViewEvent() throws PartyException {
//		EventDTO event = EventTest.eventDTO();
//		Mockito.when(modelMapper.map(event, Event.class)).thenReturn(event());
//		Mockito.when(eventRepository.findById(event.getEventId())).thenReturn(Optional.of(EventTest.event()));
//		Assertions.assertEquals( event, eventService.getEventById(event.getEventId()));
//	}
}

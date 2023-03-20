package com.party;



import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.party.dto.ContactDTO;
import com.party.entity.Contact;
import com.party.exception.PartyException;
import com.party.repository.ContactRepository;
import com.party.service.ContactService;
import com.party.service.ContactServiceImpl;

@SpringBootTest
public class ContactTest {
	
	@Mock 
	ContactRepository contactRepository;
	
	@Mock
	ModelMapper modelMapper;
	
	@InjectMocks
	ContactService contactService = new ContactServiceImpl();
	
	public static ContactDTO contactDTO() {
		ContactDTO contact = new ContactDTO();
		contact.setContactId(1);
		contact.setName("contact");
		contact.setDescription("contact description");
		return contact;
	}
	
	public static Contact contact() {
		Contact contact = new Contact();
		contact.setContactId(1);
		contact.setName("contact");
		contact.setDescription("contact description");
		return contact;
	}
	
	@Test
	void validContactAddition() throws PartyException {
		ContactDTO contact = ContactTest.contactDTO();
		Mockito.when(modelMapper.map(contactDTO(), Contact.class)).thenReturn(contact());
		Mockito.when(contactRepository.findById(contact.getContactId())).thenReturn(Optional.empty());
		Assertions.assertEquals (contactService.addContact(contact), "Contact added successfully");
	}
	
	@Test
	void invalidContactAddition() throws PartyException {
		ContactDTO contact = ContactTest.contactDTO();
		Mockito.when(modelMapper.map(contactDTO(), Contact.class)).thenReturn(contact());
		Mockito.when(contactRepository.findById(contact.getContactId())).thenReturn(Optional.of(contact()));
		PartyException ex = Assertions.assertThrows(PartyException.class, () -> contactService.addContact(contact));
		Assertions.assertEquals(ex.getMessage(), "Service.Contact_ALREADY_EXISTS");
	}
	
	@Test
	void validContactUpdate() throws PartyException {
		ContactDTO contact = ContactTest.contactDTO();
		Mockito.when(contactRepository.findById(contact.getContactId())).thenReturn(Optional.of(contact()));
		Assertions.assertEquals (contactService.updateContact(contact), "Contact updated successfully");
	}
	
	@Test
	void invalidContactUpdate() throws PartyException {
		ContactDTO contact = ContactTest.contactDTO();
		Mockito.when(contactRepository.findById(contact.getContactId())).thenReturn(Optional.empty());
		PartyException ex = Assertions.assertThrows(PartyException.class, () -> contactService.updateContact(contact));
		Assertions.assertEquals(ex.getMessage(), "Contact not found");
	}
	
}

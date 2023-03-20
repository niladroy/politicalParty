package com.party.service;

import java.util.*;

import com.party.dto.ContactDTO;
import com.party.exception.PartyException;

public interface ContactService {
	public String addContact(ContactDTO contact) throws PartyException;
	public String updateContact(ContactDTO contact) throws PartyException;
	public void deleteContact(int contactId) throws PartyException;
	public List<ContactDTO> getContacts() throws PartyException;
}


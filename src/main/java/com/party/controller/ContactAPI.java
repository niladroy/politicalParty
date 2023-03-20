package com.party.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.party.dto.ContactDTO;
import com.party.exception.PartyException;
import com.party.service.ContactService;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin("*")
@Validated
public class ContactAPI {

    @Autowired
    private ContactService contactService;

    @GetMapping("/ViewAllContact")
    public ResponseEntity<List<ContactDTO>> getContacts() throws PartyException {
        List<ContactDTO> contacts = contactService.getContacts();
        return ResponseEntity.ok().body(contacts);
    }

    @PostMapping("/AddContact")
    public ResponseEntity<String> addContact(@RequestBody ContactDTO contactDTO) throws PartyException {
        String message = contactService.addContact(contactDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PutMapping("/UpdateContact")
    public ResponseEntity<String> updateContact(@RequestBody ContactDTO contactDTO) throws PartyException {
        String message = contactService.updateContact(contactDTO);
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/DeleteContact")
    public ResponseEntity<String> deleteContact(@PathVariable int contactId) throws PartyException {
        contactService.deleteContact(contactId);
        return ResponseEntity.ok().body("Contact deleted successfully");
    }
}

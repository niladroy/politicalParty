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

import com.party.dto.MerchandiseDTO;
import com.party.exception.PartyException;
import com.party.service.MerchandiseService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Validated
public class MerchandiseAPI {
		
	@Autowired
	MerchandiseService merchandiseService;
	
	@Autowired
	Environment environment;
	/**
	 * Add item to the database
	 * @param merchandiseDTO
	 * @return ResponseEntity
	 * @throws PartyException
	 */
	@PostMapping("/addItem")
	public ResponseEntity<String> addItem(@RequestBody MerchandiseDTO merchandiseDTO) throws PartyException {
		String ret = merchandiseService.addItem(merchandiseDTO);
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	/**
	 * Update item that is already present in database
	 * @param merchandise
	 * @return ResponseEntity
	 * @throws PartyException
	 */
	@PutMapping("/updateItem")
	public ResponseEntity<String> updateItem(@RequestBody MerchandiseDTO merchandise) throws PartyException {
		String ret = merchandiseService.updateItem(merchandise);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
/**
 * Delete item that is present in database 
 * @param itemId
 * @throws PartyException
 */
	@DeleteMapping("/deleteItem/{itemId}")
	public void deleteItem(@PathVariable int itemId) throws PartyException {
		merchandiseService.deleteItem(itemId);
	}
	/**
	 * Get all the item that are peresent in database
	 * @return
	 * @throws PartyException
	 */
	@GetMapping("/getItems")
	public ResponseEntity<List<MerchandiseDTO>> getAllItems() throws PartyException {
		List<MerchandiseDTO> ret =  merchandiseService.getAllItems();
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	/**
	 * Get item by id 
	 * @param itemId
	 * @return
	 * @throws PartyException
	 */
	@GetMapping("/getItem/{itemId}")
	public ResponseEntity<MerchandiseDTO> getItemById(@PathVariable int itemId) throws PartyException {
		MerchandiseDTO ret = merchandiseService.getItemByID(itemId);
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}

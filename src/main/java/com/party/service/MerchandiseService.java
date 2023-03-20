package com.party.service;

import java.util.List;

import com.party.dto.MerchandiseDTO;
import com.party.exception.PartyException;

public interface MerchandiseService {
	public String addItem(MerchandiseDTO merch) throws PartyException;
	public String updateItem(MerchandiseDTO merch) throws PartyException;
	public void deleteItem(int id) throws PartyException;
	public MerchandiseDTO getItemByID(int id) throws PartyException;
	public List<MerchandiseDTO> getAllItems() throws PartyException;
}

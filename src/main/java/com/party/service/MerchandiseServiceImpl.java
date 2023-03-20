package com.party.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.party.dto.MerchandiseDTO;
import com.party.entity.Merchandise;
import com.party.exception.PartyException;
import com.party.repository.MerchandiseRepository;

@Transactional
@Service
public class MerchandiseServiceImpl implements MerchandiseService {
	
	@Autowired
	MerchandiseRepository merchRepo;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
	public String addItem(MerchandiseDTO merch) throws PartyException {
		Merchandise merchEntity = modelMapper.map(merch, Merchandise.class);
		Optional<Merchandise> fromRepo = merchRepo.findById(merch.getItemId());
		if(fromRepo.isPresent()) {
			throw new PartyException("Service.MERCH_ALREADY_PRESENT");
		}
		merchRepo.save(merchEntity);
		return "Saved";
	}

	@Override
	public String updateItem(MerchandiseDTO merch) throws PartyException {
		Optional<Merchandise> fromRepo = merchRepo.findById(merch.getItemId());
		Merchandise  merchFromRepo = fromRepo.orElseThrow(()-> new PartyException("Service.MERCH_NOT_PRESENT"));
		merchFromRepo.setItemName(merch.getItemName());
		merchFromRepo.setPrice(merch.getPrice());
		return "Updated";
	}

	@Override
	public void deleteItem(int id) throws PartyException {
		Optional<Merchandise> fromRepo = merchRepo.findById(id);
		Merchandise  merchFromRepo = fromRepo.orElseThrow(()-> new PartyException("Service.MERCH_NOT_PRESENT"));
		merchRepo.delete(merchFromRepo);
	}

	@Override
	public MerchandiseDTO getItemByID(int id) throws PartyException {
		Optional<Merchandise> fromRepo = merchRepo.findById(id);
		Merchandise  merchFromRepo = fromRepo.orElseThrow(()-> new PartyException("Service.MERCH_NOT_PRESENT"));
		return modelMapper.map(merchFromRepo, MerchandiseDTO.class);
	}

	@Override
	public List<MerchandiseDTO> getAllItems() throws PartyException {
		List<Merchandise> fromRepo = merchRepo.findAll();
		List<MerchandiseDTO> ret = new ArrayList<>();
		fromRepo.forEach((merch) -> {
			ret.add(modelMapper.map(merch, MerchandiseDTO.class));
		});
		return ret;
	}

}

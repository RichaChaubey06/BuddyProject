package com.ust.buddy.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ust.buddy.entity.Associate;
import com.ust.buddy.repository.AssociateRepository;
import com.ust.buddy.repository.BuddyRepository;
import com.ust.buddy.util.UuidUtil;

@Service
public class AssociateService {
	
	@Autowired
	AssociateRepository associateRepository;
	@Autowired
	BuddyRepository buddyRepository;
	
	public Associate saveAssociate(Associate associate) {
		
		associate.setId(UuidUtil.getUuid());
		
	return	associateRepository.save(associate);	
		
	}
	
public Associate updateAssociate(Associate associate) {
		
	
	return	associateRepository.save(associate);	
		
	}
	
	
	public void saveAssociates(List<Associate> list) {
		
		associateRepository.saveAll(list);
	}
	
	public List<Associate> getAllAssociates() {
		
		return associateRepository.findAll();
	}
	
	
	public Associate findAssociatesById(String associate_id){
		
		Optional o=  associateRepository.findByAssociateId(associate_id);
		if(o.isPresent()) {
			return (Associate) o.get();
		}
		return null;
	}
	
	
	public boolean deleteAssociateById(String associate_id) {
		
//		assciateRepository.deleteByAssociateId(associate_id);
		Optional<Associate> findByAssociateId = associateRepository.findByAssociateId(associate_id);
		boolean ifPresent = findByAssociateId.isPresent();
		if(ifPresent) {
			Associate associate = findByAssociateId.get();
			associateRepository.deleteById(associate.getId());
			return true;
		}
		return false;
	}
	
}

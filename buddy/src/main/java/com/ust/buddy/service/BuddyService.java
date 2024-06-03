package com.ust.buddy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.buddy.entity.Associate;
import com.ust.buddy.entity.Buddy;
import com.ust.buddy.repository.AssociateRepository;
import com.ust.buddy.repository.BuddyRepository;
import com.ust.buddy.util.UuidUtil;

@Service
public class BuddyService {

	@Autowired
	BuddyRepository buddyRepo;
	@Autowired
	AssociateRepository associateRepo;

	public Buddy createBuddy(Buddy buddy) {

		buddy.setId(UuidUtil.getUuid());
		return buddyRepo.save(buddy);

	}

	/*
	public Buddy findBuddyById(String buddy_id) {

		Optional<Buddy> o = buddyRepo.findByBuddy_id(buddy_id);
		if (o.isPresent()) {
			return (Buddy) o.get();
		}
		return null;
	}

*/
	
	public Buddy findBuddyById(String buddy_id) {
	    Optional<Buddy> o = buddyRepo.findByBuddy_id(buddy_id);
	    if (o.isPresent()) {
	        Buddy buddy = o.get();
	        System.out.println("Buddy found: " + buddy);
	        return buddy;
	    } else {
	        System.out.println("Buddy not found for ID: " + buddy_id);
	        return null;
	    }
	}
	
	public List<Buddy> getAllBuddy() {

		return buddyRepo.findAll();
	}

	public Buddy updateBuddy(Buddy buddy) {

		return buddyRepo.save(buddy);

	}

	public Buddy updateBuddyWithAssociate(String buddy_id, List<String> associate_ids) {

		try {
			ArrayList<Associate> listAssociate = new ArrayList<Associate>();

			Optional<Buddy> buddyOptional = buddyRepo.findByBuddy_id(buddy_id);
			if (buddyOptional.isPresent()) {
				Buddy buddy = buddyOptional.get();
				for (String associate_id : associate_ids) {
					Optional<Associate> optional = associateRepo.findByAssociateId(associate_id);
					if (optional.isPresent()) {
						Associate associate = optional.get();
						associate.setBuddy(buddy);
						listAssociate.add(associate);

					}

				}
				buddy.setAssociate(listAssociate);
				return buddyRepo.save(buddy);

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteBuddyById(String buddy_id) {
		try {
			Optional<Buddy> findByBuddy_id = buddyRepo.findByBuddy_id(buddy_id);
			boolean ifPresent = findByBuddy_id.isPresent();
			if (ifPresent) {
				Buddy buddy = findByBuddy_id.get();
				buddyRepo.deleteById(buddy.getId());
				return true;
			}
			return true;

		} catch (Exception e) {
			return false;

		}

	}

}

//service and controller class ......write try catch for methods 
//bind associates with buddy in db
//
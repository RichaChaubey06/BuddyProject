package com.ust.buddy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ust.buddy.entity.Associate;
import com.ust.buddy.entity.Buddy;
import com.ust.buddy.service.AssociateService;
import com.ust.buddy.service.BuddyService;

@RestController
@RequestMapping(value = "/CRUD")
@CrossOrigin(value = "*")
public class CrudController {

	@Autowired
	AssociateService associateService;

	@Autowired
	BuddyService buddyService;

//	@PostMapping(value = "/getBuddyDetails")
//	public String buddyDetails() {
//		return "working";
//	}

//	@GetMapping(value = "/getBuddyDetails")
//	public String buddyDetails() {
//		return "working";
//	}
//	
	
	Logger logger= LoggerFactory.getLogger(CrudController.class);
	
	@PostMapping(value = "/createAssociate")
	public @ResponseBody String saveAssociate(@RequestBody Associate associate) {
		try {
			associateService.saveAssociate(associate);
			return "success";


		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
			
		}
	}

	@PostMapping("/saveAssociates")
	public @ResponseBody String saveAssociates(@RequestBody List<Associate> associate) {
		try {
			associateService.saveAssociates(associate);
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}


	@GetMapping("/getAllAssociates")
	public List<Associate> getAllAssociates() {
		try {
			return associateService.getAllAssociates();
		}catch(Exception e) {
			return null;
		}

	}

	@GetMapping("/findAssociatesById/{enterAssociateId}")
	public Associate findAssociatesById(@PathVariable("enterAssociateId") String associate_id) {
		logger.error("error");
		try {
			 associateService.findAssociatesById(associate_id);
		}catch(Exception e) {
			System.out.println("associate not found");
		}
		return  null;

	}

	@PutMapping("/updateAssociates/{associate_id}")
	public ResponseEntity<String> updateAssociate(@PathVariable String associate_id,
			@RequestBody Associate updatedAssociate) {
		try {
			Associate existingAssociate = associateService.findAssociatesById(associate_id);
			if (existingAssociate == null) {
				return new ResponseEntity<>("Associate not found", HttpStatus.NOT_FOUND);
			}

			existingAssociate.setFirst_name(updatedAssociate.getFirst_name());
			existingAssociate.setLast_name(updatedAssociate.getLast_name());
			existingAssociate.setStatus(updatedAssociate.getStatus());
			existingAssociate.setEmail_id(updatedAssociate.getEmail_id());
			existingAssociate.setPhoneno(updatedAssociate.getPhoneno());
			existingAssociate.setPrev_account_name(updatedAssociate.getPrev_account_name());
			associateService.updateAssociate(existingAssociate);

			return new ResponseEntity<String>("Associate updated successfully", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("failure", HttpStatus.BAD_REQUEST );

		} 
		

	}

	@DeleteMapping("/deleteAssociateById/{associate_id}")
	public ResponseEntity<String> deleteAssociateById(@PathVariable("associate_id") String associate_id) {
		try {
			boolean deleteAssociateById = associateService.deleteAssociateById(associate_id);
			if (deleteAssociateById) {
				return new ResponseEntity<>("Associate deleted", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Associate not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
		}

	}
	
	

	@PostMapping(value = "/createBuddy")
	public @ResponseBody String createBuddy(@RequestBody Buddy buddy) {
		try {
			buddyService.createBuddy(buddy);
			return "success";

		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}

	}
	/*
	@GetMapping("/findBuddyById/{enterBuddyId}")
	public Buddy findBuddyById(@PathVariable("enterBuddyId") String buddy_id) {
		try {
			buddyService.findBuddyById(buddy_id);
		}catch(Exception e) {
			System.out.println("buddy not found");
		}
		return  null;

	}
	*/
	
	@GetMapping("/findBuddyById/{buddyId}")
	public Buddy findBuddyById(@PathVariable("buddyId") String buddyId) {
	    Buddy buddy = buddyService.findBuddyById(buddyId);
	    if (buddy == null) {
	        buddy = new Buddy();
	        buddy.setBuddy_id(buddyId);
	    }
	    return buddy;
	}
	
	@GetMapping("/getAllBuddy")
	public List<Buddy> getAllBuddy() {
		try {
			return buddyService.getAllBuddy();
		}catch(Exception e) {
			return null;
		}

	}
	
	@PutMapping("/updateBuddy/{buddy_id}")
	public ResponseEntity<String> updateBuddy(@PathVariable String buddy_id,
			@RequestBody Buddy updatedBuddy) {
		try {
			 Buddy existingBuddy = buddyService.findBuddyById(buddy_id);
			if (existingBuddy == null) {
				return new ResponseEntity<>("Buddy not found", HttpStatus.NOT_FOUND);
			}

			existingBuddy.setAccount_name(updatedBuddy.getAccount_name());
			existingBuddy.setAssociate(updatedBuddy.getAssociate());
			existingBuddy.setBuddy_name(updatedBuddy.getBuddy_name());
			existingBuddy.setEmail(updatedBuddy.getEmail());
			existingBuddy.setNo_associates_assigned(updatedBuddy.getNo_associates_assigned());
			existingBuddy.setPhno(updatedBuddy.getPhno());
			buddyService.updateBuddy(existingBuddy);

			return new ResponseEntity<String>("Buddy updated successfully", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("failure", HttpStatus.BAD_REQUEST );

		}
		
	}
	
	@DeleteMapping("/deleteBuddyById/{buddy_id}")
	public ResponseEntity<String> deleteBuddyById(@PathVariable("buddy_id") String buddy_id) {
		try {
			 boolean deleteBuddyById = buddyService.deleteBuddyById(buddy_id);
			if (deleteBuddyById) {
				return new ResponseEntity<>("Buddy deleted", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Buddy not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
		}

	}
	
	
	@PostMapping(value = "/updateBuddyWithAssociates/{buddy_id}")
	public @ResponseBody String updateBuddyWithAssociates(@PathVariable("buddy_id") String buddy_id,
			@RequestBody List<String> associate_ids) {
		try {
			 buddyService.updateBuddyWithAssociate(buddy_id, associate_ids);
			 return "Success";
		}catch(Exception e) {
			
			return "failure";
		}
	}

}

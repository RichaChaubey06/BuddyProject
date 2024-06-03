  package com.ust.buddy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ust.buddy.entity.Buddy;

@Repository
public interface BuddyRepository extends JpaRepository<Buddy, String> {
	
//	findBYBuddyId(String buddy_id)
	 @Query(value= "SELECT * FROM buddy_table a WHERE a.buddy_id = :buddy_id", nativeQuery=true)
	public Optional<Buddy> findByBuddy_id(String buddy_id);
	 
	 

}

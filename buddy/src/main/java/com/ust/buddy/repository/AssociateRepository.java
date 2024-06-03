package com.ust.buddy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ust.buddy.entity.Associate;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, String> {

	  @Query(value= "SELECT * FROM tp_associate a WHERE a.associate_id = :associate_id", nativeQuery=true)
	    Optional<Associate> findByAssociateId(String associate_id);
	  
//	  @Query(value="DELETE FROM tp_associate a WHERE a.associate_id = :associate_id", nativeQuery=true )
//	    void deleteByAssociateId(String associate_id);
}

package com.pratiti.myBank.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pratiti.myBank.Entity.Executive;

public interface ExecutiveRepo extends JpaRepository<Executive, Integer> {

	@Query("SELECT e FROM Executive e WHERE e.available=1")
	List<Executive> findAll();
	

}

package com.pratiti.myBank.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pratiti.myBank.Entity.Counter;

public interface CounterRepo extends JpaRepository<Counter, Integer> {

	boolean existsByCounterNo(int counterNo);

	Optional<Counter> findByCounterNo(int counterNumber);
	
	@Query("SELECT c FROM Counter c WHERE c.available=1")
	List<Counter> findAll();

	
	

}

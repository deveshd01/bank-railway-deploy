package com.pratiti.myBank.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pratiti.myBank.Entity.Counter;
import com.pratiti.myBank.Entity.Token;

public interface TokenRepo extends JpaRepository<Token, Integer> {

//	@Query("SELECT c FROM Counter c JOIN c.counter_service cs JOIN cs.service s WHERE b.s_id = :serviceId")
//	 c.counterOpen=1 AND
	@Query("SELECT c FROM Counter c JOIN c.service s WHERE c.counterOpen=0 AND s.s_id = :serviceId")
	List<Counter> findCounterByServiceId(@Param("serviceId") int serviceId);

	@Query("SELECT t FROM Token t WHERE t.status='PENDING' OR t.status='NOSHOW' ")
	List<Token> findAllActiveTokens();
	

	

}

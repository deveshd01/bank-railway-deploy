package com.pratiti.myBank.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.pratiti.myBank.Entity.BankService;
public interface ServiceRepo extends JpaRepository<BankService, Integer> {

	boolean existsByServiceName(String serviceName);

	Optional<BankService> findByServiceName(String serviceName);
	
	@Query("SELECT s FROM BankService s WHERE s.available=1")
	List<BankService> findAll();
	

}

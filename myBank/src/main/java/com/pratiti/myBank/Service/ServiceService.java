package com.pratiti.myBank.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratiti.myBank.Entity.BankService;
import com.pratiti.myBank.Exception.MyException;
import com.pratiti.myBank.Repository.ServiceRepo;

@Service
public class ServiceService {
	
	@Autowired
	private ServiceRepo serviceRepo;
	
	public int addService(BankService service) {
		if (!serviceRepo.existsByServiceName(service.getServiceName())) {
			serviceRepo.save(service);
			return service.getS_id();
		} 
			throw new MyException("Service Already Exist");
	}

	public List<BankService> findAll() {
		List<BankService> services = new ArrayList<>();
		services = serviceRepo.findAll();
		return services;
	}

	public void removeService(int id) {
		try {
			BankService s= serviceRepo.findById(id).get();
			s.setAvailable(0);
			serviceRepo.save(s);
			
		} catch (Exception e) {
			throw new MyException("Wrong Service Id...!!!!");			
		}
		
		
	}
	
	
}

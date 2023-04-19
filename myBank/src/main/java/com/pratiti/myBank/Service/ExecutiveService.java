package com.pratiti.myBank.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratiti.myBank.Entity.Counter;
import com.pratiti.myBank.Entity.Executive;
import com.pratiti.myBank.Exception.MyException;
import com.pratiti.myBank.Model.LoginExecutive;
import com.pratiti.myBank.Repository.CounterRepo;
import com.pratiti.myBank.Repository.ExecutiveRepo;

@Service
public class ExecutiveService {
	@Autowired
	private ExecutiveRepo executiveRepo;

	@Autowired
	private CounterRepo counterRepo;
	
//	@Autowired
//	private ServiceService serviceService;

//	addExecutive
//	public int addExecutive(Executive executive, Set<BankService> services) {
//		Set<BankService> serviceSet = new HashSet<>();
//		for(BankService s : services) {
//			try {
//				serviceService.addService(s);
//				serviceSet.add(s);
//			} catch (Exception e) {	
//				// If Service Already present then Fetch that Service & add it to serviceSet
//				BankService s2 = serviceRepo.findByServiceName(s.getServiceName()).get();
//				serviceSet.add(s2);
//			}
//		}
//		executive.setService(serviceSet);
//		executiveRepo.save(executive);
//		return executive.getE_id();
//	}

	public int addExecutive(Executive executive) {

		executiveRepo.save(executive);
		return executive.getE_id();
	}

//	Remove Executive ****************************************
	public void removeExecutive(int id) {
		try {
			Executive e = executiveRepo.findById(id).get();
			e.setAvailable(0);
			executiveRepo.save(e);
		} catch (Exception e) {
			throw new MyException("Wrong Executive Id...!!!!"); 
		}
	}

	public int loginExecutive(LoginExecutive executiveModel) {
		if(!executiveRepo.existsById(executiveModel.getId()) || !counterRepo.existsByCounterNo(executiveModel.getCounterNumber())   ){
			throw new MyException("!!!!!!!!!.......Id Not Matched...!!!!!!!");
		}
		Executive executive = executiveRepo.findById(executiveModel.getId()).get();
		Counter counter = counterRepo.findByCounterNo(executiveModel.getCounterNumber()).get(); 
		
		if(counter.getCounterOpen()!=0) {throw new MyException("!!!!!!!!!.......Counter Already Open...!!!!!!!");}
		if (executive.getPassword().equals(executiveModel.getePassword())  && counter.getPassword().equals(executiveModel.getcPassword())){
			counter.setCounterOpen(1);
			counterRepo.save(counter);
			return counter.getC_id();
		}
		throw new MyException("!!!!!!!!!.......Id Password Not Matched...!!!!!!!");
		
	}
	
	public List<Executive> findAll() {
		List<Executive> executives = new ArrayList<>();
		executives = executiveRepo.findAll();
		return executives;
	}

}

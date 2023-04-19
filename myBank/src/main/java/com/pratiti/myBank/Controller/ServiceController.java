package com.pratiti.myBank.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.myBank.Entity.BankService;
import com.pratiti.myBank.Exception.MyException;
import com.pratiti.myBank.Model.RequestStatus;
import com.pratiti.myBank.Service.ServiceService;



@RestController
@CrossOrigin
public class ServiceController {
	@Autowired
	private ServiceService serviceService;
	
	@GetMapping("/addService")
	public RequestStatus addService(@RequestParam String serviceName, int avgTime) {
		BankService service = new BankService();
		service.setServiceName(serviceName);
		service.setAvgTime(avgTime);
		
		RequestStatus status = new RequestStatus();
		try {
			int cId = serviceService.addService(service);
			status.setStatus(true);
			status.setMessage("Service Added......!!!!!!  -->  @ serviceID : "+cId);
			status.setId(cId);
		}
		catch(MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		
		return status;
	}
	
	@GetMapping("/showAllServices")
	public List<BankService> findAll(){
		return serviceService.findAll();
	}
	
	@GetMapping("/removeService")
	public RequestStatus removeService(@RequestParam int id) {
		RequestStatus status = new RequestStatus();
		try {
			serviceService.removeService(id);
			status.setStatus(true);
			status.setMessage("Service Removed Successfully......!!!!!!");
			status.setId(id);
		}
		catch(MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}

}

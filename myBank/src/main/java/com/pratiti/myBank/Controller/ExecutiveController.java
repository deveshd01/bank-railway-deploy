package com.pratiti.myBank.Controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.pratiti.myBank.Entity.Executive;
import com.pratiti.myBank.Exception.MyException;
import com.pratiti.myBank.Model.LoginExecutive;
import com.pratiti.myBank.Model.RequestStatus;
import com.pratiti.myBank.Service.ExecutiveService;


@RestController
@CrossOrigin
public class ExecutiveController {
	@Autowired
	private ExecutiveService executiveService;


	
	@PostMapping("/addExecutive")
	public RequestStatus addExecutive(@RequestBody Executive executive) {

		RequestStatus status = new RequestStatus();
		try {
			int cId = executiveService.addExecutive(executive);
			status.setStatus(true);
			status.setMessage("Executive Added......!!!!!!");
			status.setId(cId);
		}
		catch(MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}
	
	@PostMapping("/loginExecutive")
	public RequestStatus loginExecutive(@RequestBody LoginExecutive executiveModel) {

		RequestStatus status = new RequestStatus();
		try {
			int cid = executiveService.loginExecutive(executiveModel);
			status.setStatus(true);
			status.setMessage("Executive Login Successful......!!!!!!");
			status.setId(cid);
		}
		catch(MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}
	
	@GetMapping("/showAllExecutives")
	public List<Executive> findAll(){
		return executiveService.findAll();
	}
	
	
	@GetMapping("/removeExecutive")
	public RequestStatus removeExecutive(@RequestParam int id) {
		RequestStatus status = new RequestStatus();
		try {
			executiveService.removeExecutive(id);
			status.setStatus(true);
			status.setMessage("Executive Removed Successfully......!!!!!!");
			status.setId(id);
		}
		catch(MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}
	

}

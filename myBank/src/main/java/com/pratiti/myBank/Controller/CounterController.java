package com.pratiti.myBank.Controller;

import java.util.List;

import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pratiti.myBank.Entity.Counter;
import com.pratiti.myBank.Exception.MyException;
import com.pratiti.myBank.Model.CounterModel;
import com.pratiti.myBank.Model.RequestStatus;
import com.pratiti.myBank.Service.CounterService;

@RestController
@CrossOrigin
public class CounterController {
	@Autowired
	private CounterService counterService;

	@PostMapping("/addCounter")
	public RequestStatus addCounter(@RequestBody CounterModel counterModel) {
		Counter counter = new Counter();
		BeanUtils.copyProperties(counterModel, counter);

		Set<Integer> serviceIds = counterModel.getServiceIds();

		RequestStatus status = new RequestStatus();
		try {
			int cId = counterService.addCounter(counter, serviceIds);
			status.setStatus(true);
			status.setMessage("Counter Added......!!!!!!");
			status.setId(cId);
		} catch (MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}

	@GetMapping("/addTokenAgain")
	public RequestStatus addTokenAgain(@RequestParam int tokenId, int counterId) {

		RequestStatus status = new RequestStatus();
		try {
			counterService.addTokenAgain(tokenId, counterId);
			status.setStatus(true);
			status.setMessage("Token Added in Queue.... with CounterId : " + counterId + "  & tokenId " + tokenId + "......!!!!!! ");
		} catch (MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}

	@PostMapping("/updateCounterService")
	public RequestStatus updateCounterService(@RequestBody CounterModel counterModel, @RequestParam int operation) {
		Counter counter = new Counter();
		counter.setC_id(counterModel.getCounterId());
		counter.setPassword(counterModel.getPassword());
		
		Set<Integer> serviceIds = counterModel.getServiceIds();
	
		
		RequestStatus status = new RequestStatus();
		try {
			counterService.updateCounterService(counter, operation, serviceIds);
			status.setStatus(true);
			status.setMessage("Services updated...!!!");
		} catch (MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}

	@GetMapping("/counterClose")
	public RequestStatus counterClose(@RequestParam int counterId) {
		RequestStatus status = new RequestStatus();
		try {
			counterService.counterClose(counterId);
			status.setStatus(true);
			status.setMessage("Addinging new peoples on This Counter is STOPPED...!!! ");
		} catch (MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}

	@GetMapping("/emptyAllCounter")
	public RequestStatus emptyAllCounter() {
		RequestStatus status = new RequestStatus();
		try {
			counterService.emptyAllCounter();
			status.setStatus(true);
			status.setMessage("All counters Queues Closed Good Night...!!! ");
		} catch (MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}

	@GetMapping("/showAllCounters")
	public List<Counter> findAll() {
		return counterService.findAll();
	}

	@GetMapping("/showCounter")
	public CounterModel findCounter(@RequestParam int id) {
		return counterService.findCounter(id);
	}

	@GetMapping("/removeCounter")
	public RequestStatus removeCounter(@RequestParam int id) {
		RequestStatus status = new RequestStatus();
		try {
			counterService.removeCounter(id);
			status.setStatus(true);
			status.setMessage("Counter Removed Successfully......!!!!!!");
			status.setId(id);
		} catch (MyException e) {
			status.setStatus(false);
			status.setMessage(e.getMessage());
		}
		return status;
	}

}

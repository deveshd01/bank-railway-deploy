package com.pratiti.myBank.Service;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pratiti.myBank.Entity.BankService;
import com.pratiti.myBank.Entity.Counter;
import com.pratiti.myBank.Entity.Token;
import com.pratiti.myBank.Entity.Token.Status;
import com.pratiti.myBank.Exception.MyException;
import com.pratiti.myBank.Model.CounterModel;
import com.pratiti.myBank.Repository.CounterRepo;
import com.pratiti.myBank.Repository.ServiceRepo;
import com.pratiti.myBank.Repository.TokenRepo;

@Service
public class CounterService {
	@Autowired
	private CounterRepo counterRepo;

	@Autowired
	private ServiceRepo serviceRepo;

	@Autowired
	private TokenRepo tokenRepo;

//	addCounter**********************************************************************************
	public int addCounter(Counter counter, Set<Integer> serviceIds) {

		if (!counterRepo.existsByCounterNo(counter.getCounterNo())) {
			Set<BankService> serviceSet = new HashSet<>();
			for (Integer i : serviceIds) {
				try {
					BankService s = serviceRepo.findById(i).get();
					serviceSet.add(s);
				} catch (Exception e) {
				}
			}
			counter.setService(serviceSet);
			counterRepo.save(counter);
			return counter.getC_id();
		} 
			throw new MyException("Counter Already Exist");
	}

	public void addTokenAgain(int tokenId, int counterId) {
		Counter counter = counterRepo.findById(counterId).get();
		Token token = tokenRepo.findById(tokenId).get();

		int temp = token.getTokenCalled();
		if (temp < 3) {
			token.setTokenCalled(temp + 1);
			token.setStatus(Token.Status.NOSHOW);

//			List<Token> tokenList = counter.getTokenList();
//			tokenList.add(token);
//			counter.setTokenList(tokenList);
			
			token.setCounter(counter);
			tokenRepo.save(token);
			
//			counterRepo.save(counter);
		} else {
			token.setStatus(Token.Status.ABSENT);
			tokenRepo.save(token);
			throw new MyException("Token Recall Limit Exceeded");
		}
		
		
	}

	public void updateCounterService(Counter userCounter, int operation, Set<Integer> newServiceIds) {
		String password = userCounter.getPassword();		
		int counterId = userCounter.getC_id();
		Counter counter = counterRepo.findById(counterId).get();
		
		if(counter.getPassword().equals(password)) {
			Set<BankService> currentServices = counter.getService();
			for(int id:newServiceIds) {
				BankService s = serviceRepo.findById(id).get();
				if(operation==1)
					currentServices.add(s);
				else 
					try {
						currentServices.remove(s);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			counter.setService(currentServices);
			counterRepo.save(counter);
			return;
		}
		 throw new MyException("Wrong Counter ID Password...!!!!");
		
	}

	public void counterClose(int counterId) {
		Counter counter = counterRepo.findById(counterId).get();
		counter.setCounterOpen(0);
		counterRepo.save(counter);
	}

	public void emptyAllCounter() {
		List<Counter> counterList = counterRepo.findAll();		
		for(Counter counter:counterList) {
			List<Token> tokenList = counter.getTokenList();
			for(Token t:tokenList) {
				t.setCounter(null);
				t.setCounterNumber(0);
				t.setStatus(Status.EXPIRED);
				tokenRepo.save(t);
			}
		}		
	}
	
	public List<Counter> findAll() {
		List<Counter> counters = new ArrayList<>();
		counters = counterRepo.findAll();
		return counters;
	}

	public void removeCounter(int id) {
		 try {
			 Counter c = counterRepo.findById(id).get();	
			 c.setAvailable(0);
			 counterRepo.save(c);
			
		} catch (Exception e) {
			throw new MyException("Wrong Counter Id...!!!!");
		}
	}

	public CounterModel findCounter(int id) {
		Counter counter = counterRepo.findByCounterNo(id).get();
		CounterModel counterModel = new CounterModel();
		counterModel.setCounterId(counter.getC_id());
		counterModel.setCounterNo(counter.getCounterNo());	
//		List<BankService> serviceList = new ArrayList<BankService>(counter.getService());
		counterModel.setServices(counter.getService());
		
		return counterModel;
	}
	
	
	
}

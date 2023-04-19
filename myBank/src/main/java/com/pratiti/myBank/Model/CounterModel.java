package com.pratiti.myBank.Model;

import java.util.Set;
import com.pratiti.myBank.Entity.BankService;

public class CounterModel {
	private int counterId;
	private int counterNo;
	private Set<Integer> serviceIds;
	private String password;
	private Set<BankService> Services;
	
	
	public Set<BankService> getServices() {
		return Services;
	}
	public void setServices(Set<BankService> services) {
		Services = services;
	}
	public int getCounterId() {
		return counterId;
	}
	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCounterNo() {
		return counterNo;
	}
	public void setCounterNo(int counterNo) {
		this.counterNo = counterNo;
	}
	public Set<Integer> getServiceIds() {
		return serviceIds;
	}
	public void setServiceIds(Set<Integer> serviceIds) {
		this.serviceIds = serviceIds;
	}
	
	

}

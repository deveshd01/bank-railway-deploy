package com.pratiti.myBank.Entity;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table
public class Counter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int c_id;
	private int counterNo;
	private int counterOpen=0;
	private String password;
	private int queueTotalTime;
	private int available=1;

	
	@JsonIgnore
	@OneToMany(mappedBy = "counter", cascade = CascadeType.ALL)
	private List<Token> tokenList;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="counter_service", joinColumns = @JoinColumn(name="c_id"),inverseJoinColumns = @JoinColumn(name="s_id"))
	private Set<BankService> service;
	
	
	
	public void updateQueueTotalTime(BankService service) {
		this.queueTotalTime += service.getAvgTime();
	}
	
//	 GETTERS & SETTERS
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}

	
	public int getQueueTotalTime() {
		return queueTotalTime;
	}
	public void setQueueTotalTime(int queueTotalTime) {
		this.queueTotalTime = queueTotalTime;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCounterOpen() {
		return counterOpen;
	}
	public void setCounterOpen(int counterOpen) {
		this.counterOpen = counterOpen;
	}

	public List<Token> getTokenList() {
		return tokenList;
	}
	
	public void setTokenList(List<Token> tokenList) {
		this.tokenList = tokenList;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public Set<BankService> getService() {
		return service;
	}
	public void setService(Set<BankService> service) {
		this.service = service;
	}
	public int getCounterNo() {
		return counterNo;
	}
	public void setCounterNo(int counterNo) {
		this.counterNo = counterNo;
	}
}

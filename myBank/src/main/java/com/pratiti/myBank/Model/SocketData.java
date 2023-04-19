package com.pratiti.myBank.Model;

public class SocketData {
	private String tokenNo; 
	private String counterNo;
	
	
	
	// Constructor Getters-Setters
	public SocketData() {
		super();
	}
	public SocketData(String tokenNo, String counterNo) {
		super();
		this.tokenNo = tokenNo;
		this.counterNo = counterNo;
	}
	public String getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	public String getCounterNo() {
		return counterNo;
	}
	public void setCounterNo(String counterNo) {
		this.counterNo = counterNo;
	} 

}

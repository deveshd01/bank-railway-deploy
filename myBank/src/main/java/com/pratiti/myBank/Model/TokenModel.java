package com.pratiti.myBank.Model;


public class TokenModel {
	private int tokenId;
	private int tokenNo;
	private int counterNo;
	private String serviceName;
	private String timeGenerated;
	private String expectedTime;
	
	
	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}


	public String getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}

	public String getTimeGenerated() {
		return timeGenerated;
	}

	public void setTimeGenerated(String timeGenerated) {
		this.timeGenerated = timeGenerated;
	}

	private Status status;
	
	public static enum Status{
		PENDING,  NOSHOW, SERVICED; 
	}
	

	public int getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(int tokenNo) {
		this.tokenNo = tokenNo;
	}

	public int getCounterNo() {
		return counterNo;
	}

	public void setCounterNo(int counterNo) {
		this.counterNo = counterNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	

	

}

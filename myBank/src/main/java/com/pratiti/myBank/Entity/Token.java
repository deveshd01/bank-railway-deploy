package com.pratiti.myBank.Entity;

import java.util.Objects;


import javax.persistence.Entity;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table
public class Token implements Comparable<Token>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int tokenNo;
	private int tokenCalled;
	private int counterNumber;
	
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "service_id")
	private BankService service;
	

	@ManyToOne
	@JoinColumn(name = "counter_id")
	private Counter counter;
	
	
	public static enum Status{
		PENDING,  NOSHOW, SERVICED, ABSENT, EXPIRED; 			// EXPIRED = Day over 
	}
	
	
	
	
	
	
	
	@Override
	public int compareTo(Token token) {
		if(tokenCalled < token.tokenCalled)
			return 1;
		else if(tokenCalled > token.tokenCalled)
			return -1;
		if(tokenNo < token.tokenNo)
			return -1;
		else if(tokenNo > token.tokenNo)
			return 1;
		return 0;
	}

	
@Override
	public int hashCode() {
		return Objects.hash(tokenCalled, tokenNo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Token other = (Token) obj;
		return tokenCalled == other.tokenCalled && tokenNo == other.tokenNo;
	}







	//	GETTER  &  SETTER
	public Counter getCounter() {
		return counter;
	}


	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	
	public int getTokenCalled() {
		return tokenCalled;
	}

	public void setTokenCalled(int tokenCalled) {
		this.tokenCalled = tokenCalled;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(int tokenNo) {
		this.tokenNo = tokenNo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public BankService getService() {
		return service;
	}

	public void setService(BankService service) {
		this.service = service;
	}

	public int getCounterNumber() {
		return counterNumber;
	}

	public void setCounterNumber(int counterNumber) {
		this.counterNumber = counterNumber;
	}

}

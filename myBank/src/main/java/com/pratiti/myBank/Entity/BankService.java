package com.pratiti.myBank.Entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class BankService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int s_id;
	private String serviceName;
	private int avgTime;
	private int available = 1;

	@JsonIgnore
	@OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
	private List<Token> token;

	@JsonIgnore
	@ManyToMany(mappedBy = "service")
	private Set<Counter> counter;



	// GETTERS & SETTERS
	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(int avgTime) {
		this.avgTime = avgTime;
	}

	public int getS_id() {
		return s_id;
	}

	public void setS_id(int s_id) {
		this.s_id = s_id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Set<Counter> getCounter() {
		return counter;
	}

	public void setCounter(Set<Counter> counter) {
		this.counter = counter;
	}

	public List<Token> getToken() {
		return token;
	}

	public void setToken(List<Token> token) {
		this.token = token;
	}
}

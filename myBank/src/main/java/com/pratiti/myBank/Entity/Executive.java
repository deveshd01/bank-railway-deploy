package com.pratiti.myBank.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Executive {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int E_id;
	private String name;
	private String email;
	private String mob;
	private String password;
	private int available=1;


	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}

	public String getMob() {
		return mob;
	}
	
	public void setMob(String mob) {
		this.mob = mob;
	}
	public int getE_id() {
		return E_id;
	}

	public void setE_id(int e_id) {
		E_id = e_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

package com.pratiti.myBank.Model;

public class LoginExecutive {
	private int id;
	private String mail;
	private String ePassword;
	private int counterNumber;
	private String cPassword;
	
	
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getePassword() {
		return ePassword;
	}
	public void setePassword(String ePassword) {
		this.ePassword = ePassword;
	}

	public int getCounterNumber() {
		return counterNumber;
	}
	public void setCounterNumber(int counterNumber) {
		this.counterNumber = counterNumber;
	}
	public String getcPassword() {
		return cPassword;
	}
	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}

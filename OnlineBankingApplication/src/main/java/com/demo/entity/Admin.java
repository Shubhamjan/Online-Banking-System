package com.demo.entity;

public class Admin {
	private String acctUsername;
	
	private String accEmail;
	
	private String passsword;

	public Admin() {
		super();
	}

	public Admin(String acctUsername, String accEmail, String passsword) {
		super();
		this.acctUsername = acctUsername;
		this.accEmail = accEmail;
		this.passsword = passsword;
	}

	public String getAcctUsername() {
		return acctUsername;
	}

	public void setAcctUsername(String acctUsername) {
		this.acctUsername = acctUsername;
	}

	public String getAccEmail() {
		return accEmail;
	}

	public void setAccEmail(String accEmail) {
		this.accEmail = accEmail;
	}

	public String getPasssword() {
		return passsword;
	}

	public void setPasssword(String passsword) {
		this.passsword = passsword;
	}

	@Override
	public String toString() {
		return "Accountant [acctUsername=" + acctUsername + ", accEmail=" + accEmail + ", passsword=" + passsword + "]";
	}
	
}

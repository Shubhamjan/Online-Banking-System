package com.demo.entity;

public class Customer {
	
	private int cust_accNumber;
	
	private String cust_name;
	
	private String cust_pass;
	
	private String cust_email;
	
	private String cust_number;
	
	private String cust_address;
	
	private double cust_balance;

	public Customer() {
		super();
	}

	public Customer(int cust_accNumber, String cust_name, String cust_pass, String cust_email, String cust_number,
			String cust_address, double cust_balance) {
		super();
		this.cust_accNumber = cust_accNumber;
		this.cust_name = cust_name;
		this.cust_pass = cust_pass;
		this.cust_email = cust_email;
		this.cust_number = cust_number;
		this.cust_address = cust_address;
		this.cust_balance = cust_balance;
	}

	public int getCust_accNumber() {
		return cust_accNumber;
	}

	public void setCust_accNumber(int cust_accNumber) {
		this.cust_accNumber = cust_accNumber;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_pass() {
		return cust_pass;
	}

	public void setCust_pass(String cust_pass) {
		this.cust_pass = cust_pass;
	}

	public String getCust_email() {
		return cust_email;
	}

	public void setCust_email(String cust_email) {
		this.cust_email = cust_email;
	}

	public String getCust_number() {
		return cust_number;
	}

	public void setCust_number(String cust_number) {
		this.cust_number = cust_number;
	}

	public String getCust_address() {
		return cust_address;
	}

	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}

	public double getCust_balance() {
		return cust_balance;
	}

	public void setCust_balance(double cust_balance) {
		this.cust_balance = cust_balance;
	}

	@Override
	public String toString() {
		return "Customer [cust_accNumber=" + cust_accNumber + ", cust_name=" + cust_name + ", cust_pass=" + cust_pass
				+ ", cust_email=" + cust_email + ", cust_number=" + cust_number + ", cust_address=" + cust_address
				+ ", cust_balance=" + cust_balance + "]";
	}
	
	
}

package com.demo.dao;

import com.demo.entity.Customer;
import com.demo.exception.CustomerException;

public interface CustomerService {
	
	public Customer login(String userName,String password,int accountNumber) throws CustomerException;
	
	public double viewBalance(int accountNumber)throws CustomerException;
	
	public double Deposit(int accountNumber,double amount) throws CustomerException;
	
	public double withDraw(int accountNumber,double amount) throws CustomerException;
	
	public void transferMoney(int accountNumber,double amount,int accountNumber2) throws CustomerException;
}

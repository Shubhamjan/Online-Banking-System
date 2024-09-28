package com.demo.dao;

import com.demo.entity.Admin;
import com.demo.entity.Customer;
import com.demo.exception.AdminException;
import com.demo.exception.CustomerException;

public interface AdminService {
	public Admin Login(String accUsername,String accPassword) throws AdminException;
	
	public int addCustomer(String cust_name, String cust_email, String cust_pass, String cust_number,String cust_address) throws CustomerException;
	
	public String addAccount(double customerBalance,int cid) throws CustomerException;
	
	public String updateCustomer(int custAccountNumber,String custAddress) throws CustomerException;
	
	public String deleteAccount(int custAccountNumber)throws CustomerException;
	
	public Customer viewCustomer(int custAccountNumer)throws CustomerException;
	
	public Customer viewAllCustomer()throws CustomerException;
	
}

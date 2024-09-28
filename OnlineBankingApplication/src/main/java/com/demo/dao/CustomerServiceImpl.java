package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.databaseconnection.DatabaseConnection;
import com.demo.entity.Customer;
import com.demo.exception.CustomerException;

public class CustomerServiceImpl implements CustomerService{

	@Override
	public Customer login(String userName, String password, int accountNumber) throws CustomerException {
//		System.out.println(userName+" "+password);
		
		Customer cu=null;
		try {
			Connection co=DatabaseConnection.provideConnection();
			PreparedStatement ps=co.prepareStatement("select * from customer c inner join account a on c.cid=a.cid where c.custName=? and c.custPass=? and a.cust_accNumber=?");
			ps.setString(1, userName);
			ps.setString(2, password);
			ps.setInt(3, accountNumber);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				int ac=rs.getInt("cust_accNumber");
				String n=rs.getString("custName");
				double b=rs.getDouble("cust_balance");
				String e=rs.getString("custEmail");
				String p=rs.getString("custPass");
				String m=rs.getString("custMobile");
				String ad=rs.getString("custAddress");
//				System.out.println(ad);
				cu=new Customer(ac,n,p,e,m,ad,b);
	
				
			}else {
				throw new CustomerException("Invalid customer and Password!!!!!");
			}
		}catch(SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		return cu;
	}

	@Override
	public double viewBalance(int accountNumber) throws CustomerException {
		
		double bal=-1;
		
		try {
			Connection con=DatabaseConnection.provideConnection();
			PreparedStatement ps=con.prepareStatement("select cust_balance from account where cust_accNumber=?");
			ps.setInt(1, accountNumber);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				bal=rs.getDouble("cust_balance");
			}
			
		}catch(SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		return bal;
	}

	@Override
	public double Deposit(int accountNumber, double amount) throws CustomerException {
		
		double am=-1;
		
		try {
			Connection con=DatabaseConnection.provideConnection();
			PreparedStatement ps=con.prepareStatement("update account set cust_balance=cust_balance+ ? where cust_accNumber=?");
			ps.setDouble(1, amount);
			ps.setInt(2, accountNumber);
			int rs=ps.executeUpdate();
			PreparedStatement ps2=con.prepareStatement("select cust_balance from account where cust_accNumber=?");
			ps2.setInt(1, accountNumber);
			ResultSet r=ps2.executeQuery();
			if(r.next()) {
				am=r.getDouble("cust_balance");
			}
			
		}catch(SQLException e) {
			throw new CustomerException(e.getMessage());
		}
		return am;
	}

	public double withDraw(int accountNumber, double amount) throws CustomerException {
		 
		double cb= viewBalance(accountNumber);
		if(cb>amount) {
			try {
				Connection co=DatabaseConnection.provideConnection();
				PreparedStatement ps=co.prepareStatement("update account set cust_balance=cust_balance-? where cust_accNumber=?");
				ps.setDouble(1, amount);
				ps.setInt(2, accountNumber);
				int x=ps.executeUpdate();
				
				
			}catch(SQLException e) {
				throw new CustomerException(e.getMessage());
			}
		}else {
			throw new CustomerException("Warning : Insufficient Balance");
		}
		return amount;
	}

	public void transferMoney(int accountNumber, double amount, int accountNumber2) throws CustomerException {
		
		double vb=viewBalance(accountNumber);
		if(vb>amount && checkAccount(accountNumber2)) {
			double wid=withDraw(accountNumber,amount);
			double dep=Deposit(accountNumber2,wid);
		}else {
			throw new CustomerException("Insufficient Balance or Account doesn't exist");
		}
		
	}
	
	public boolean checkAccount(int accountNumer2) {
		
		try {
			
			Connection c=DatabaseConnection.provideConnection();
			PreparedStatement ps=c.prepareStatement("Select * from account where cust_accNumber=?");
			ps.setInt(1, accountNumer2);
			ResultSet r=ps.executeQuery();
			if(r.next()) {
				return true;
			}
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}

package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.databaseconnection.DatabaseConnection;
import com.demo.entity.Admin;
import com.demo.entity.Customer;
import com.demo.exception.AdminException;
import com.demo.exception.CustomerException;

public class AdminServiceImpl implements AdminService {

	@Override
	public Admin Login(String accUsername, String accPassword) throws AdminException {
		// TODO Auto-generated method stub
		Admin acc = null;

		try {
			Connection conn = DatabaseConnection.provideConnection();
			PreparedStatement ps = conn.prepareStatement("select * from admin where accUsername= ? and password=?");
//			System.out.println(accUsername+"  "+accPassword);
			ps.setString(1, accUsername);
			ps.setString(2, accPassword);

			ResultSet re = ps.executeQuery();

			if (re.next()) {
				String u = re.getString("accUsername");
				String e = re.getString("accEmail");
				String p = re.getString("password");
				acc = new Admin(u, e, p);
//				System.out.println(acc);
			}
		} catch (SQLException e) {
			throw new AdminException("Invalid username and password");
		}
		return acc;
	}

	@Override
	public int addCustomer(String cust_name, String cust_email, String cust_pass, String cust_number,
			String cust_address) throws CustomerException {
		int cid=-1;
		try {
			Connection con=DatabaseConnection.provideConnection();
			PreparedStatement ps=con.prepareStatement("insert into customer (custName,custEmail,custPass,custMobile,custAddress) values(?,?,?,?,?)");
			ps.setString(1, cust_name);
			ps.setString(2, cust_email);
			ps.setString(3, cust_pass);
			ps.setString(4, cust_number);
			ps.setString(5, cust_address);
			
			int r=ps.executeUpdate();
//			System.out.println(r);
			if(r>0) {
				System.out.println("\nCustomer added successfully");
			}else {
				System.out.println("Customer not added successfully");
			}
			if(r>0) {
				PreparedStatement ps2=con.prepareStatement("select cid from customer where custEmail=? and custMobile=?");
				ps2.setString(1, cust_email);
				ps2.setString(2, cust_number);
				ResultSet rs=ps2.executeQuery();
				
				if(rs.next()) {
					cid=rs.getInt(1);
//					System.out.println(1);
				}else {
					System.out.println("Inserted data is incorrect: Please Try again:");
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return cid;
	}

	@Override
	public String addAccount(double customerBalance, int cid) throws CustomerException {
		
		String message=null;
		try {
			Connection con=DatabaseConnection.provideConnection();
			PreparedStatement pt=con.prepareStatement("insert into account (cust_balance,cid) values (?,?)");
			pt.setDouble(1, customerBalance);
			pt.setInt(2, cid);
			int x=pt.executeUpdate();
//			System.out.println(customerBalance);
			
//			if(x>0) {
//				System.out.println("Account added successfully ");
//			}else {
//				System.out.println("Inserted data not added successfully");
//			}
		}catch(SQLException e) {
			System.out.println("SQL related exception");
		}
		return message;
	}

	@Override
	public String updateCustomer(int custAccountNumber, String custAddress) throws CustomerException {
		
		String message=null;
		
		try {
			
			Connection con=DatabaseConnection.provideConnection();
			PreparedStatement ps=con.prepareStatement("update customer i inner join account a on i.cid=a.cid and a.cust_accNumber=? set i.custAddress=?");
			ps.setInt(1, custAccountNumber);
			ps.setString(2, custAddress);
			int x=ps.executeUpdate();
			if(x>0) {
				System.out.println("Address Updated successfully.......");
				System.out.println("-------------------------------------------");
			}else {
				System.out.println("Customer Updation is not successfull.....");
				System.out.println("--------*****--------");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public String deleteAccount(int custAccountNumber) throws CustomerException {
		
		String message=null;
		try {
			Connection con=DatabaseConnection.provideConnection();
			PreparedStatement pe=con.prepareStatement("Delete c from customer c inner join account a on c.cid=a.cid where a.cust_accNumber=?");
			pe.setInt(1, custAccountNumber);
			
			int c=pe.executeUpdate();
			if(c>0) {
				System.out.println("Account deleted Successfully");
				System.out.println("---------------------------------------------");
			}else {
				System.out.println("Account not found");
				System.out.println("--------------------------");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		return message;
	}
	
	public Customer viewCustomer(int custAccountNumber) {
		Customer cust=null;
		try {
			Connection con=DatabaseConnection.provideConnection();
			PreparedStatement ps=con.prepareStatement("Select * from customer c inner join Account a on c.cid=a.cid where a.cust_accNumber=?");
			ps.setInt(1, custAccountNumber);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				int cid=rs.getInt("cid");
				String name=rs.getString("custName");
				String email=rs.getString("custEmail");
				String pass=rs.getString("custPass");
				String num=rs.getString("custMobile");
				String add=rs.getString("custAddress");
				int accNum=rs.getInt("cust_accNumber");
				double bal=rs.getDouble("cust_balance");
				
				cust=new Customer(accNum,name,pass,email,num,add,bal);
			}else {
				System.out.println("Invalid account Number");
			}
		}catch(SQLException e) {
			e.getMessage();
		}
		return cust;
	}

	@Override
	public Customer viewAllCustomer() throws CustomerException {
		
		Customer cu=null;
		
		try {
			Connection con=DatabaseConnection.provideConnection();
			PreparedStatement ps=con.prepareStatement("select * from customer c inner join account a on c.cid=a.cid");
			ResultSet rs=ps.executeQuery();
			System.out.println("\t--------------------ALL CUSTOMERS---------------------");
			while(rs.next()) {
				int cid=rs.getInt("cid");
				String name=rs.getString("custName");
				String email=rs.getString("custEmail");
				String pass=rs.getString("custPass");
				String num=rs.getString("custMobile");
				String add=rs.getString("custAddress");
				int accNum=rs.getInt("cust_accNumber");
				double bal=rs.getDouble("cust_balance");
				
//				System.out.println("        *#*#*#*#*#  Account Details #*#*#*#*#       ");
				System.out.println("Account Number    : "+accNum);
				System.out.println("Customer Name     : "+name);
				System.out.println("Email            		 : "+email);
				System.out.println("Password         	 : "+pass);
				System.out.println("Mobile Number      : "+num);
				System.out.println("Address        	 	 : "+add);
				System.out.println("Customer Balance : "+bal);
				System.out.println("----------------------------------------------------		");
				
				cu=new Customer(accNum,name,pass,email,num,add,bal);
			}
		}catch(SQLException e) {
			System.out.println("Invalid Account Number!!!!!");
		}
		return cu;
	}

	

}

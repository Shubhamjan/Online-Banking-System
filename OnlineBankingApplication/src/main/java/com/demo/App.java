package com.demo;

import java.sql.SQLException;
import java.util.Scanner;

import com.demo.dao.AdminServiceImpl;
import com.demo.dao.CustomerServiceImpl;
import com.demo.entity.Admin;
import com.demo.entity.Customer;
import com.demo.exception.CustomerException;
import com.demo.dao.AdminService;
import com.demo.dao.CustomerService;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		boolean f = true;

		while (f) {
			System.out.println("\t\t\t\t\t\t\t\t\t\t--------------------WELCOME TO BANKING SYSTEM--------------------\t\t\t\t");
			System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t-----------------------------------------------------------------");
			System.out.println("\n1.ADMIN LOGIN PORTAL\r\n" + "2.CUSTOMER\n");

			System.out.println("Choose your option");
			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				System.out.println("\t\t\t\t\t\t\t\t\t\t---------------------------ADMIN LOGIN CREDENTIALS-----------------------");
				System.out.println("\nEnter the username: ");
				String uname = sc.next();
				System.out.println("Enter the Password: ");
				String pass = sc.next();

				AdminService a = new AdminServiceImpl();
				try {
					Admin ad = a.Login(uname, pass);
					if (ad == null) {
						System.out.println("Wrong Credentials.....");
						break;
					}
					System.out.println("\n.....Login Successfully.....\n");
					System.out.println("WELCOME TO : "  + ad.getAcctUsername()+"  As A Admin  ");
				} catch (Exception e) {
					e.printStackTrace();
				}

				boolean y = true;

				while (y) {
					System.out.println("\r\n" + "1.Add New Customer Account\r\n"
							+ "2.Update Customer Address\r\n" + "3.Remove the Acccount by Account Number\r\n"
							+ "4.View Account Details using Account Number\r\n" + "5.View All Customer List\r\n"
							+ "6.Account Logout\r\n");

					int x = sc.nextInt();
					if (x == 1) {
						System.out.println("-------------New Account------------");
						System.out.println("Enter the customerName: ");
						String n1 = sc.next();
						System.out.println("Enter the customerEmail: ");
						String n2 = sc.next();
						System.out.println("Enter the customerPassword: ");
						String n3 = sc.next();
						System.out.println("Enter the customerMobile: ");
						String n4 = sc.next();
						System.out.println("Enter the customeAddress: ");
						String n5 = sc.next();
						System.out.println("Enter the AccountOpeningBalance: ");
						double n6 = sc.nextInt();

						int s1 = -1;
						try {
							s1 = a.addCustomer(n1, n2, n3, n4, n5);
							try {
								a.addAccount(n6, s1);
							} catch (Exception e) {
								e.printStackTrace();
							}
//        					System.out.println(s1);

						} catch (CustomerException e) {
							e.getMessage();
						}
						System.out.println("-------------------------------------------");
					}

					if (x == 2) {
						System.out.println("Update Customer Address..............");
						System.out.println("Enter the Customer Account Number");
						int k = sc.nextInt();
						System.out.println("Enter the new Address...");
						String adr = sc.next();

						try {
							String m = a.updateCustomer(k, adr);
						} catch (CustomerException e) {
							e.printStackTrace();
						}
					}
					if (x == 3) {
						System.out.println("-------------REMOVE ACCOUNT-------- ");
						System.out.println("Enter the account number :");
						int acc = sc.nextInt();
						String s = null;
						try {
							s = a.deleteAccount(acc);
						} catch (CustomerException e) {
							e.printStackTrace();
						}
//        				
					}

					if (x == 4) {
						System.out.println("----------------CUSTOMER DETAILS-------------------");
						System.out.println("Enter the account number: ");
						int ac = sc.nextInt();
						try {
							Customer ct = a.viewCustomer(ac);

							if (ct != null) {
								System.out.println("\n       Account Details        ");
								System.out.println("Account Number     : " + ct.getCust_accNumber());
								System.out.println("Customer Name      : " + ct.getCust_name());
								System.out.println("Email               	  : " + ct.getCust_email());
								System.out.println("Password         	  : " + ct.getCust_pass());
								System.out.println("Mobile Number       : " + ct.getCust_number());
								System.out.println("Address              	  : " + ct.getCust_address());
								System.out.println("Customer Balance  : " + ct.getCust_balance());
								System.out.println("-------------------------------------------------------------");
							} else {
								System.out.println("Account does not exist");
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (x == 5) {

						try {
//							System.out.println("\t\t\tAll Customer List\t\t\t");
							Customer c = a.viewAllCustomer();

						} catch (CustomerException e) {
							e.printStackTrace();
						}
					}
					if (x == 6) {
						System.out.println("-----------Account Logout Successfully---------\t\t\t\n\n");
						y = false;
					}
				}
				break;

			case 2:
				System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t---------------Customer Login---------------");
				System.out.println("\t\t\t\t\t\t\t\t\t\t\t------------------------------------------------------------------");
				System.out.println("Enter the User Name: ");
				String cname = sc.next();
				System.out.println("Enter the password: ");
				String cpass = sc.next();
				System.out.println("Enter the Account Number: ");
				int ac = sc.nextInt();

				CustomerService cs = new CustomerServiceImpl();

				try {
					Customer cv = cs.login(cname, cpass, ac);
					System.out.println("\nWelcome :" + cv.getCust_name());
					
					boolean m=true;
					
					while(m) {
						System.out.println("***********************************************************\r\n");
						System.out.println("1.View Balance\r\n"
								+"2.Deposit Money\r\n"
								+"3.Withdraw Money\r\n"
								+"4.Transfer Money\r\n"
								+"5.Log out");
						
						
						
						int x=sc.nextInt();
						
						if(x==1) {
							System.out.println("\n*********Current Balance*********\n");
							System.out.println(cv.getCust_name()+" Your Current Balance is "+cs.viewBalance(ac));
						}
						
						if(x==2) {
							System.out.println("--------Deposit Money----------");
							System.out.println("Enter Amount to Deposit :");
							double am=sc.nextDouble();
							
							System.out.println("\n"+cv.getCust_name()+" Your Current Balance is "+cs.Deposit(ac, am));
						}
						
						if(x==3) {
							System.out.println("\n-------Withdraw Money--------\n");
							System.out.println("Enter the withdraw money : ");
							double wd=sc.nextDouble();
							
							try {
								
								double bal=cs.withDraw(ac, wd);
								System.out.println("\nRS."+wd+" withdraw successfully");
//								System.out.println("--------------------");
								
							}catch(Exception e) {
								System.out.println(e.getMessage());
							}
						}
						
						if(x==4) {
							System.out.println("\n------------AMOUNT TRANSFER---------------");
							System.out.println("Enter the amount transfer: ");
							double t=sc.nextDouble();
							System.out.println("Enter the Account Number to transfer money");
							int an=sc.nextInt();
							
							try {
								
								cs.transferMoney(ac, t, an);
								System.out.println("Amount Transfer successfully.....!!!!");
//								System.out.println("---------------------------------");
								
							}catch(Exception e) {
								System.out.println(e.getMessage());
							}
						}
						
						if(x==5) {
							System.out.println("Customer Logout Successfully");
							System.out.println("\n\t\t\t\t\t\t\t\t\t********************Thank You For Using Our Services********************\n\n\n\n");
							m=false;
						}
						
					}
					break;

				} catch (Exception e) {
					System.out.println("something went wrong!!!...Please Try Again....");
				}
			}
		}
	}
}

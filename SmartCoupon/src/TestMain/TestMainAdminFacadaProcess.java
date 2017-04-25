package TestMain;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import Facades.AdminFacade;
import Facades.CouponClientFacade;
import Java_Beans.Company;
import Java_Beans.Customer;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;

public class TestMainAdminFacadaProcess {
	
	CouponClientFacade couponClientFacade = null ;
	Scanner scanner = null;
	
	
	public TestMainAdminFacadaProcess(CouponClientFacade couponClientFacade, Scanner scanner) {
		super();
		this.couponClientFacade = couponClientFacade;
		this.scanner = scanner;
	}

	public  void Process() throws SQLException {
		AdminFacade adminFacade = (AdminFacade)couponClientFacade;
		
		do{
	    	createAdminMenu( );
	    	switch(scanner.nextInt()){
	    	case 1:
	    		createCompany( adminFacade,  scanner );
	    		break;
	    	case 2:
	    		removeCompany(adminFacade,  scanner );
	    		break;
	    	case 3:
	    		updateCompany(adminFacade,  scanner );
	    		break;
	    	case 4:
	    		getAllCompanies(adminFacade,  scanner );
	    		break;
	    	case 5:
	    		getCompany(adminFacade,  scanner );
	    		break;
	    	case 6:
	    		createCustomer( adminFacade,  scanner );
	    		break;
	    	case 7:
	    		removeCustomer(adminFacade,  scanner );
	    		break;
	    	case 8:
	    		updateCustomer(adminFacade,  scanner );
	    		break;
	    	case 9:
	    		getAllCustomers(adminFacade,  scanner );
	    		break;
	    	case 10:
	    		getCustomer(adminFacade,  scanner );
	    		break;
	    	case 11:
	    		adminFacade = null;
	    		scanner.close();
				System.exit(0);
	    		break;
	    	default:
	    		System.out.println("Please enter a action (1-11):");
	    		break;
	    	}
		}while(adminFacade != null);
	}
	
	public  void createAdminMenu( ){
		System.out.println("==== Admin Menu ====");
		///company
		System.out.println("1: Create company");
		System.out.println("2: Remove company");
		System.out.println("3: Update company");
		System.out.println("4: Companies report - Get all companies");
		System.out.println("5: company details report  - Get company");
		///customer
		System.out.println("6: Create customer");
		System.out.println("7: Remove customer");
		System.out.println("8: Update customer");
		System.out.println("9: Customers report - Get all customers");
		System.out.println("10: Customer details report  - Get customer");
	
		System.out.println("11: Exit");
		System.out.println("Please enter a action (1-11):");
	}
	
	private  void createCompany(AdminFacade adminFacade, Scanner scanner)  {
		System.out.println("Please enter a company name:");
		String compName = scanner.next();
		scanner.nextLine();
		System.out.println("Please enter a password:");
		String password = scanner.next();
		scanner.nextLine();
		System.out.println("Please enter a email:");
		String email = scanner.next();
		scanner.nextLine();
		try {
			adminFacade.createCompany(new Company(compName, password, email));
		} catch (SQLException | DuplicateException | ParseException e) {
			System.err.println(e.getMessage());
		}
	
	}
	
	private  void removeCompany(AdminFacade adminFacade, Scanner scanner) throws SQLException {
		System.out.println("Please enter a company name to be deleted:");
		String compName = scanner.next();
		scanner.nextLine();
		try {
			adminFacade.removeCompany(new Company(compName));
		} catch (NotAvailableException | ParseException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
private  void updateCompany(AdminFacade adminFacade, Scanner scanner) throws SQLException {
		
		
		System.out.println("Please enter a company name for update details:");
		String compName = scanner.next();
		scanner.nextLine();
		System.out.println("Please enter new password:");
		String password = scanner.next();
		scanner.nextLine();
		System.out.println("Please enter new email:");
		String email = scanner.next();
		scanner.nextLine();
		
		adminFacade.updateCompany(new Company(compName, password, email));	

	}

	private  void getAllCompanies(AdminFacade adminFacade, Scanner scanner) {
		ArrayList<Company> companys = null;
		try {
			companys = adminFacade.getAllCompanies();
		} catch (SQLException | ParseException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(companys);
		
	}

	private  void getCompany(AdminFacade adminFacade, Scanner scanner) throws SQLException {
		
		System.out.println("Please enter a company id for company report:");
		long id = scanner.nextInt();
		scanner.nextLine();
		Company company = null;
		try {
			company = adminFacade.getCompany(new Company(id));
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		if(company == null)
		{
			System.out.println("The company is not found, please enter right id !");
		}
		else{
			System.out.println(company);	
		}
		
	}

		
	public  void createCustomer(AdminFacade adminFacade, Scanner scanner ) throws SQLException{
		System.out.println("Please enter a customer name:");
		String custName = scanner.next();
		scanner.nextLine();
		System.out.println("Please enter a password:");
		String password = scanner.next();
		scanner.nextLine();
		try {
			adminFacade.createCustomer(new Customer(custName, password));
		} catch (DuplicateException | ParseException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	private static void removeCustomer(AdminFacade adminFacade, Scanner scanner) throws SQLException {
		System.out.println("Please enter a customer name to be deleted:");
		String custName = scanner.next();
		scanner.nextLine();
		try {
			adminFacade.removeCustomer(new Customer(custName));
		} catch (NotAvailableException | ParseException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private  void updateCustomer(AdminFacade adminFacade, Scanner scanner) throws SQLException {
		System.out.println("Please enter a customer name for update details:");
		String custName = scanner.next();
		scanner.nextLine();
		System.out.println("Please enter new password:");
		String password = scanner.next();
		scanner.nextLine();
		adminFacade.updateCustomer(new Customer(custName, password));		
	}
	
	private  void getAllCustomers(AdminFacade adminFacade, Scanner scanner) {
		ArrayList<Customer> customers = null;
		try {
			customers = adminFacade.getAllCustomers();
		} catch (SQLException | ParseException e) {
			System.err.println(e.getMessage());
		}
		System.out.println(customers);
	}
	
	
private  void getCustomer(AdminFacade adminFacade, Scanner scanner)  {
		
		System.out.println("Please enter a customer id for customer report:");
		long id = scanner.nextInt();
		scanner.nextLine();
		Customer customer = null;
		try {
			customer = adminFacade.getCustomer(new Customer(id));
		} catch (SQLException | NotAvailableException | ParseException e) {
			System.err.println(e.getMessage());
		}
		if(customer == null)
		{
			System.out.println("The customer is not found, please enter right id !");
		}
		else{
			System.out.println(customer);	
		}
		
	}

}

package TestMain;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import Facades.AdminFacade;
import Facades.ClientType;
import Facades.CompanyFacade;
import Facades.CouponClientFacade;
import Facades.CouponSystem;
import Facades.CustomerFacade;

public class testMain {

	public static void main(String[] args)  {

		CouponSystem couponSystem = CouponSystem.getInstance();
		Scanner scanner = new Scanner(System.in);
		CouponClientFacade  couponClientFacade = null;
		
		do{
		couponClientFacade = login(couponSystem ,scanner);
		
		if(couponClientFacade == null)
			System.out.println("One of the details is incorrect. Please try again.\n");
		
		}while(couponClientFacade == null);
		
		switch (couponClientFacade.getClass().getName()) {
	       case "Facades.AdminFacade"   :
		       TestMainAdminFacadaProcess testMainAdminFacadaProcess = new TestMainAdminFacadaProcess(couponClientFacade, scanner);
		       try {
				testMainAdminFacadaProcess.Process();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		       break;
	       case "Facades.CustomerFacade":
	    	   TestMainCustomerFacadaProcess testMainCustomerFacadaProcess = new TestMainCustomerFacadaProcess(couponClientFacade, scanner);
	    	   try {
				testMainCustomerFacadaProcess.Process();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
	    	   break;
	       case "Facades.CompanyFacade" :	    	   
	    	   TestMainCompanyFacadaProcess testMainCompanyFacadaProcess = new TestMainCompanyFacadaProcess(couponClientFacade, scanner);
	    	   try {
				testMainCompanyFacadaProcess.Process();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}	    	   
	    	   break;

	       default        : 
	    	   break;
			}
	}
	
	public static CouponClientFacade login(CouponSystem couponSystem, Scanner scanner ){
		System.out.println("==== login ====");
		System.out.println("To identify");
		System.out.println("Please enter a username:");
		String userName = scanner.next();
		scanner.nextLine();
		System.out.println("Please enter a password:");
		String password = scanner.next();
		scanner.nextLine();
		System.out.println("Please enter a client type (1 for Admin, 2 for Company, 3 for Customer):");
		int clientTypeCode = scanner.nextInt();
		scanner.nextLine();
		
		switch (clientTypeCode) {
        case 1   : try {
				return (AdminFacade)couponSystem.login(userName, password, ClientType.ADMIN);
			} catch (SQLException | ParseException e) {
				System.err.println(e.getMessage());
			}
        case 2  : try {
				return  (CompanyFacade)couponSystem.login(userName, password, ClientType.COMPANY);
			} catch (SQLException | ParseException e) {
				System.err.println(e.getMessage());
			} 
        case 3  : try {
			return  (CustomerFacade)couponSystem.login(userName, password, ClientType.CUSTOMER);
		} catch (SQLException | ParseException e) {
			System.err.println(e.getMessage());
		}
        default    : return null;
		}	
	}
}




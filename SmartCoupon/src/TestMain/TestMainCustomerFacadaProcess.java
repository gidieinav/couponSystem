package TestMain;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import Facades.CouponClientFacade;
import Facades.CustomerFacade;
import Java_Beans.Coupon;
import Java_Beans.CouponType;
import customExceptions.RanOutOfStockException;

public class TestMainCustomerFacadaProcess {
	
	CouponClientFacade couponClientFacade = null ;
	Scanner scanner = null;
	
	
	public TestMainCustomerFacadaProcess(CouponClientFacade couponClientFacade, Scanner scanner) {
		super();
		this.couponClientFacade = couponClientFacade;
		this.scanner = scanner;
	}

	public  void Process() throws SQLException {
		CustomerFacade customerFacade = (CustomerFacade)couponClientFacade;
		
		do{
	    	createCustomerMenu( );
	    	switch(scanner.nextInt()){
	    	case 1:
	    		purchaseCoupon( customerFacade,  scanner );
	    		break;
	    	case 2:
	    		getAllCouponsReport(customerFacade,  scanner );
	    		break;
	    	case 3:
	    		getCouponsReportByType( customerFacade,  scanner );
	    		break;
	    	case 4:
	    		getCouponsReportByPrice(customerFacade,  scanner );
	    		break;
	    	case 5:
	    		customerFacade = null;
	    		scanner.close();
				System.exit(0);
	    		break;
	    	default:
	    		System.out.println("Please enter a action (1-5):");
	    		break;
	    	} 
		}while(customerFacade != null);
	}
	
	public  void createCustomerMenu( ){
		System.out.println("==== Customer Menu ====");
		System.out.println("1: Purchase Coupon");
		System.out.println("2: All coupons report");
		System.out.println("3: Coupons report by type");
		System.out.println("4: Coupons report by price");
		System.out.println("5: Exit");
		System.out.println("Please enter a action (1-5):");
	}
	
	
	private void purchaseCoupon(CustomerFacade customerFacade, Scanner scanner){
		System.out.println("Please enter a coupon title:");
		String title = scanner.next();
		try {
			customerFacade.purchaseCoupon(new Coupon(title));
		} catch (SQLException | ParseException | RanOutOfStockException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void getAllCouponsReport(CustomerFacade customerFacade, Scanner scanner){
		
		try {
			System.out.println(customerFacade.getAllPurchaseCoupon());
		} catch (SQLException | ParseException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void getCouponsReportByType(CustomerFacade customerFacade, Scanner scanner){
		System.out.println("Please enter a coupon type:");
		int min=1, max=1;
		for (CouponType couponType : CouponType.values() ) {
			System.out.println(couponType.getValue() +": "+couponType);
			
			if(min >= couponType.getValue())
				min = couponType.getValue();
			
			if(max <= couponType.getValue())
				max = couponType.getValue();
		}
		System.out.println("Please enter a code coupon type ("+min+"-"+max+")");
		int couponTypeInt = scanner.nextInt();	
		
		try {
		System.out.println(customerFacade.getCouponByType(CouponType.getCouponType(couponTypeInt)));
		} catch (SQLException | ParseException e) {
			System.err.println(e.getMessage());
		}
	}

private void getCouponsReportByPrice(CustomerFacade customerFacade, Scanner scanner){
	System.out.println("Please enter a coupon price:");
	Double couponPrice = scanner.nextDouble();
	try {
		System.out.println(customerFacade.getCouponsByPrice(couponPrice));
	} catch (SQLException | ParseException e) {
		System.err.println(e.getMessage());
	}
}

}

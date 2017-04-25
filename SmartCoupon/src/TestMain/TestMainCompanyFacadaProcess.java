package TestMain;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import Facades.CompanyFacade;
import Facades.CouponClientFacade;
import Java_Beans.Coupon;
import Java_Beans.CouponType;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;



public class TestMainCompanyFacadaProcess {
	
	CouponClientFacade couponClientFacade = null ;
	Scanner scanner = null;
	
	
	public TestMainCompanyFacadaProcess(CouponClientFacade couponClientFacade, Scanner scanner) {
		super();
		this.couponClientFacade = couponClientFacade;
		this.scanner = scanner;
	}

	public  void Process() throws SQLException {
		CompanyFacade companyFacade = (CompanyFacade)couponClientFacade;
		
		do{
	    	createCompanyMenu( );
	    	switch(scanner.nextInt()){
	    	case 1:
	    		createCoupon( companyFacade,  scanner );
	    		break;
	    	case 2:
	    		removeCoupon(companyFacade,  scanner );
	    		break;
	    	case 3:
	    		updateCoupon(companyFacade,  scanner );
	    		break;
	    	case 4:
	    		getCompany(companyFacade,  scanner );
	    		break;
	    	case 5:
	    		getAllCouponsReport(companyFacade,  scanner );
	    		break;
	    	case 6:
	    		getCouponsReportByType( companyFacade,  scanner );
	    		break;
	    	case 7:
	    		getCouponsReportByPrice(companyFacade,  scanner );
	    		break;
	    	case 8:
	    		getCouponsReportByDate(companyFacade,  scanner );
	    		break;
	    	case 9:
	    		companyFacade = null;
	    		scanner.close();
				System.exit(0);
	    		break;
	    	default:
	    		System.out.println("Please enter a action (1-9):");
	    		break;
	    	}
		}while(companyFacade != null);
	}
	
	public  void createCompanyMenu( ){
		System.out.println("==== Company Menu ====");
		System.out.println("1: Create/Add Coupon");
		System.out.println("2: Remove Coupon");
		System.out.println("3: Update Coupon");
		System.out.println("4: get Company");
		System.out.println("5: All coupons report");
		System.out.println("6: Coupons report by type");
		System.out.println("7: Coupons report by price");
		System.out.println("8: Coupons report by date");
		System.out.println("9: Exit");
		System.out.println("Please enter a action (1-9):");
	}
	
	private void createCoupon(CompanyFacade companyFacade, Scanner scanner){
	
		Date startDate =  new Date();
		Date endDate =  new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Please enter a coupon title:");
		String coupontitle = scanner.next();
		
		System.out.println("Please enter a coupon start date in format dd/MM/yyyy:");
		String couponStartDate = scanner.next();
		try {
			startDate = dateFormat.parse(couponStartDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Please enter a coupon end date in format dd/MM/yyyy:");
		String couponEndDate = scanner.next();
		try {
			endDate = dateFormat.parse(couponEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Please enter a amount:");
		int amount = scanner.nextInt();
		
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
		
		System.out.println("Please enter a coupon message:");
		scanner.nextLine();
		String couponMessage = scanner.nextLine();
		
		System.out.println("Please enter a coupon price:");
		Double couponPrice = scanner.nextDouble();
		
		System.out.println("Please enter a coupon image:");
		String couponImage = scanner.next();
		
		try {
			companyFacade.creatCoupon(new Coupon(0, coupontitle, startDate, endDate, amount,CouponType.getCouponType(couponTypeInt), couponMessage ,couponPrice, couponImage));
		} catch (SQLException | DuplicateException | ParseException e) {
			System.err.println(e.getMessage());
		}	
	}
	
	private void removeCoupon(CompanyFacade companyFacade, Scanner scanner){
		System.out.println("Please enter a coupon title:");
		String coupontitle = scanner.next();
		try {
			companyFacade.removeCoupon(new Coupon(coupontitle));
		} catch (SQLException | NotAvailableException | ParseException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void updateCoupon(CompanyFacade companyFacade, Scanner scanner){
		
		Date endDate =  new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
	
		System.out.println("Please enter the coupon title you want to update:");
		String coupontitle = scanner.next();
		
		System.out.println("Please enter a updated coupon end date in format dd/MM/yyyy:");
		String couponEndDate = scanner.next();
		try {
			endDate = dateFormat.parse(couponEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Please enter a updated coupon price:");
		Double couponPrice = scanner.nextDouble();
		
		try {
			companyFacade.updateCoupon(new Coupon(coupontitle,endDate,couponPrice));
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void getCompany(CompanyFacade companyFacade, Scanner scanner){
		try {
			System.out.println(companyFacade.getCompany());
		} catch (SQLException | ParseException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private void getAllCouponsReport(CompanyFacade companyFacade, Scanner scanner){
		
		try {
			System.out.println(companyFacade.getAllCoupon());
		} catch (SQLException | ParseException e) {
			System.err.println(e.getMessage());
		}
	}
	
private void getCouponsReportByType(CompanyFacade companyFacade, Scanner scanner){
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
			System.out.println(companyFacade.getCouponByType(CouponType.getCouponType(couponTypeInt)));
		} catch (SQLException | ParseException e) {
			System.err.println(e.getMessage());
		}
	}

private void getCouponsReportByPrice(CompanyFacade companyFacade, Scanner scanner){
	System.out.println("Please enter a coupon price:");
	Double couponPrice = scanner.nextDouble();
	try {
		System.out.println(companyFacade.getCouponsByPrice(couponPrice));
	} catch (SQLException | ParseException e) {
		System.err.println(e.getMessage());
	}
}

private void getCouponsReportByDate(CompanyFacade companyFacade, Scanner scanner){
	
	System.out.println("Under construction"); ////// ===========================
}


	
	

}

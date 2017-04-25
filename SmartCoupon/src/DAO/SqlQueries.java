package DAO;
/**
 * This static class implements all the SQL queries
 *  @author Gidi
 *
 */
public class SqlQueries {

	public static final String allCompanies = "SELECT * FROM Company ;";
    public static final  String getCompanyByName = "SELECT * FROM Company WHERE COMP_NAME = '%s'";   		
	public static final String insertCompanyToDB = "INSERT INTO Company VALUES ('%s','%s' ,'%s');" ;  					 
	public static final String getCompIdByName = "SELECT ID FROM Company WHERE COMP_NAME = '%s';";
	public static final String removeCustomerCouponByCompID =  "DELETE Customer_Coupon WHERE COUPON_ID IN " 
		+ "(SELECT COUPON_ID FROM Company_Coupon WHERE COMP_ID = %d );"; 
	public static String removeCouponsByCompID = "DELETE Coupon WHERE ID IN " 
		+ "(SELECT  COUPON_ID FROM Company_Coupon WHERE COMP_ID = %d );"; 
	public static final String removeCompanyCouponByCompID = "DELETE Company_Coupon WHERE COMP_ID = %d;";	
	public static final String removeCompanyByName = "DELETE Company WHERE COMP_NAME = '%s' ;"; 
	public static final String updateCompany = "UPDATE Company SET PASSWORD = '%s' , EMAIL = '%s'"	
	    +" WHERE COMP_NAME = '%s' " ; 
	public static final String getCmpanyByID = "SELECT * FROM Company WHERE ID = %d;"; 
	public static final String loginExecut = "SELECT * FROM Company WHERE COMP_NAME = '%s' AND PASSWORD = '%s';";

	public static final String couponByTitle = "SELECT * FROM Coupon WHERE TITLE = '%s' ;";
	public static final String insertCouponToDB = "INSERT INTO Coupon VALUES ('%s','%s','%s',%d,'%s','%s',%f,'%s');";
	public static final String bindCouponToCompanyInDB = "INSERT INTO Company_Coupon VALUES (%d,%d);";	
	public static final String couponIdFromDB = "SELECT ID FROM Coupon WHERE TITLE = '%s';";
	public static final String removeCompanyCouponByCouponID = " DELETE Company_Coupon WHERE coupon_ID = %d ; "	;
	public static final String removeCustomerCouponByCouponDI = "DELETE Customer_Coupon WHERE coupon_ID = %d ;";
	public static final String removeCouponByCouponID = "DELETE Coupon WHERE ID = %d;";
	public static final String updatingCoupon = "UPDATE Coupon SET END_DATE = '%s' , PRICE = %f WHERE TITLE = '%s';";
	public static final String couponByID = "SELECT * FROM Coupon WHERE ID = %d ;";
	public static final String allCouponsInDB = "SELECT * FROM Coupon ;"; 
	public static final String allCouponsByCompanyID = "SELECT * FROM Coupon WHERE ID IN"
			+ "(SELECT  coupon_ID FROM Company_Coupon WHERE comp_ID = %d );";
	public static final String allCouponsByTypeAndCompID = "SELECT * FROM Coupon WHERE TYPE = '%s' AND ID IN "
			+ "(SELECT Coupon_ID FROM Company_Coupon WHERE comp_ID = %d );"; 
	
	public static final String customerByCustName = "SELECT * FROM Customer WHERE CUST_NAME = '%s'" ;  
	public static final String insertCustomerToDB = "INSERT INTO Customer VALUES ('%s','%s');";	   
	public static final String customerIDByCustName = "SELECT ID FROM Customer WHERE CUST_NAME = '%s'";
	public static final String removeCustomerCouponByCustID = "DELETE Customer_Coupon WHERE CUST_ID = %d " ;
	public static final String removeCustomerByID = "DELETE Customer WHERE ID = %d ;";
	public static final String updateCustomer = "UPDATE Customer SET PASSWORD = '%s' WHERE CUST_NAME = '%s'" ;
	public static final String customerByID = "SELECT * FROM Customer WHERE ID = %d ;";
	public static final String allCustomers = "SELECT * FROM Customer ";
	public static final String getCouponsByCustName = "SELECT * FROM Coupon WHERE ID IN "
	        + "(SELECT COUPON_ID FROM Customer_Coupon WHERE CUST_ID = "
	        + "(SELECT ID FROM Cusromer WHERE CUST_NAME = '%s'));";
	public static final String ifCustomerOwnCoupon = "SELECT * FROM Customer_Coupon WHERE CUST_ID = %d" 
			+ " AND COUPON_ID =(SELECT ID FROM Coupon WHERE TITLE = '%s'); ";
	public static final String couponAmountEndDateId = "SELECT AMOUNT ,END_DATE,ID FROM Coupon WHERE TITLE = '%s';";
	public static final String purchaseCouponDB = "INSERT INTO Customer_Coupon VALUES (%d,%d);";
	public static final String setAmount = "UPDATE Coupon SET AMOUNT = %d WHERE TITLE = '%s'";
	public static final String allPurchaseCoupons = "SELECT * FROM Coupon WHERE ID IN "
			+ "(SELECT COUPON_ID FROM Customer_Coupon WHERE CUST_ID  = %d);";
	public static final String purchaseCouponsByType = "SELECT * FROM Coupon WHERE TYPE = '%s'" 
			+ " AND ID IN (SELECT COUPON_ID FROM Customer_Coupon WHERE CUST_ID = %d);";
	public static final String gettingCouponsByPrice = "SELECT * FROM Coupon WHERE PRICE <= %f" 
			+ " AND ID IN (SELECT COUPON_ID FROM Customer_Coupon WHERE CUST_ID = %d);";
	public static final String customerloginExecute = "SELECT * FROM Customer WHERE CUST_NAME = '%s'" 
			+" AND PASSWORD = '%s' ;"; 
	public static final String allCouponsByTypeAndCustID = "SELECT * FROM Coupon WHERE TYPE = '%s' AND ID IN "
			+ "(SELECT Coupon_ID FROM Customer_Coupon WHERE CUST_ID = %d );"; 
}

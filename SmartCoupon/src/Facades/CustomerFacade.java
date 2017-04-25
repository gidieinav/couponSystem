package Facades;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import DAO.CouponDBDAO;
import DAO.CustomerDBDAO;
import Java_Beans.Coupon;
import Java_Beans.CouponType;
import customExceptions.RanOutOfStockException;
/**
 * Class Facade managing the actions of the Customer
 * This Class implements {@link CouponClientFacade} 
 * @author Gidi
 */
public class CustomerFacade implements CouponClientFacade {
	
	CustomerDBDAO customerDBDAO = new CustomerDBDAO();
	private CouponDBDAO couponDBDAO = new CouponDBDAO (); 
	/**
	 * Constructor
	 */
	public void CustomerFacada(){
	}
	/**
	 * purchase coupon
	 * @param coupon
	 * @throws SQLException
	 * @throws ParseException
	 * @throws RanOutOfStockException
	 */
	public void purchaseCoupon(Coupon coupon) throws SQLException, ParseException, RanOutOfStockException{
		
		if(couponDBDAO.isValidCoupon(coupon) && !customerDBDAO.ifCustomerOwnCoupon(coupon))
		{
			coupon.setId(couponDBDAO.getCoupon(coupon.getTitle()).getId());
			customerDBDAO.purchaseCoupon(coupon);
			couponDBDAO.decrementAmountOfCoupons(coupon);
			System.out.println("Congratulation, You just Purchased a new coupon  !");
		}
	}
	/**
	 * get all purchase Coupon
	 * @return ArrayList<Coupon>
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getAllPurchaseCoupon() throws SQLException, ParseException {
			return couponDBDAO.getAllCustomerCoupons();
	}
	/**
	 * login
	 * @param String name
	 * @param String password
	 * @return CouponClientFacade
	 */
	@Override
	public CouponClientFacade login(String name, String password) throws SQLException, ParseException {
			if(customerDBDAO.login(name, password))
			{
				couponDBDAO.setCustomerId(customerDBDAO.getCustomerId());
				return this;
			}
			return null;
	}
	/**
	 * get all Coupons of Customer in current session By Type
	 * @param type
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getCouponByType(CouponType type) throws SQLException, ParseException{
			return couponDBDAO.getCustomerCouponsByType(type);
	}
	/**
	 * get all Coupons of Customer in current session By price
	 * @param price
	 * @return ArrayList<Coupon>
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getCouponsByPrice(Double price) throws SQLException, ParseException{
			return couponDBDAO.getCustomerCouponsByPrice(price);
	}
}

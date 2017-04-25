package DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import Java_Beans.Coupon;
import Java_Beans.CouponType;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
import customExceptions.RanOutOfStockException;

/**
 * Interface class that has DataBase operations for a coupon.
 * @author Gidi
 *
 */

public interface CouponDAO {
	
	public void createCoupon(Coupon coupon) throws SQLException, DuplicateException, ParseException;
	public void removeCoupon(Coupon coupon) throws SQLException, NotAvailableException, ParseException;
	public void updateCoupon(Coupon coupon) throws SQLException;
	public Coupon getCoupon(long id) throws SQLException, ParseException;
	public Coupon getCoupon(String Title) throws SQLException, ParseException;
	public ArrayList<Coupon> getAllCoupons() throws SQLException, ParseException;
	public ArrayList<Coupon> getCouponsByType(CouponType type) throws SQLException, ParseException;
	boolean isValidCoupon(Coupon coupon) throws SQLException, ParseException, RanOutOfStockException;
	public ArrayList<Coupon> getCompanyCouponsByType(CouponType type) throws SQLException, ParseException;

}

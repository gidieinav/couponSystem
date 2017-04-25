package Facades;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import DAO.CompanyDBDAO;
import DAO.CouponDBDAO;
import Java_Beans.Company;
import Java_Beans.Coupon;
import Java_Beans.CouponType;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;

/**
 * Class Facade managing the actions of the Company
 * This Class implements {@link CouponClientFacade} 
 * @author Gidi
 */

public class CompanyFacade implements CouponClientFacade {

	private CompanyDBDAO companyDBDAO = new CompanyDBDAO() ;
	private CouponDBDAO couponDBDAO = new CouponDBDAO ();
	/**
	 * Constructor
	 */
	public CompanyFacade() {
		super();
	}
	/**
	 * create coupon in DB
	 * @param coupon
	 * @throws SQLException
	 * @throws DuplicateException
	 * @throws ParseException
	 */
	public void creatCoupon(Coupon coupon) throws SQLException, DuplicateException, ParseException {
			couponDBDAO.createCoupon(coupon);
	}
	/**
	 * remove coupon from DB
	 * @param coupon
	 * @throws SQLException
	 * @throws NotAvailableException
	 * @throws ParseException
	 */
	public void removeCoupon(Coupon coupon) throws SQLException, NotAvailableException, ParseException{
			couponDBDAO.removeCoupon(coupon);

	}
	/**
	 * update end date and price of Coupon
	 * @param coupon
	 * @throws SQLException
	 */
	public void updateCoupon(Coupon coupon) throws SQLException{
			couponDBDAO.updateCoupon(coupon);
	}
	/**
	 * get specific Coupon by id
	 * @param id
	 * @return Coupon
	 * @throws SQLException
	 * @throws ParseException
	 */
	public Coupon getCoupon(long id) throws SQLException, ParseException{		
			return  couponDBDAO.getCoupon(id);
	}
	/**
	 * get the Company in current session 
	 * @return Company
	 * @throws SQLException
	 * @throws ParseException
	 */
	public Company getCompany() throws SQLException, ParseException{		
				Company company = companyDBDAO.getCompany(companyDBDAO.getCompanyId());
				company.setCoupons(getAllCoupon());
				return company;
	}
	/**
	 * get all Coupons of Company in current session
	 * ArrayList<Coupon>
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getAllCoupon() throws SQLException, ParseException{
			return couponDBDAO.getAllCompanyCoupons();
	}
	/**
	 * get all Coupons of Company in current session By Type
	 * ArrayList<Coupon>
	 * @param type
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getCouponByType(CouponType type) throws SQLException, ParseException{
			return couponDBDAO.getCompanyCouponsByType(type);
	}
	/**
	 * get all Coupons of Company in current session By price
	 * ArrayList<Coupon>	
	 * @param price
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getCouponsByPrice(Double price) throws SQLException, ParseException{
			return couponDBDAO.getCompanyCouponsByPrice(price);
	}
	/**
	 * login
	 * @param String name
	 * @param String password
	 * @return CouponClientFacade
	 */	
	@Override
	public CouponClientFacade login(String name, String password) throws SQLException, ParseException {

			if(companyDBDAO.login(name, password))
			{
				couponDBDAO.setCompanyId(companyDBDAO.getCompanyId());
				return this;
			}
			return null;
	}
}

package DAO;


import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import DbOperator.CouponDbOperator;
import Java_Beans.Coupon;
import Java_Beans.CouponType;
import Java_Beans.DbComponent;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
import customExceptions.RanOutOfStockException;
/**
 * class that has DataBase operations for a Coupon.
 * This Class implements {@link CompanyDAO} 
 * @author Gidi
 */
public class CouponDBDAO implements CouponDAO {
	
	private  long couponId;
	private  long companyId;
	private  long customerId;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}
	/**
	 * create coupon in DB
	 * @param coupon
	 * @throws SQLException
	 * @throws DuplicateException
	 * @throws ParseException
	 */
	@Override
	public void createCoupon(Coupon coupon) throws SQLException, DuplicateException, ParseException {		
		if(!couponExist(coupon.getTitle())){

			Date startDate = coupon.getStartDate();
			Date endDate = coupon.getEndDate();

			SimpleDateFormat startDateFormat = new SimpleDateFormat("yyyy/MM/dd",Locale.US);
			SimpleDateFormat endDateFormat = new SimpleDateFormat("yyyy/MM/dd",Locale.US);
			String startDateString= startDateFormat.format(startDate);
			String endDateString = endDateFormat.format(endDate);
			
			CouponDbOperator couponBuilder1 = new CouponDbOperator();
			String sqlQueryInsertCouponToDB = String.format(SqlQueries.insertCouponToDB, coupon.getTitle(),startDateString,endDateString,coupon.getAmount(),
					coupon.getType(),coupon.getMessage(),coupon.getPrice(),coupon.getImage());
			setCouponId(couponBuilder1.buildDbComponentCreator(sqlQueryInsertCouponToDB)) ;
			
			CouponDbOperator couponBuilder2 = new CouponDbOperator();
			String sqlQuerybindCouponToCompanyInDB = String.format(SqlQueries.bindCouponToCompanyInDB,getCompanyId(),getCouponId());
			couponBuilder2.buildWriter(sqlQuerybindCouponToCompanyInDB) ;
			
		}
		else
		{
			throw new DuplicateException("the coupon "+ coupon.getTitle()+" is alredy exist!");
		}
	}
	/**
	 * remove coupon from DB
	 * @param coupon
	 * @throws SQLException
	 * @throws NotAvailableException
	 * @throws ParseException
	 */
	@Override
	public void removeCoupon(Coupon coupon) throws SQLException, NotAvailableException, ParseException {
		CouponDbOperator checkBeforeRemoveBuilder = new CouponDbOperator();	
		String sqlQueryGetCouponByTitle = String.format(SqlQueries.couponByTitle, coupon.getTitle());
		ArrayList<DbComponent> dbComponents = checkBeforeRemoveBuilder.buildSelected(sqlQueryGetCouponByTitle);
		
		if(!dbComponents.isEmpty()){
			
			CouponDbOperator couponBuilder1 = new CouponDbOperator();
			String sqlQuery1 = String.format(SqlQueries.removeCompanyCouponByCouponID, ((Coupon)dbComponents.get(0)).getId());
			couponBuilder1.buildWriter(sqlQuery1) ;
			
			CouponDbOperator couponBuilder2 = new CouponDbOperator();
			String sqlQuery2 = String.format(SqlQueries.removeCustomerCouponByCouponDI, ((Coupon)dbComponents.get(0)).getId());
			couponBuilder2.buildWriter(sqlQuery2) ;
			
			CouponDbOperator couponBuilder3 = new CouponDbOperator();
			String sqlQuery3 = String.format(SqlQueries.removeCouponByCouponID, ((Coupon)dbComponents.get(0)).getId());
			couponBuilder3.buildWriter(sqlQuery3) ;
			
			System.out.println("The Coupon whith title "+coupon.getTitle()+" is removed!"); 
		}
		else{
	           throw new NotAvailableException("The coupon with title "+coupon.getTitle()+" is not found!");	
	           }
	}
	/**
	 * update end date and price of Coupon
	 * @param coupon
	 * @throws SQLException
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws SQLException {
		
		Date endDate = coupon.getEndDate();
		SimpleDateFormat endDateFormat = new SimpleDateFormat("yyyy/MM/dd",Locale.US);
		String endDateString = endDateFormat.format(endDate);
		
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.updatingCoupon, endDateString,coupon.getPrice(),
				coupon.getTitle());
		couponBuilder.buildWriter(sqlQuery) ;
	}
	/**
	 * get specific Coupon by id
	 * @param id
	 * @return Coupon
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Override
	public Coupon getCoupon(long id) throws SQLException, ParseException {
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.couponByID, id);
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(!dbComponents.isEmpty())
		{
			return  (Coupon) dbComponents.get(0);
		}
		return null;
	}
	/**
	 * get specific Coupon by title
	 * @param String Title
	 * @return Coupon
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Override
	public Coupon getCoupon(String Title) throws SQLException, ParseException {
		
		
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.couponByTitle, Title);
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(!dbComponents.isEmpty())
		{
			return  (Coupon) dbComponents.get(0);
		}
		return null;
	}
	
	private boolean couponExist(String Title) throws SQLException, ParseException {
		
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.couponByTitle, Title);
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(dbComponents.isEmpty())
		{
			return false;
		}
		return true;
	}
	/**
	 * is Valid Coupon
	 * @param Coupon
	 * @return boolean
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Override
	public boolean isValidCoupon(Coupon coupon) throws SQLException, ParseException, RanOutOfStockException {
		
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.couponByTitle,coupon.getTitle());
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		if(!dbComponents.isEmpty())
		{
			Coupon dbCoupon =  (Coupon) dbComponents.get(0);
			if (dbCoupon.getAmount() == 0) {
				throw new RanOutOfStockException("Sorry, the coupon "+dbCoupon.getTitle()+" is SOLD OUT");
			}
			if (dbCoupon.getEndDate().before(new Date())) {
				throw new RanOutOfStockException("Sorry the coupon "+coupon.getTitle()+" is expired");
			}
			return true;
		}
		return false;
	}
	
	/**
	 * get all Coupons 
	 * @return ArrayList<Coupon>
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Override
	public ArrayList<Coupon> getAllCoupons() throws SQLException, ParseException {

		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.allCouponsInDB);
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(!dbComponents.isEmpty())
		{
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			for (DbComponent component : dbComponents) {
				coupons.add((Coupon)component);
			}
			return coupons;
		}
		return null;
		
	}
	/**
	 * get all Coupons of Company in current session
	 * @return ArrayList<Coupon>
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getAllCompanyCoupons() throws SQLException, ParseException  {
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.allCouponsByCompanyID, this.getCompanyId());
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(!dbComponents.isEmpty())
		{
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			for (DbComponent component : dbComponents) {
				coupons.add((Coupon)component);
			}
			return coupons;
		}
		return null;
	}
	/**
	 * get all Coupons of Customer in current session
	 * @return ArrayList<Coupon>
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getAllCustomerCoupons() throws SQLException, ParseException  {
		
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.allPurchaseCoupons, this.getCustomerId());
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(!dbComponents.isEmpty())
		{
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			for (DbComponent component : dbComponents) {
				coupons.add((Coupon)component);
			}
			return coupons;
		}
		return null;
	}
	/**
	 * get all Coupons of Company in current session By price
	 * ArrayList<Coupon>	
	 * @param price
	 * @return
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getCompanyCouponsByPrice(double price) throws SQLException, ParseException  {
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.allCouponsByCompanyID, this.getCompanyId());
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(!dbComponents.isEmpty())
		{
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			for (DbComponent component : dbComponents) {
				if(((Coupon)component).getPrice() < price)
					coupons.add((Coupon)component);
			}
			return coupons;
		}
		return null;
	}
	/**
	 * get all Coupons of Customer in current session By price
	 * @param price
	 * @return ArrayList<Coupon>
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getCustomerCouponsByPrice(double price) throws SQLException, ParseException  {
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.allPurchaseCoupons, this.getCustomerId());
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(!dbComponents.isEmpty())
		{
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			for (DbComponent component : dbComponents) {
				if(((Coupon)component).getPrice() < price)
					coupons.add((Coupon)component);
			}
			return coupons;
		}
		return null;
	}
	
	@Override
	public ArrayList<Coupon> getCouponsByType(CouponType type) throws SQLException, ParseException {
		return null;
	}
	/**
	 * get all Coupons of Company in current session By Type
	 * @param type
	 * @return ArrayList<Coupon>
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Override
	public ArrayList<Coupon> getCompanyCouponsByType(CouponType type) throws SQLException, ParseException {
		
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.allCouponsByTypeAndCompID,
				type,getCompanyId());
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(!dbComponents.isEmpty())
		{
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			for (DbComponent component : dbComponents) {
				coupons.add((Coupon)component);
			}
			return coupons;
		}
		return null;
	}
	/**
	 * get all Coupons of Customer in current session By Type
	 * @param type
	 * @return ArrayList<Coupon>
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Coupon> getCustomerCouponsByType(CouponType type) throws SQLException, ParseException {
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.allCouponsByTypeAndCustID,
				type,this.getCustomerId());
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(!dbComponents.isEmpty())
		{
			ArrayList<Coupon> coupons = new ArrayList<Coupon>();
			for (DbComponent component : dbComponents) {
				coupons.add((Coupon)component);
			}
			return coupons;
		}
		return null;
	}
	/**
	 * decrement Amount Of Coupons
	 * @param coupon
	 * @throws SQLException
	 * @throws ParseException
	 */
	public void decrementAmountOfCoupons(Coupon coupon) throws SQLException, ParseException {
		CouponDbOperator couponBuilder = new CouponDbOperator();
		String sqlQuery = String.format(SqlQueries.couponByTitle,coupon.getTitle());
		ArrayList<DbComponent> dbComponents = couponBuilder.buildSelected(sqlQuery) ;
		
		if(!dbComponents.isEmpty())
		{
			Coupon dbCoupon =  (Coupon) dbComponents.get(0);
			if(dbCoupon.getAmount()>0){
				CouponDbOperator couponBuilder2 = new CouponDbOperator();
				String sqlQuery2 = String.format(SqlQueries.setAmount,dbCoupon.getAmount()-1, dbCoupon.getTitle());
				couponBuilder2.buildWriter(sqlQuery2) ;
			}
		}
	}
}

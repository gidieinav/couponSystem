package DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import DbOperator.CustomerDbOperator;
import Java_Beans.Coupon;
import Java_Beans.Customer;
import Java_Beans.DbComponent;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
/**
 * class that has DataBase operations for a Customer.
 * This Class implements {@link CompanyDAO} 
 * @author Gidi
 */
public class CustomerDBDAO implements CustomerDAO {
		
	private  long customerId;
	
	public long getCustomerId() {
			return customerId;
	}

	public void setCustomerId(long customerId) {
			this.customerId = customerId;
	}
	/**
	 * purchase coupon
	 * @param coupon
	 * @throws SQLException
	 */
	@Override
	public void purchaseCoupon(Coupon coupon) throws SQLException  {
		
		CustomerDbOperator customerBuilder = new CustomerDbOperator();	
		String sqlQuery = String.format(SqlQueries.purchaseCouponDB, this.getCustomerId(),coupon.getId());
		customerBuilder.buildWriter(sqlQuery) ;
	 }
	
	public boolean ifCustomerOwnCoupon(Coupon coupon) throws SQLException, ParseException   {
		CustomerDbOperator customerBuilder = new CustomerDbOperator();		
		String sqlQuery = String.format(SqlQueries.ifCustomerOwnCoupon,this.getCustomerId(),coupon.getTitle());
		ArrayList<DbComponent> dbComponents = customerBuilder.buildSelected(sqlQuery);
		if(dbComponents.isEmpty())
			return false;
		else
			return true;
	}
	/**
	 * create customer in DB
	 * @param customer
	 * @throws SQLException
	 * @throws DuplicateException
	 * @throws ParseException
	 */		
	@Override
	public void createCustomer(Customer customer) throws SQLException, DuplicateException, ParseException {
		
		CustomerDbOperator checkBeforeInsertBuilder = new CustomerDbOperator();		
		String sqlQueryGetCustomerByCustName = String.format(SqlQueries.customerByCustName, customer.getCustName());
		ArrayList<DbComponent> dbComponents = checkBeforeInsertBuilder.buildSelected(sqlQueryGetCustomerByCustName);
		
		if(dbComponents.isEmpty())
		{	
			CustomerDbOperator customerBuilder = new CustomerDbOperator();
			String sqlQueryInsertCustomerToDB = String.format(SqlQueries.insertCustomerToDB, customer.getCustName()
					,customer.getPassword());
			customerBuilder.buildWriter(sqlQueryInsertCustomerToDB) ;
		}
		else
		{
			throw new DuplicateException("the customer "+customer.getCustName()+" is exist!");
		}	
	}
	/**
	 * remove customer from DB
	 * @param customer
	 * @throws SQLException
	 * @throws NotAvailableException
	 * @throws ParseException
	 */
	@Override
	public void removeCustomer(Customer customer) throws SQLException, NotAvailableException, ParseException {

		CustomerDbOperator checkBeforeRemoveBuilder = new CustomerDbOperator();		
		String sqlQueryGetCustomerByCustName = String.format(SqlQueries.customerByCustName, customer.getCustName());
		ArrayList<DbComponent> dbComponents = checkBeforeRemoveBuilder.buildSelected(sqlQueryGetCustomerByCustName);
		
		if(!dbComponents.isEmpty())
		{
			CustomerDbOperator customerBuilder1 = new CustomerDbOperator();	
			String sqlQuery1 = String.format(SqlQueries.removeCustomerCouponByCustID, ((Customer)dbComponents.get(0)).getId());
			customerBuilder1.buildWriter(sqlQuery1) ;
			
			CustomerDbOperator customerBuilder2 = new CustomerDbOperator();	
			String sqlQuery2 = String.format(SqlQueries.removeCustomerByID, ((Customer)dbComponents.get(0)).getId());
			customerBuilder2.buildWriter(sqlQuery2) ;
			 
			System.out.println("Customer name " + customer.getCustName() + " has removed.");
		}
		else
		{
			throw new NotAvailableException("The required customer name "+customer.getCustName()
			+" is not found or not exist!");
		}
	}
	/**
	 * update password  of customer
	 * @param customer
	 * @throws SQLException
	 */
	@Override
	public void updateCustomer(Customer customer) throws SQLException {
		CustomerDbOperator customerBuilder = new CustomerDbOperator();	
		String sqlQuery = String.format(SqlQueries.updateCustomer,
				customer.getPassword(),customer.getCustName());
		customerBuilder.buildWriter(sqlQuery) ;
	}
	/**
	 * get specific Customer by id
	 * @param long id
	 * @return Customer
	 * @throws SQLException
	 * @throws NotAvailableException
	 * @throws ParseException
	 */
	@Override
	public Customer getCustomer(long id) throws SQLException, NotAvailableException, ParseException {

		
		CustomerDbOperator customerBuilder = new CustomerDbOperator();	
		String sqlQueryGetcustomerByID = String.format(SqlQueries.customerByID,id);
		ArrayList<DbComponent> dbComponents = customerBuilder.buildSelected(sqlQueryGetcustomerByID);
		
		if(!dbComponents.isEmpty())
		{
			return  (Customer) dbComponents.get(0);
		}
		else{
			throw new NotAvailableException("The customer is not found insert right id !");
		}
	}
	/**
	 * get all Customer in DB
	 * @return ArrayList<Customer>
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Override
	public ArrayList<Customer> getAllCustomers() throws SQLException, ParseException {
		
		CustomerDbOperator customerBuilder = new CustomerDbOperator();	
		String sqlQueryGetAllCustomers = String.format(SqlQueries.allCustomers);
		ArrayList<DbComponent> dbComponents = customerBuilder.buildSelected(sqlQueryGetAllCustomers);
		if(!dbComponents.isEmpty())
		{
			ArrayList<Customer> customers = new ArrayList<Customer>();
			for (DbComponent component : dbComponents) {
				customers.add((Customer)component);
			}
			return  customers;
		}
		return null;
	}

	@Override
	public ArrayList<Coupon> getCoupons() {
		// TODO Auto-generated method stub
		return null;

		
	}
	/**
	 * login
	 * @param String custName
	 * @param String password
	 * @return boolean
	 */	
	@Override
	public boolean login(String custName, String password) throws SQLException, ParseException {
		
		CustomerDbOperator customerBuilder = new CustomerDbOperator();	
		String sqlQuerycustomerloginExecute = String.format(SqlQueries.customerloginExecute, custName,password);
		ArrayList<DbComponent> dbComponents = customerBuilder.buildSelected(sqlQuerycustomerloginExecute);
		if(!dbComponents.isEmpty())
		{
			setCustomerId(((Customer)dbComponents.get(0)).getId());
			return  true;
		}
		return false;
	}
}

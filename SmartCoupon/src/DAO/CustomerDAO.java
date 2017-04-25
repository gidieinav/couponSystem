package DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import Java_Beans.Coupon;
import Java_Beans.Customer;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
/**
 * Interface class that has DataBase operations for a customer.
 * @author Gidi
 *
 */
public interface CustomerDAO {
	public void purchaseCoupon(Coupon coupon) throws SQLException;
	public void createCustomer(Customer customer) throws SQLException, DuplicateException, ParseException;
	public void removeCustomer(Customer customer) throws SQLException, NotAvailableException, ParseException;
	public void updateCustomer(Customer customer) throws SQLException;
	public Customer getCustomer(long id) throws SQLException, NotAvailableException, ParseException;
	public ArrayList<Customer> getAllCustomers() throws SQLException, ParseException;
	public ArrayList<Coupon> getCoupons() throws SQLException;
	public boolean login(String custName, String password) throws SQLException, ParseException;
	
	

}

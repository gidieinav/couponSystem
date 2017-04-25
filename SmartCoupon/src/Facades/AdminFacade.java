package Facades;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import DAO.CompanyDBDAO;
import DAO.CustomerDBDAO;
import Java_Beans.Company;
import Java_Beans.Customer;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;

/**
 * Class Facade managing the actions of the administrator
 * This Class implements {@link CouponClientFacade} 
 * @author Gidi
 */

public class AdminFacade implements CouponClientFacade {
	
	private CustomerDBDAO customerDBDAO = new CustomerDBDAO();
	private CompanyDBDAO  companyDBDAO = new CompanyDBDAO();
	
	final private String name = "admin";
	final private String password = "1234";
	/**
	 * Constructor
	 */
	public AdminFacade() {
		super();
	}
	/**
	 * login
	 * @param String name
	 * @param String password
	 * @return CouponClientFacade
	 */
	@Override
	public CouponClientFacade login(String name, String password) {
		if(name.equals(this.name)  && password.equals(this.password)) 
			return this;
		else return null;
	}
	/**
	 * create company in DB
	 * @param company 
	 * @throws SQLException
	 * @throws DuplicateException
	 * @throws ParseException
	 */
	public void createCompany(Company company) throws SQLException, DuplicateException, ParseException  {
			companyDBDAO.createCompany(company);
	}
	/**
	 * remove Company from DB
	 * @param company
	 * @throws SQLException
	 * @throws NotAvailableException
	 * @throws ParseException
	 */
	public void removeCompany(Company company) throws SQLException, NotAvailableException, ParseException  {
			companyDBDAO.removeCompany(company);
	}
	/**
	 * update password and email of company
	 * @param company
	 * @throws SQLException
	 */
	public void updateCompany(Company company) throws SQLException  {
			companyDBDAO.updateCompany(company);
	}
	/**
	 * get specific Company by id
	 * @param company
	 * @return Company
	 * @throws SQLException
	 * @throws ParseException
	 */
	public Company getCompany(Company company) throws SQLException, ParseException  {
			return companyDBDAO.getCompany(company.getId());
	}
	/**
	 * get all companies in DB
	 * @return ArrayList<Company>
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Company> getAllCompanies() throws SQLException, ParseException{
			return companyDBDAO.getAllCompanies();
	}
	/**
	 * create customer in DB
	 * @param customer
	 * @throws SQLException
	 * @throws DuplicateException
	 * @throws ParseException
	 */
	public void createCustomer(Customer customer) throws SQLException, DuplicateException, ParseException  {
			customerDBDAO.createCustomer(customer);
	}
	/**
	 * remove customer from DB
	 * @param customer
	 * @throws SQLException
	 * @throws NotAvailableException
	 * @throws ParseException
	 */
	public void removeCustomer(Customer customer) throws SQLException, NotAvailableException, ParseException  {
			customerDBDAO.removeCustomer(customer);
	}
	/**
	 * update password  of customer
	 * @param customer
	 * @throws SQLException
	 */
	public void updateCustomer(Customer customer) throws SQLException  {
			customerDBDAO.updateCustomer(customer);	
	}
	/**
	 * get specific Customer by id
	 * @param customer
	 * @return Customer
	 * @throws SQLException
	 * @throws NotAvailableException
	 * @throws ParseException
	 */
	public Customer getCustomer(Customer customer) throws SQLException, NotAvailableException, ParseException  {
				return customerDBDAO.getCustomer(customer.getId());
	}
	/**
	 * get all Customer in DB
	 * @return ArrayList<Customer>
	 * @throws SQLException
	 * @throws ParseException
	 */
	public ArrayList<Customer> getAllCustomers() throws SQLException, ParseException{
			return customerDBDAO.getAllCustomers();
	}
	
}

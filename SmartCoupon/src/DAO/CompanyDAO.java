package DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import Java_Beans.Company;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
/**
 * Interface class that has DataBase operations for a company.
 * @author Gidi
 *
 */
public interface CompanyDAO {
	
	public void createCompany(Company company) throws SQLException, DuplicateException, ParseException;
	public void removeCompany(Company company) throws SQLException, NotAvailableException, ParseException;
	public void updateCompany(Company company) throws SQLException;
	public Company getCompany(long id) throws SQLException, ParseException;
	public ArrayList<Company> getAllCompanies() throws SQLException, ParseException;
	public boolean login(String compName, String password) throws SQLException, ParseException;
}

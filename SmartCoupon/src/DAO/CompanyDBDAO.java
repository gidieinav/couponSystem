package DAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import DbOperator.CompanyDbOperator;
import Facades.CouponClientFacade;
import Java_Beans.Company;
import Java_Beans.DbComponent;
import customExceptions.DuplicateException;
import customExceptions.NotAvailableException;
/**
 * class that has DataBase operations for a company.
 * This Class implements {@link CompanyDAO} 
 * @author Gidi
 */
public class CompanyDBDAO implements CompanyDAO {
	
	private  long companyId;
	
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	/**
	 * create company in DB
	 * @param company 
	 * @throws SQLException
	 * @throws DuplicateException
	 * @throws ParseException
	 */
	@Override
	public void createCompany(Company company) throws SQLException, DuplicateException, ParseException {
		
		CompanyDbOperator checkBeforeInsertBuilder = new CompanyDbOperator();		
		String sqlQueryGetCompanyByName = String.format(SqlQueries.getCompanyByName, company.getCompName());
		ArrayList<DbComponent> dbComponents = checkBeforeInsertBuilder.buildSelected(sqlQueryGetCompanyByName);
		
		if(dbComponents.isEmpty())
		{	
			CompanyDbOperator companyBuilder = new CompanyDbOperator();
			String sqlQueryInsertCompanyToDB = String.format(SqlQueries.insertCompanyToDB, company.getCompName(),company.getPassword(),company.getEmail());
			companyBuilder.buildWriter(sqlQueryInsertCompanyToDB) ;
		}
		else
		{
			throw new DuplicateException("the company "+ company.getCompName()+" is alredy exist!");
		}
	}
	/**
	 * remove Company from DB
	 * @param company
	 * @throws SQLException
	 * @throws NotAvailableException
	 * @throws ParseException
	 */
	@Override
	public void removeCompany(Company company) throws SQLException, NotAvailableException, ParseException {
		CompanyDbOperator checkBeforeRemoveBuilder = new CompanyDbOperator();		
		String sqlQueryGetCompanyByName = String.format(SqlQueries.getCompanyByName, company.getCompName());
		ArrayList<DbComponent> dbComponents = checkBeforeRemoveBuilder.buildSelected(sqlQueryGetCompanyByName);
		
		if(!dbComponents.isEmpty())
		{
		
			CompanyDbOperator companyBuilder1 = new CompanyDbOperator();
			String sqlQuery1 = String.format(SqlQueries.removeCustomerCouponByCompID, ((Company)dbComponents.get(0)).getId());
			companyBuilder1.buildWriter(sqlQuery1) ;
			 
			CompanyDbOperator companyBuilder2 = new CompanyDbOperator();
			String sqlQuery2 = String.format(SqlQueries.removeCouponsByCompID, ((Company)dbComponents.get(0)).getId());
			companyBuilder2.buildWriter(sqlQuery2) ;
			
			CompanyDbOperator companyBuilder3 = new CompanyDbOperator();
			String sqlQuery3 = String.format(SqlQueries.removeCompanyCouponByCompID, ((Company)dbComponents.get(0)).getId());
			companyBuilder3.buildWriter(sqlQuery3) ;
			
			CompanyDbOperator companyBuilder4 = new CompanyDbOperator();
			String sqlQuery4 = String.format(SqlQueries.removeCompanyByName, ((Company)dbComponents.get(0)).getCompName());
			companyBuilder4.buildWriter(sqlQuery4) ;
			
			System.out.println("The company  "+company.getCompName()+"  has removed.");
			 
		}
		else
		{
			throw new NotAvailableException("The required company name "+company.getCompName()+
					 " is not exist!");
		}

	}
	/**
	 * update password and email of company
	 * @param company
	 * @throws SQLException
	 */
	@Override
	public void updateCompany(Company company) throws SQLException {
		
		CompanyDbOperator companyBuilder = new CompanyDbOperator();
		String sqlQuery = String.format(SqlQueries.updateCompany,company.getPassword(),company.getEmail(),company.getCompName());
		companyBuilder.buildWriter(sqlQuery) ;
	}
	/**
	 * get specific Company by id
	 * @param long id
	 * @return Company
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Override
	public Company getCompany(long id) throws SQLException, ParseException {
		CompanyDbOperator companyBuilder = new CompanyDbOperator();		
		String sqlQueryGetCmpanyByID = String.format(SqlQueries.getCmpanyByID,id);
		ArrayList<DbComponent> dbComponents = companyBuilder.buildSelected(sqlQueryGetCmpanyByID);
		
		if(!dbComponents.isEmpty())
		{
			return  (Company) dbComponents.get(0);
		}
		return null;
	}
	/**
	 * get all companies in DB
	 * @return ArrayList<Company>
	 * @throws SQLException
	 * @throws ParseException
	 */
	@Override
	public ArrayList<Company> getAllCompanies() throws SQLException, ParseException {
	
		CompanyDbOperator companyBuilder = new CompanyDbOperator();		
		String sqlQueryGetallCompanies = String.format(SqlQueries.allCompanies);
		ArrayList<DbComponent> dbComponents = companyBuilder.buildSelected(sqlQueryGetallCompanies);
		
		if(!dbComponents.isEmpty())
		{
			ArrayList<Company> companies = new ArrayList<Company>();
			for (DbComponent component : dbComponents) {
				companies.add((Company)component);
			}
			return  companies;
		}
		return null;
	}
	/**
	 * login
	 * @param String compName
	 * @param String password
	 * @return boolean
	 */	
	@Override
	public boolean login(String compName, String password) throws SQLException, ParseException {
		CompanyDbOperator companyBuilder = new CompanyDbOperator();		
		String sqlQueryloginExecut = String.format(SqlQueries.loginExecut,compName,password);
		ArrayList<DbComponent> dbComponents = companyBuilder.buildSelected(sqlQueryloginExecut);
		
		if(!dbComponents.isEmpty())
		{
			setCompanyId(((Company)dbComponents.get(0)).getId());
			return  true;
		}
		return false;
	}

}

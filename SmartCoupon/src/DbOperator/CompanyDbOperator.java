package DbOperator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Java_Beans.Company;
import Java_Beans.DbComponent;
/**
 * Class  that handling repeat actions on DB for Company
 * @author Gidi
 */
public class CompanyDbOperator extends DbOperatorBase
{
	/**
	 * Override runSelectedQueryAndBuildArrayListComponent
	 */
	@Override
	public ArrayList<DbComponent> runSelectedQueryAndBuildArrayListComponent(Statement stmt, String query) throws SQLException {
		
		ArrayList<DbComponent> companies = new ArrayList<DbComponent>();
		
		ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Company company = new Company();
		    	 company.setId(rs.getLong("ID")); 
		    	 company.setCompName(rs.getString("COMP_NAME"));
		    	 company.setPassword(rs.getString("PASSWORD"));
		    	 company.setEmail(rs.getString("EMAIL"));
		    	 companies.add(company);
		        }
		
		
		return companies;
	}


}
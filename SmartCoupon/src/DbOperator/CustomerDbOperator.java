package DbOperator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Java_Beans.Customer;
import Java_Beans.DbComponent;

/**
 * Class  that handling repeat actions on DB for Customer
 * @author Gidi
 */

public class CustomerDbOperator extends DbOperatorBase
{	
	/**
	 * Override runSelectedQueryAndBuildArrayListComponent
	 */
	@Override
	public ArrayList<DbComponent> runSelectedQueryAndBuildArrayListComponent(Statement stmt, String query) throws SQLException {
		
		ArrayList<DbComponent> customers = new ArrayList<DbComponent>();
		
		ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getLong("ID")); 
				customer.setCustName(rs.getString("CUST_NAME"));
				customer.setPassword(rs.getString("PASSWORD"));
				customers.add(customer);
		        }
		return customers;

	}
}
package Facades;

import java.sql.SQLException;
import java.text.ParseException;
/**
 * Interface class that has the login method.
 * @author Gidi
 *
 */

public interface CouponClientFacade {
	/**
	 * login
	 * @param name
	 * @param password
	 * @return {@link CouponClientFacade} 
	 * @throws SQLException
	 * @throws ParseException
	 */
	public CouponClientFacade login(String name, String password) throws SQLException, ParseException;

}

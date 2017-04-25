package Facades;

import java.sql.SQLException;
import java.text.ParseException;
import DailyProcesses.DailyCouponExpirationTask;
/**
 * This class is a singleton
 * It is the engine system for this project
 *  Hence entrance to the system.
 *  Hence the system's closing.
 * The {@link DailyCouponExpirationTask} is operated from this class
 * @author Gidi
 *
 */

public class CouponSystem {
	
	
	DailyCouponExpirationTask dailyCouponExpirationTask = new DailyCouponExpirationTask();
	
	Thread task = new Thread(dailyCouponExpirationTask);
	private static CouponSystem instance = null;
	
	private CouponSystem()
	{
		task.setDaemon(true);

		task.start(); 
	}
	/**
	 *  This method provide instance of the couponSystem
	 * @return
	 */
	public static synchronized CouponSystem getInstance()
	{
		if (instance == null)
		{
			instance = new CouponSystem();
		}
		
		return instance;
	}
	/**
	 * Performs logins for the system for all Client types 
	 * if success return a custom Facade to the client type else null
	 * @param name
	 * @param password
	 * @param type
	 * @return CouponClientFacade
	 * @throws SQLException
	 * @throws ParseException
	 */
	public CouponClientFacade login(String name, String password, ClientType type) throws SQLException, ParseException{
		switch (type) {
        case ADMIN   : return  new AdminFacade().login(name, password);
        case CUSTOMER: return new CustomerFacade().login(name, password);
        case COMPANY : return new CompanyFacade().login(name, password); 
        default      : return null;
		}
	}
}

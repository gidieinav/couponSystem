package DailyProcesses;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import DAO.CouponDBDAO;
import Java_Beans.Coupon;
import customExceptions.NotAvailableException;
/**
 * This class is implements Runnable
 * It's a thread that run once a day and delete expired Coupon from the DB.
 *  @author Gidi
 *
 */
public class DailyCouponExpirationTask implements Runnable{

	CouponDBDAO coupDb = new CouponDBDAO();
	
	private boolean runner = true;

	public boolean isRunner() {
		return runner;
	}

	public void setRunner(boolean runner) {
		this.runner = runner;
	}
	/**
	 * override of run method
	 */
	@Override
	public void run() {
		
			
		while(runner){
			
		ArrayList<Coupon> allCoupons = new ArrayList<>();

		Date endDate = new Date();
		endDate.getTime();
			try {
				allCoupons.addAll(coupDb.getAllCoupons());
			} catch (SQLException | ParseException e) {
				System.err.println(e.getMessage());
			}
		
		
		for(int i=0; i<allCoupons.size(); i++){
			
		if(allCoupons.get(i).getEndDate().before(endDate)){
			try {
				coupDb.removeCoupon(allCoupons.get(i));
			} catch (SQLException | NotAvailableException | ParseException e) {
				System.err.println(e.getMessage());
			}
		}
	}
		
		 System.out.println("DailyCouponExpirationTask done!");
		 
		 try {
			 TimeUnit.DAYS.sleep(1);
		     }
		     catch (InterruptedException e) {
			 System.err.println(e.getMessage());
		}
	   }
	  }
	}



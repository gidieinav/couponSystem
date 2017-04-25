package customExceptions;

/**
 * this class assigned to throw {@link Exception}
 * when the coupon purchase progress failed for out of stock or expired date
 *  reason.
 *  @author Gidi
 */
public class RanOutOfStockException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * this constructor using Exception super class
	 * method to send out message and cause exception. 
	 */
	public RanOutOfStockException (String message){
		
		super( message);
	}
	
}

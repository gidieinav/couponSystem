package customExceptions;

/**
 * this class assigned to throw {@link Exception}
 * when the coupon purchase progress failed for some reason failed.
 * @author Gidi
 */
public class PurchaseFailedException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * this constructor using Exception super class
	 * method to send out message and cause exception. 
	 */
	public PurchaseFailedException (String message){
		
		super( message);
	}

}

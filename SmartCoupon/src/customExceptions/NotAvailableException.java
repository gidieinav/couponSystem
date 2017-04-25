package customExceptions;
/**
 * this class assigned to throw {@link Exception}
 * when the specific requesting object not found in the DB.
 * @author Gidi
 */
public class NotAvailableException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * this constructor using Exception super class
	 * method to send out message and cause exception. 
	 */
	public NotAvailableException (String message){
		
		super( message);
	
	}


}

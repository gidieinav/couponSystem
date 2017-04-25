package customExceptions;

/**
 * this class assigned to throw {@link Exception}
 * when the specific requesting object already exist in the DB.
 * @author Gidi
 */
public class DuplicateException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * this constructor using Exception super class
	 * method to send out message and cause exception. 
	 */
	public DuplicateException (String message){
		
		super( message);
	
	}

}

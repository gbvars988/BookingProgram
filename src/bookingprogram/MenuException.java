package bookingprogram;

/** 
 * Class created for custom exceptions involving
 * user entering input outside of allowed range or
 * null input
 */
public class MenuException extends Exception{
	
	public MenuException() {
		super();
	}

	public MenuException(String errorMessage) {
		super(errorMessage);
	}

}
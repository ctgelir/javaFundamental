package cihat.main.adt;

/**
 * A runtime exception class which emphasizes a stack is Empty
 * @author Cihat Gelir cihatgelir35@gmail.com
 *
 */
public class EmptyStackException extends RuntimeException  {
	
	private static final long serialVersionUID = 1L;
	
	public EmptyStackException(String message) {
		super(message);
	}
}

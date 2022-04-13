package cihat.main.adt;
/**
 * A runtime exception class which emphasizes a queue is Empty
 * @author Cihat Gelir cihatgelir35@gmail.com
 *
 */
public class EmptyQueueException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmptyQueueException(String message) {
		super(message);
	}
}

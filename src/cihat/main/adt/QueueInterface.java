package cihat.main.adt;

/**
 * An interface that describes the operations of a queue of objects
 * @author Cihat Gelir cihatgelir35@gmail.com
 * 
 * Comments taken from Data Structures and Abstractions with Java 4th Edition by Frank M. Carrano
 */
public interface QueueInterface<T>
{
	/** Adds a new entry to the back of this queue.
		@param newEntry An object to be added. 
		@throws IllegalStateException if newEntry is null.*/
	public void enqueue(T newEntry);
	
	/** Removes and returns the entry at the front of this queue.
		@return The object at the front of the queue.
		@throws EmptyQueueException if the queue is empty before the operation.*/
	public T dequeue();
	
	/** Retrieves the entry at the front of this queue.
		@return The object at the front of the queue.
		@throws EmptyQueueException if the queue is empty. */
	public T getFront();
	
	/** Detects whether this queue is empty.
		@return True if the queue is empty, or false otherwise. */
	public boolean isEmpty();
	
	/** Removes all entries from this queue. */
	public void clear();
}
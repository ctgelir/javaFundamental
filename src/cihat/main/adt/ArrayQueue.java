package cihat.main.adt;

import java.util.Arrays;
/**
 * A class that implements a queue of objects by using an array.
 * @author Cihat Gelir cihatgelir35@gmail.com
 * 
 * Comments mainly taken from Data Structures and Abstractions with Java 4th Edition by Frank M. Carrano
 */
public class ArrayQueue<T> implements QueueInterface<T> {
	private T[] queue; // one location is unused
	
	private int frontIndex;
	private int backIndex;
	
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	public ArrayQueue(int desiredCapacity) {
		checkCapacity(desiredCapacity);
		
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[desiredCapacity + 1];
		queue = tempQueue;
		
		frontIndex = 0;
		backIndex = desiredCapacity;
	}
	
	/** Adds a new entry to the back of this queue.
		@param newEntry An object to be added. */
	public void enqueue(T newEntry) {
		if(newEntry == null)
			throw new IllegalStateException("newEntry is null");
		ensureCapacity();
		backIndex = (backIndex+1) % queue.length;
		queue[backIndex] = newEntry;
	}
	
	/** Removes and returns the entry at the front of this queue.
		@return The object at the front of the queue.
		@throws EmptyQueueException if the queue is empty before the operation.*/
	public T dequeue() {
		if(isEmpty())
			throw new EmptyQueueException("Queue is empty.");
		else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1)% queue.length;
			return front;
		}
	}
	
	/** Retrieves the entry at the front of this queue.
		@return The object at the front of the queue.
		@throws EmptyQueueException if the queue is empty. */
	public T getFront() {
		if(isEmpty()) 
			throw new EmptyQueueException("Queue is empty.");
		else
			return queue[frontIndex];
	}
	
	/** Removes all entries from this queue. */
	public void clear() {
		while(!isEmpty())
			dequeue();
	}
	
	/** Detects whether this queue is empty.
		@return True if the queue is empty, or false otherwise. */
	public boolean isEmpty() {
		return frontIndex == (backIndex + 1) % queue.length;
	}
	
	
	/** Throws an exception if the client requests a capacity that exceeds limitations.*/
	private void checkCapacity(int desiredCapacity) {
		if (desiredCapacity > MAX_CAPACITY || desiredCapacity <= 0) {
			throw new IllegalStateException("Attempt to create a queue " + 
											"whose capacity exceeds limitations.");
		}
	}
	
	/** if stack is full it doubles the size of stack. */
	private void ensureCapacity() {
		if (isFull()) {
			int newLength = 2 * queue.length;
			checkCapacity(newLength - 1) ;
			queue = Arrays.copyOf(queue, newLength);
		}
	}

	/** Detects whether this queue is full.
	    @return True if the queue is full, or false otherwise*/
	private boolean isFull() {
		return frontIndex == ((backIndex + 2 ) % queue.length);
	}
	
	
}
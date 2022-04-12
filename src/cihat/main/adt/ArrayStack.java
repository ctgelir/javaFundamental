package cihat.main.adt;

import java.util.Arrays;

/**
 * A class of Stack whose entries are stored in a fixed-size array.
 * @author Cihat Gelir cihatgelir35@gmail.com
 *
 * Comments taken from Data Structures and Abstractions with Java 4th Edition by Frank M. Carrano
 */
public class ArrayStack<T> implements StackInterface<T>{
	private T[] stack;
	public int topIndex;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayStack() {
		this(DEFAULT_CAPACITY);
	}
	
	
	public ArrayStack(int desiredCapacity) {
		checkCapacity(desiredCapacity);
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[desiredCapacity];
		stack = tempStack;
		topIndex = -1;
	}
	
	/** Adds a new entry to the top of this stack.
		@param newEntry An object to be added to the stack.
		@exception IllegalStateException If newEntry is null */
	public void push(T newEntry) {
		if(newEntry == null) {
			throw new IllegalStateException("New Entry is null.");
		}
		ensureCapacity();
		stack[topIndex + 1] = newEntry;
		topIndex++;
	}
	
	
	/** Retrieves this stack's top entry.
		@return The object at the top of the stack.
		@throws EmptyStackException if the stack is empty.*/
	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException("Stack is empty");
		}
		return stack[topIndex];
	}
	
	/** Removes and returns this stack's top entry.
		@return The object at the top of the stack.
		@throws EmptyStackException if the stack is empty before the operation. */
	public T pop() {
		if(isEmpty()) {
			throw new EmptyStackException("Stack is empty");
		}
		T top = stack[topIndex];
		stack[topIndex] = null;
		topIndex--;
		return top;
	}

	
	/** Detects whether this stack is empty.
		@return True if the stack is empty. */
	public boolean isEmpty() {
		return topIndex == -1;
	}
	

	/** Removes all entries from this stack. */
	public void clear() {
		while(!isEmpty()) {
			pop();
		}
	}
	
	/** Throws an exception if the client requests a capacity that exceeds limitations.*/
	private void checkCapacity(int desiredCapacity) {
		if (desiredCapacity > MAX_CAPACITY || desiredCapacity < 0) {
			throw new IllegalStateException("Attempt to create a bag" + 
											"whose capacity exceeds limitations.");
		}
	}
	
	/** if stack is full it doubles the size of stack. */
	private void ensureCapacity() {
		if (topIndex == stack.length -1) {
			int newLength = 2 * stack.length;
			checkCapacity(newLength);
			stack = Arrays.copyOf(stack, newLength);
		}
	}
}

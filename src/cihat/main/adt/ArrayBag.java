package cihat.main.adt;

import java.util.Arrays;

/**
 * A class of bags whose entries are stored in a fixed-size array.
 * @author Cihat Gelir cihatgelir35@gmail.com
 * 
 * Comments taken from Data Structures and Abstractions with Java 4th Edition by Frank M. Carrano
 */
public class ArrayBag<T> implements BagInterface<T>{
	
	private T[] bag;
	private int numberOfEntries;
	private static final int DEFAULT_CAPACITY = 30;
	private static final int MAX_CAPACITY = 10000;
	
	/** Creates an empty bag with initial capacity is DEFAULT_CAPACITY */
	public ArrayBag() {
		this(DEFAULT_CAPACITY);
	}
	
	
	/** Creates an empty bag having a given initial capacity.
	    @param capacity The desired integer capacity.
	    @exception IllegalStateException if desiredCapacity exceeds limitations*/
	public ArrayBag(int desiredCapacity) {
		checkCapacity(desiredCapacity);
		@SuppressWarnings("unchecked")
		T[] tempBag = (T[]) new Object[desiredCapacity];
		bag = tempBag;
		numberOfEntries = 0;
	}
	
	
	/**{@inheritDoc}*/
	public int getCurrentSize() {	return numberOfEntries;}

	
	/**{@inheritDoc}*/
	public boolean isEmpty() {	return numberOfEntries == 0;}

	
	/**{@inheritDoc}*/
	public void add(T newEntry) {
		checkEntry(newEntry);
		ensureCapacity();
		bag[numberOfEntries] = newEntry;
		numberOfEntries++;
	}
	
	/**{@inheritDoc}*/
	public T remove() {
		T result = null;
		if (numberOfEntries > 0) {
			result = bag[numberOfEntries-1];
			bag[numberOfEntries-1] = null;
			numberOfEntries--;
		}
		return result;
	}

	
	/**{@inheritDoc}*/
	public boolean remove(T anEntry) {
		checkEntry(anEntry);
		int index = getIndexOf(anEntry);
		if(index >= 0) {
			removeEntry(index);
			return true;
		}
		return false;
	}

	
	/**{@inheritDoc}*/
	public void clear() {
		while(!isEmpty())
			remove();		
	}

	
	/**{@inheritDoc}*/
	public int getFrequencyOf(T anEntry) {
		checkEntry(anEntry);
		int counter = 0;
		for (int index = 0; index < numberOfEntries; index++) {
			if (anEntry.equals(bag[index])) {
				counter++;
			}
		}
		return counter;
	}

	
	/**{@inheritDoc}*/
	public boolean contains(T anEntry) {
		checkEntry(anEntry);
		for(int index =0; index < numberOfEntries;index++) {
			if (anEntry.equals(bag[index])) 
				return true;
		}
		return false;
	}


	/**{@inheritDoc}*/
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		for (int index = 0; index < numberOfEntries; index++) {
			result[index] = bag[index];
		}
		return result;
	}
	
	
	// Returns true if the arraybag is full, or false if not.
	private boolean isFull() {	return numberOfEntries >= bag.length;}

	
	// Pre-Condition index >= 0 and index < numberOfEntries
	// Removes the entry at position: index
	private void removeEntry(int index) {
		bag[index] = bag[numberOfEntries-1];
		bag[numberOfEntries-1] = null;
		numberOfEntries--;	
	}

	
	//  Pre-Condition: anEntry is not null
	//  Returns the index of anEntry if it is in the bag else, returns -1 
	private int getIndexOf(T anEntry) {
		for (int index = 0; index < numberOfEntries; index++) {
			if (anEntry.equals(bag[index]))
				return index;
		}
		return -1;
	}

	
	// Throws IllegalStateException if desiredCapacity exceeds limitation.
	private void checkCapacity(int desiredCapacity) {
		if (desiredCapacity > MAX_CAPACITY || desiredCapacity <= 0) {
			throw new IllegalStateException("Attempt to create a bag " +
										"whose capacity exceeds the limitations.");
		}
	}
	
	
	// It doubles the capacity if bag is full.
	private void ensureCapacity() {
		if(isFull()) {
			int newLength = 2 * bag.length;
			bag = Arrays.copyOf(bag, newLength);
		}
	}	
	
	
	// Throws IllegalStateException if anEntry is null.
	private void checkEntry(T anEntry) {
		if (anEntry == null) 
			throw new IllegalStateException("New Entry is null.");
	}
}

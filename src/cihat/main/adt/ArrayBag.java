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
		if (desiredCapacity <= MAX_CAPACITY && desiredCapacity > 0) {
			@SuppressWarnings("unchecked")
			T[] tempBag = (T[]) new Object[desiredCapacity];
			bag = tempBag;
			numberOfEntries = 0;
		} else {
			throw new IllegalStateException("Attempt to create a bag " + 
											"whose capacity exceeds the limitation");
		}
	}
	
	/** Gets the current number of entries in this bag.
    	@return The integer number of entries currently in the bag.*/
	public int getCurrentSize() {
		return numberOfEntries;
	}

	/** Sees whether this bag is empty.
    	@return True if the bag is empty, or false if not. */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	}

	/** Adds a new entry to this bag. If bag is full it doubles capacity.
    	@param newEntry The object to be added as a new entry.
    	@exception IllegalStateException if newEntry is null. */
	public void add(T newEntry) {
		if (newEntry == null) {	throw new IllegalStateException("New Entry is null.");}
		if (isArrayFull()) {
			doubleCapacity();
		} else {
			bag[numberOfEntries] = newEntry;
			numberOfEntries++;
		}
		
	}
	
	/** Removes one unspecified entry from this bag, if possible.
		@return Either the removed entry, if the removal was successful, or null. */
	public T remove() {
		T result = null;
		if (numberOfEntries > 0) {
			result = bag[numberOfEntries-1];
			bag[numberOfEntries-1] = null;
			numberOfEntries--;
		}
		return result;
	}

	
	/** Removes one occurrence of a given entry from this bag, if possible.
		@param anEntry The entry to be removed.
		@return True if the removal was successful, or false if not. */
	@Override
	public boolean remove(T anEntry) {
		if (anEntry == null) {	throw new IllegalStateException("New Entry is null.");}
		int index = getIndexOf(anEntry);
		if(index >= 0) {
			removeEntry(index);
			return true;
		}
		return false;
	}

	/** Removes all entries from this bag. */
	public void clear() {
		while(!isEmpty()) {
			remove();
		}
		
	}

	/** Counts the number of times a given entry appears in this bag.
		@param anEntry The entry to be counted.
		@exception IllegalStateException if newEntry is null
		@return The number of times anEntry appears in the bag. */
	public int getFrequencyOf(T anEntry) {
		if(anEntry == null) {	throw new IllegalStateException("New Entry is null.");}
		int counter = 0;
		for (int index = 0; index < numberOfEntries; index++) {
			if (anEntry.equals(bag[index])) {
				counter++;
			}
		}
		return counter;
	}

	
	/** Tests whether this bag contains a given entry.
		@param anEntry The entry to locate.
		@return True if the bag contains anEntry, or false if not. */
	public boolean contains(T anEntry) {
		if (anEntry == null) {
			throw new IllegalStateException("New Entry is null.");
		}
		boolean found = false;
		int index = 0;
		while (!found && index < numberOfEntries) {
			if (anEntry.equals(bag[index])) {
				found = true;
			}
			index++;
		}
		return found;
	}


	/** Retrieves all entries that are in this bag.
		@return A newly allocated array of all the entries in the bag.
		Note: If the bag is empty, the returned array is empty. */
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		for (int index = 0; index < numberOfEntries; index++) {
			result[index] = bag[index];
		}
		return result;
	}
	
	
	/** Returns true if the arraybag is full, or false if not */
	private boolean isArrayFull() {
		return numberOfEntries >= bag.length;
	}

	/** Pre-Condition index >= 0 and index < numberOfEntries
	    removes the entry at position: index
	    @param index */
	private void removeEntry(int index) {
		bag[index] = bag[numberOfEntries-1];
		bag[numberOfEntries-1] = null;
		numberOfEntries--;
		
	}

	/**	Pre-Condition: anEntry is not null
	   	@param anEntry 
	   	returns index of anEntry if it is in the bag else, returns -1 */
	private int getIndexOf(T anEntry) {
		for (int index = 0; index < numberOfEntries; index++) {
			if (anEntry.equals(bag[index])) {
				return index;
			}
		}
		return -1;
		
	}

	/** tries to double capacity.*/
	private void doubleCapacity() {
		int newLength = 2 * bag.length;
		if (newLength > MAX_CAPACITY) {
			throw new IllegalStateException("Attempt to create a bag " + 
					"whose capacity exceeds the limitation");
		} else {
			bag = Arrays.copyOf(bag, newLength);
		}
	}	
}

package cihat.main.adt;

/**
 * An interface that describes the operations of a bag of objects
 * @author Cihat Gelir
 * 
 * Comments taken from Data Structures and Abstractions with Java 4th Edition by Frank M. Carrano
 */
public interface BagInterface<T> {
	
	/** Gets the current number of entries in this bag.
	    @return The integer number of entries currently in the bag.*/
	public int getCurrentSize();
	
	
	/** Sees whether this bag is empty.
	    @return True if the bag is empty, or false if not. */
	public boolean isEmpty();
	
	
	/** Adds a new entry to this bag. If bag is full it doubles the capacity.
	    @param newEntry The object to be added as a new entry.
	    @throws IllegalStateException if newEntry is null*/
	public void add(T newEntry);
	
	
	/** Removes one unspecified entry from this bag, if possible.
		@return Either the removed entry, if the removal
		was successful, or null. */
	public T remove();
	
	
	/** Removes one occurrence of a given entry from this bag, if possible.
		@param anEntry The entry to be removed.
		@throws IllegalStateException if newEntry is null
		@return True if the removal was successful, or false if not. */
	public boolean remove (T anEntry);
	
	
	/** Removes all entries from this bag. */
	public void clear();
	
	
	/** Counts the number of times a given entry appears in this bag.
		@param anEntry The entry to be counted.
		@throws IllegalStateException if newEntry is null
		@return The number of times anEntry appears in the bag. */
	public int getFrequencyOf(T anEntry);
	
	
	/** Tests whether this bag contains a given entry.
		@param anEntry The entry to locate.
		@throws IllegalStateException if newEntry is null
		@return True if the bag contains anEntry, or false if not. 
		*/
	public boolean contains(T anEntry);
	
	
	/** Retrieves all entries that are in this bag.
		@return A newly allocated array of all the entries in the bag.
		Note: If the bag is empty, the returned array is empty. */
	public T[] toArray();
}

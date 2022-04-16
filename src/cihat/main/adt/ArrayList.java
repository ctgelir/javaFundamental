package cihat.main.adt;

import java.util.Arrays;

/** A class that implements a list of objects by using an array.
    Entries in a list have positions that begin with 1.
    Duplicate entries are allowed.
    Null entries are are allowed.
    @author Cihat Gelir cihatgelir35@gmail.com
    Comments taken from Data Structures and Abstractions with Java 4th Edition by Frank M. Carrano.*/
public class ArrayList<T> implements ListInterface<T> {
    private T[] list;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    /** Creates a List with given capacity*/
    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }
    /** Creates a list with given capacity
     *  @throws IllegalStateException if the client requests a capacity that exceeds limitations.*/
    public ArrayList(int desiredCapacity){
        checkCapacity(desiredCapacity);
        @SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Object[desiredCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
    }

    /** Adds a new entry to the end of this list.
        Entries currently in the list are unaffected.
        The list’s size is increased by 1.
        If the list is full then it makes list's size double.
        @param newEntry The object to be added as a new entry.*/
    public void add(T newEntry){
        ensureCapacity();
        list[numberOfEntries +1] = newEntry;
        numberOfEntries++;
    }

    /** Adds a new entry at a specified position within this list.
        Entries originally at and above the specified position
        are at the next higher position within the list.
        The list’s size is increased by 1.
        If the list is full then it makes list's size double.
        @param newPosition An integer that specifies the desired position of the new entry.
        @param newEntry The object to be added as a new entry.
        @throws IndexOutOfBoundsException if either
        newPosition < 1 or newPosition > getLength() + 1.*/
    public void add(int newPosition, T newEntry){
        ensureCapacity();
        if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1)){
            if (newPosition <= numberOfEntries){
                makeRoom(newPosition);
            }
            list[newPosition] = newEntry;
            numberOfEntries++;
        }
        else
            throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
    }

    /** Removes the entry at a given position from this list.
     Entries originally at positions higher than the given
     position are at the next lower position within the list,
     and the list’s size is decreased by 1.
     @param givenPosition An integer that indicates the position of
     the entry to be removed.
     @return A reference to the removed entry.
     @throws IndexOutOfBoundsException if either
     givenPosition < 1 or givenPosition > getLength(). */
    public T remove(int givenPosition){
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
            T result = list[givenPosition];
            if(givenPosition < numberOfEntries) {
                removeGap(givenPosition);
            }
            numberOfEntries--;
            return result;
        }
        else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    }

    /** Removes all entries from this list. */
    public void clear() {
        while(!isEmpty()){
            remove(numberOfEntries);
            numberOfEntries--;
        }
    }

    /** Replaces the entry at a given position in this list.
        @param givenPosition An integer that indicates the position of the entry to be replaced.
        @param newEntry The object that will replace the entry at the position givenPosition.
        @return The original entry that was replaced.
        @throws IndexOutOfBoundsException if either
        givenPosition < 1 or givenPosition > getLength(). */
    public T replace(int givenPosition, T newEntry){
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
            T originalEntry = list[givenPosition];
            list[givenPosition] = newEntry;
            return originalEntry;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    }

    /** Retrieves the entry at a given position in this list.
        @param givenPosition An integer that indicates the position of the desired entry.
        @return A reference to the indicated entry.
        @throws IndexOutOfBoundsException if either
        givenPosition < 1 or givenPosition > getLength(). */
    public T getEntry(int givenPosition){
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
            return list[givenPosition];
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    }

    /** Retrieves all entries that are in this list in the order in which they occur in the list.
        @return A newly allocated array of all the entries in the list.
        If the list is empty, the returned array is empty. */
    public T[] toArray() {
        @SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
        for(int index = 0; index < numberOfEntries; index++){
            result[index] = list[index+1];
        }
        return result;
    }


    /** Sees whether this list contains a given entry.
        @param anEntry The object that is the desired entry.
        @return True if the list contains anEntry, or false if not. */
    public boolean contains(T anEntry){
        for (int index = 1; index <= numberOfEntries;index++){
            if(anEntry.equals(list[index])){
                return true;
            }
        }
        return false;
    }

    /** Gets the length of this list.
        @return The integer number of entries currently in the list. */
    public int getLength() {
        return numberOfEntries;
    }

    /** Sees whether this list is empty.
        @return True if the list is empty, or false if not. */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }


    // Makes room for a new entry at newPosition.
    // Precondition: 1<= newPosition <= numberOfEntries + 1
    private void makeRoom(int newPosition) {
        int newIndex = newPosition;
        int lastIndex = numberOfEntries;

        for(int index = lastIndex; index >= newIndex; index--){
            list[index + 1] = list[index];
        }
    }


    // Shifts entries that are beyond the entry to be removed to the next lower position.
    // Precondition: 1 <= givenPosition < numberOfEntries;
    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;
        for (int index = removedIndex; index < lastIndex; index++){
            list[index] = list[index+1];

        }
    }

    // if list is full it doubles the size of list.
    private void ensureCapacity() {
        int capacity = list.length -1;
        if (numberOfEntries >= capacity){
            int newLength = list.length*2;
            list = Arrays.copyOf(list, newLength);

        }
    }


    // Throws IllegalStateException if the client requests a capacity that exceeds limitations.
    private void checkCapacity(int desiredCapacity) {
        if (desiredCapacity > MAX_CAPACITY || desiredCapacity < 1){
            throw new IllegalStateException("Attempt to create a list " +
                                            "whose capacity exceeds the limitations.");
        }
    }


}

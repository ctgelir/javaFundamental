package cihat.main.adt;

/** A linked implementation of the ADT list.
 *  @author Cihat Gelir
 *  Comments are mainly taken from Data Structures and Abstractions with Java 4th Edition by Frank M Carrano.
 *  I named this class LList instead of LinkedList to avoid confusion with Java’s class LinkedList 
    in the package java.util. */
public class LList<T> implements ListInterface<T> {
	private Node firstNode;
	private int numberOfEntries;
	
	public LList() {
		initializeDataFields();
	}
	
	
	/**{@inheritDoc}*/
	public void clear() {
		initializeDataFields();
	}
	
	
	/**{@inheritDoc}*/
	public void add(T newEntry) {
		Node newNode = new Node(newEntry);
		if (isEmpty()) {
			firstNode = newNode;
		} else {
			Node lastNode = getNodeAt(numberOfEntries);
			lastNode.next = newNode;
		}
		numberOfEntries++;
	}
	
	
	/**{@inheritDoc}*/
	public void add(int newPosition, T newEntry) {
		if(newPosition >= 1 && newPosition <= numberOfEntries + 1) {
			Node newNode = new Node(newEntry);
			if (newPosition == 1) {
				newNode.next = firstNode;
				firstNode = newNode;
			} else {
				Node nodeBefore = getNodeAt(newPosition - 1);
				Node nodeAfter = nodeBefore.next;
				newNode.next = nodeAfter; 
				nodeBefore.next = newNode;
			}
			numberOfEntries++;
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to add operation.");
		}
	}
	
	
	/**{@inheritDoc}*/
	public boolean isEmpty() {
		return numberOfEntries == 0;	
	}
	
	
	/**{@inheritDoc}*/
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries];
		int index = 0;
		Node currentNode = firstNode;
		while ((index < numberOfEntries) && (currentNode != null)) {
			result[index] = currentNode.data;
			currentNode = currentNode.next;
			index++;
		}
		return result;
	}


	/**{@inheritDoc}*/
	public T remove(int givenPosition) {
		T result = null;
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			if (givenPosition == 1) {
				result = firstNode.data;
				firstNode = firstNode.next;
			} else {
				Node nodeBefore = getNodeAt(givenPosition -1);
				Node nodeToRemove = nodeBefore.next;
				result = nodeToRemove.data;
				Node nodeAfter = nodeToRemove.next;
				nodeBefore.next = nodeAfter;
			}
			numberOfEntries--;
			return result;
		} else {
			throw new IndexOutOfBoundsException("Illegal position given to remove operation");
		}
	}
	
	
	/**{@inheritDoc}*/
	public T replace(int givenPosition, T newEntry) {
		 if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			 Node desiredNode = getNodeAt(givenPosition);
			 T originalEntry = desiredNode.data;
			 desiredNode.data = newEntry;
			 return originalEntry;
		 }
		 else
			 throw new IndexOutOfBoundsException(
			 "Illegal position given to replace operation.");
	} 
	
	
	/**{@inheritDoc}*/
	public T getEntry(int givenPosition) {
		 if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
			 return getNodeAt(givenPosition).data;
		 }
		 else
			 throw new IndexOutOfBoundsException(
			 "Illegal position given to getEntry operation.");
	}
	
	
	/**{@inheritDoc}*/
	public boolean contains(T anEntry) {
		Node currentNode = firstNode;
		while (currentNode != null) {
			if (anEntry == null) {
				if (currentNode.data == null) {	return true;}
			} else {
				if(currentNode.data != null && currentNode.data.equals(anEntry)) { return true;}
			}
			currentNode = currentNode.next;
		}
		return false;
	}
	
	
	/**{@inheritDoc}*/
	public int getLength() {
		return numberOfEntries;
	}

	// Returns node at givenPosition
	// Pre-Condition : given position >= 1 and <= numberOfEntries
	private Node getNodeAt(int givenPosition) {
		Node currentNode = firstNode;
		for(int counter = 1; counter < givenPosition; counter++) {
			currentNode = currentNode.next;
		}
		return currentNode;
	}
	
	
	private void initializeDataFields() {
		firstNode = null;
		numberOfEntries = 0;
	}
	
	
	// Single linked Node.
	private class Node {
		private T data;
		private Node next;
		
		private Node(T dataPortion) {
			this(dataPortion, null);
		}
		
		
		private Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}
	}
}

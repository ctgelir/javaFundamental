package cihat.main.algorithm;

import java.util.Arrays;

/**
 * This class includes implementation of basic sorting algorithms for arrays that have comparable object.
 * @author Cihat Gelir cihatgelir35@gmail.com
 */
public class Sort {
	
	/**
	 * Insertion Sorts the given array in ascending order.
	 * NOTE: The array SHOULDN'T have null elements (throws NullPointerException)
	 * @param <E> element of array must be comparable with itself
	 * @param arr array
	 */
	public static <E extends Comparable<E>> void insertionSort(E[] arr){
		if (arr == null) {
			System.out.println("Null parameter !");
			return;
		}
		if (arr.length == 0) {
			System.out.println("Array is empty.");
			return;
		}
		for (int i = 1; i < arr.length; i++) {
			E key = arr[i];
			int j = i-1;
			while (j >= 0 && arr[j].compareTo(key)> 0) {
				arr[j+1] = arr[j];
				j = j-1;
			}
			arr[j+1] = key;
		}
	}
	
	
	/**
	 * Quick Sorts the given array in ascending order.
	 * NOTE: The array SHOULDN'T have null elements (throws NullPointerException)
	 * @param <E> element of array must be comparable with itself
	 * @param arr array
	 * @param low lowest index of array
	 * @param high highest index of array (length - 1)
	 */
	public static <E extends Comparable<E>> void quickSort(E[] arr, int low , int high) {
		if(arr == null) {
			System.out.println("Null parameter !");
			return;
		}
		if(low  < high) {
			int pi = partition(arr,low,high); /* pi is partitioning index */
			quickSort(arr,low,pi-1); /* left of the pivot */
			quickSort(arr,pi+1,high);/* right of the pivot */
		}
	}
	
	/**
	 * This function takes last element as pivot, places the pivot element at its correct position in sorted array, 
	 * and places all smaller (smaller than pivot) to left of pivot and all greater elements to right of pivot.
	 * @param <E> element of array must be comparable with itself
	 * @param arr array
	 * @param low lowest index of array
	 * @param high highest index of array
	 * @return
	 */
	private static <E extends Comparable<E>> int partition(E[]arr, int low, int high) {
		E pivot = arr[high];
		int i = low-1;
		for (int j = low; j < high; j++) {
			if (arr[j].compareTo(pivot) <= 0) {
				i++;
				swap(arr,i,j);
			}
		}
		swap(arr,i+1,high);
		return i+1;
	}
	
	/**
	 * This function switches arr[m] and arr[n]
	 * @param <E>
	 * @param arr arr
	 * @param m index of one element to be switched
	 * @param n index of other one element to be switched
	 */
	private static <E extends Comparable<E>> void swap(E[]arr, int m, int n) {
		E temp = arr[m];
		arr[m] = arr[n];
		arr[n] = temp;
	}

	
	/**
	 * Merge Sorts the given array in ascending order
	 * NOTE: The array SHOULDN'T have null elements (throws NullPointerException)
	 * @param <E> element of array must be comparable with itself
	 * @param arr array
	 */
	public static <E extends Comparable<E>> void mergeSort(E[] arr) {
		if(arr == null) {
			System.out.println("Null parameter !");
			return;
		}
		int arrLength = arr.length;
		if (arrLength < 2) {
			return;
		} 
		int midIndex = arrLength / 2;
		
		E[] leftHalf = Arrays.copyOfRange(arr, 0, midIndex);
		E[] rightHalf = Arrays.copyOfRange(arr, midIndex, arrLength);
		
		mergeSort(leftHalf);
		mergeSort(rightHalf);
		
		merge(arr,leftHalf,rightHalf);
	}
	
	/**
	 * Merges 2 array
	 * @param <E> element of array must be comparable with itself
	 * @param arr array
	 * @param leftHalf left half of the array w.r.t midIndex
	 * @param rightHalf right half of the array w.r.t midIndex
	 */
	private static <E extends Comparable<E>> void merge(E[] arr,E[] leftHalf, E[] rightHalf) {
		int leftSize = leftHalf.length;
		int rightSize = rightHalf.length;
		int i = 0, j = 0, k = 0;
		
		while (i < leftSize && j < rightSize) {
			if(leftHalf[i].compareTo(rightHalf[j]) <= 0) {
				arr[k] = leftHalf[i];
				i++;
			} else {
				arr[k] = rightHalf[j];
				j++;
			}
			k++;
		}
		
		while (i < leftSize) {
			arr[k] = leftHalf[i];
			i++;
			k++;
		}
		
		while(j < rightSize) {
			arr[k] = rightHalf[j];
			j++;
			k++;
		}
	}
	
}
package cihat.main.algorithm;

/**
 * This class includes implementation of basic sorting algorithms for arrays that have comparable object.
 * NOTE: The array shouldn't have null elements (throws NullPointerException)
 * @author Cihat Gelir cihatgelir35@gmail.com
 */
public class Sort {
	/**
	 * Sorts the given array in ascending order.
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
	
	


}
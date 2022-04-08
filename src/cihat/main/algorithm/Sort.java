package cihat.main.algorithm;

import java.util.List;

/**
 * This class includes implementation of basic sorting algorithms for any object that implements list object.
 * NOTE: The list shouldn't have null elements (throws NullPointerException)
 * @author Cihat Gelir cihatgelir35@gmail.com
 */
public class Sort {
	/**
	 * This method sorts the given list in ascending order.
	 * @param <E> element of list must be comparable with itself
	 * @param list collection
	 */
	public static <E extends Comparable<E>> void insertionSort(List<E> list){
		if (list == null) {
			System.out.println("Null parameter !");
			return;
		}
		if (list.size() == 0) {
			System.out.println("List is empty.");
			return;
		}
		for (int i = 1; i < list.size(); i++) {
			E key = list.get(i);
			int j = i-1;
			while (j >= 0 && list.get(j).compareTo(key) > 0) {
				list.set(j + 1, list.get(j));
				j = j-1;
			}
			list.set(j+1, key);
		}
	}
}
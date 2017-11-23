package implementation.algorithm.balaji;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Mohanakrishna,Pradeep,Vinaya,Nikita
 *
 */
public class SetOperations {

	/**
	 * The function will update outList with elements common to both l1 and l2
	 * in sorted order
	 *
	 * @param l1
	 *            : list implementing sorted sets
	 * @param l2
	 *            : list implementing sorted sets
	 * @param outList
	 *            : An empty list
	 */
	public static <T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> outList) {
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		T item1 = next(it1);
		T item2 = next(it2);
		while (item1 != null && item2 != null) {
			if (item1.compareTo(item2) < 0) {
				item1 = next(it1);
			} else if (item1.compareTo(item2) > 0) {
				item2 = next(it2);
			} else { // add to outList only if elements are equal
				outList.add(item1);
				item1 = next(it1);
				item2 = next(it2);
			}
		}
	}

	/**
	 * The function will perform union operation on l1 and l2 and store the
	 * results in outList without any duplicates
	 *
	 * @param l1
	 *            : list implementing sorted sets
	 * @param l2
	 *            : list implementing sorted sets
	 * @param outList
	 *            : An empty list
	 */
	public static <T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList) {
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		T item1 = next(it1);
		T item2 = next(it2);
		while (item1 != null && item2 != null) {
			if (item1.compareTo(item2) < 0) {
				outList.add(item1);
				item1 = next(it1);
			} else if (item1.compareTo(item2) > 0) {
				outList.add(item2);
				item2 = next(it2);
			} else {
				outList.add(item1);
				item1 = next(it1);
				item2 = next(it2);
			}
		}

		// add remaining elements from l1 to the outList
		while (item1 != null) {
			outList.add(item1);
			item1 = next(it1);
		}
		// add remaining elements from l2 to the outList
		while (item2 != null) {
			outList.add(item2);
			item2 = next(it2);
		}
	}

	/**
	 * The function will perform difference operation on l1 and l2 i.e, items in
	 * l1 that are not in l2, and store result in outList in sorted order
	 *
	 * @param l1
	 *            : list implementing sorted sets
	 * @param l2
	 *            : list implementing sorted sets
	 * @param outList
	 *            : An empty list
	 */
	public static <T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList) {
		Iterator<T> it1 = l1.iterator();
		Iterator<T> it2 = l2.iterator();
		T item1 = next(it1);
		T item2 = next(it2);
		while (item1 != null && item2 != null) {
			if (item1.compareTo(item2) < 0) {
				outList.add(item1);
				item1 = next(it1);
			} else if (item1.compareTo(item2) > 0) {
				item2 = next(it2);
			} else {
				item1 = next(it1);
				item2 = next(it2);
			}
		}

		// add remaining elements from l1 to the outList
		while (item1 != null) {
			outList.add(item1);
			item1 = next(it1);
		}
	}

	/**
	 * Helper method for iterating a List
	 *
	 * @param it
	 *            : Iterator of type T
	 * @return : T : next item in the list on which the iterator is invoked or
	 *         null if the list has no more elements
	 */
	private static <T extends Comparable<? super T>> T next(Iterator<T> it) {
		return it.hasNext() ? it.next() : null;
	}

	public static void main(String[] args) {
		List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 7, 8, 10));
		List<Integer> l2 = new LinkedList<>(Arrays.asList(2, 4, 7, 9, 11, 13, 17, 19));
		List<String> sl1 = new LinkedList<>(Arrays.asList("Algorithm", "Data", "Structures"));
		List<String> sl2 = new LinkedList<>(Arrays.asList("Algorithms", "Data", "Implementation", "Structures", "of"));
		List<Integer> iout = new ArrayList<>(); // List storing output of the
												// intersect set operations
		List<Integer> uout = new ArrayList<>(); // List storing output of the
												// union set operations
		List<Integer> dout = new ArrayList<>(); // List storing output of the
												// difference set operations
		List<String> siout = new ArrayList<>(); // List storing output of the
												// intersect set operations
		List<String> suout = new ArrayList<>(); // List storing output of the
												// union set operations
		List<String> sdout = new ArrayList<>(); // List storing output of the
												// difference set operations
		Timer t = new Timer();
		System.out.println("----------------Integer operations----------------");
		System.out.println("Elements in first list: " + l1.toString());
		System.out.println("Elements in second list: " + l2.toString());
		intersect(l1, l2, iout);
		System.out.println("Result of set intersect operation " + iout.toString());
		System.out.println(t.end());
		t.start();
		union(l1, l2, uout);
		System.out.println("Result of set union operation " + uout.toString());
		System.out.println(t.end());
		t.start();
		difference(l1, l2, dout);
		System.out.println("Result of set difference operation " + dout.toString());
		System.out.println(t.end());
		System.out.println("----------------String operations----------------");
		System.out.println("Elements in first list: " + sl1.toString());
		System.out.println("Elements in second list: " + sl2.toString());
		intersect(sl1, sl2, siout);
		System.out.println("Result of set intersect operation " + siout.toString());
		System.out.println(t.end());
		t.start();
		union(sl1, sl2, suout);
		System.out.println("Result of set union operation " + suout.toString());
		System.out.println(t.end());
		t.start();
		difference(sl1, sl2, sdout);
		System.out.println("Result of set difference operation " + sdout.toString());
		System.out.println(t.end());
	}
}

package implementation.algorithm.balaji;

import java.util.Random;

/**
 * @author Mohanakrishna,Pradeep,Vinaya,Nikita
 *
 */
public class SortableList<T extends Comparable<? super T>> extends SinglyLinkedList<T> {

	/**
	 * Calling list and otherList are both sorted, this method will merge the
	 * right list into calling list while maintaining the sorted order. Uses
	 * O(1) extra space
	 *
	 * @param otherList
	 *            : A sorted list
	 */
	void merge(SortableList<T> otherList) {
		Entry<T> prev = this.head;
		Entry<T> left_item = this.head.next;
		Entry<T> right_item = otherList.head.next;
		while (left_item != null && right_item != null) {
			if (left_item.element.compareTo(right_item.element) <= 0) {
				// prev = left_item;
				left_item = left_item.next;
			} else {
				prev.next = right_item;
				Entry<T> temp = right_item.next;
				right_item.next = left_item;
				right_item = temp;
			}
			prev = prev.next;
		}
		// now process remaining elements in otherList if any
		if (right_item != null) {
			this.tail.next = right_item;
			this.tail = otherList.tail;
		}
	}

	/**
	 * Performs merge sort on the calling list, by recursively dividing and
	 * rearranging elements of the list. Uses O(lg n) space for recursive calls,
	 * no extra space since it does not create any new entries and works by
	 * rearranging pointers of the existing entries
	 */
	void mergeSort() {
		if (this.head.next.next != null) { // more than one element in the list
			SortableList<T> right = new SortableList<>(); // only O(1) space for
															// creating dummy
															// entry
			Entry<T> mid = this.getMid();
			right.head.next = mid.next; // start the right half from next to mid
			right.tail = this.tail;
			this.tail = mid; // this list will become the left half
			this.tail.next = null; // end the left half at mid
			// recursively divide the left and right half
			this.mergeSort();
			right.mergeSort();
			// rearrange this list and right sorted list as one list, using this
			// list
			this.merge(right);
		} else {
			this.tail = this.head.next;
			this.tail.next = null;
		}
	}

	/**
	 * Finds the middle element of the list
	 *
	 * @return : Entry<T> : returns the Entry object at the mid of the list
	 */
	private Entry<T> getMid() {
		Entry<T> p1 = this.head.next;
		Entry<T> p2 = this.head.next;
		Entry<T> c = p2.next;
		// Invariant: at the end of every iteration p1 moves to next element, p2
		// skip one element ahead
		// and c is next of p2
		while (c != null && c.next != null) {
			p1 = p1.next;
			p2 = c.next;
			c = p2.next;
		}
		return p1;
	}

	/**
	 * This method will sort the list passed as a parameter using merge sort and
	 * log n space
	 *
	 * @param list
	 *            : SortableList : List to be sorted
	 */
	public static <T extends Comparable<? super T>> void mergeSort(SortableList<T> list) {
		list.mergeSort();
	}

	public static void main(String[] args) {
		SortableList<Integer> sl_huge = new SortableList<>();
		SortableList<Integer> sl = new SortableList<>();
		sl.add(3);
		sl.add(5);
		sl.add(1);
		sl.add(6);
		sl.add(2);
		sl.add(4);
		System.out.println("sorting random Integer list of size: " + 6);
		System.out.println("unsorted list");
		sl.printList();
		Timer t = new Timer();
		mergeSort(sl);
		System.out.println("Sorted List");
		sl.printList();
		System.out.println("Time taken to sort:");
		System.out.println(t.end());
		int size = 10000000;
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			sl_huge.add(rand.nextInt(10 * size));
		}
		System.out.println("sorting random Integer list of size: " + size);
		t.start();
		mergeSort(sl_huge);
		System.out.println("Time taken to sort:");
		System.out.println(t.end());
		// sl.printList();
	}
}

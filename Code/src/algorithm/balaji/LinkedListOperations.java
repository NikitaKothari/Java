package algorithm.balaji;

class List<T> {
	T val;
	List<T> next = null;

	List(T val) {
		this.val = val;
		this.next = null;
	}

	List() {
		this.next = null;
	}
}

public class LinkedListOperations<T> {

	public static <T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> outList) {
		List<T> out = new List();
		while (l1 != null && l2 != null) {
			T x1 = l1.val;
			T x2 = l2.val;
			if (x1.compareTo(x2) > 0)
				l1 = l1.next;
			else if (x1.compareTo(x2) < 0)
				l2 = l2.next;
			else {
				out.val = l1.val;
				out = out.next;
				l1 = l1.next;
				l2 = l2.next;
			}
		}
	}

	public static <T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList) {
		List<T> out = new List();
		while (l1 != null && l2 != null) {
			T x1 = l1.val;
			T x2 = l2.val;
			if (x1.compareTo(x2) == 0) {
				out.val = l1.val;
				out = out.next;
				l1 = l1.next;
				l2 = l2.next;
			} else {
				out.val = l1.val;
				out = out.next;
				out.val = l2.val;
				out = out.next;
				l1 = l1.next;
				l2 = l2.next;
			}
		}

		while (l1 != null) {
			out.val = l1.val;
			out = out.next;
			l1 = l1.next;
		}
		while (l2 != null) {
			out.val = l2.val;
			out = out.next;
			l1 = l1.next;
		}
	}

	public static <T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList) {
		List<T> out = new List();
		while (l1 != null && l2 != null) {
			T x1 = l1.val;
			T x2 = l2.val;
			if (x1.compareTo(x2) == 0) {
				l1 = l1.next;
				l2 = l2.next;
			} else {
				out.val = l1.val;
				out = out.next;
				out.val = l2.val;
				out = out.next;
				l1 = l1.next;
				l2 = l2.next;
			}
		}

		while (l1 != null) {
			out.val = l1.val;
			out = out.next;
			l1 = l1.next;
		}
		while (l2 != null) {
			out.val = l2.val;
			out = out.next;
			l1 = l1.next;
		}
	}

}

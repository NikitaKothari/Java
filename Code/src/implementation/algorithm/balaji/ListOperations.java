package implementation.algorithm.balaji;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vinaya
 */
public class ListOperations {
	List<Integer> union(List<Integer> l1, List<Integer> l2) {
		List<Integer> l3 = new ArrayList<>();
		int maxSize = Math.max(l1.size(), l2.size());
		for (int i = 0, j = 0; i < maxSize && j < maxSize;) {
			if (l1.size() > i && l2.size() > j) {
				l3.add(l1.get(i) > l2.get(j) ? l2.get(j++) : l1.get(i++));
			} else if (l1.size() > i) {
				l3.add(l1.get(i++));
			} else {
				l3.add(l2.get(j++));
			}

		}
		return l3;
	}

	List<Integer> intersection(List<Integer> l1, List<Integer> l2) {
		List<Integer> l3 = new ArrayList<>();
		int maxSize = Math.max(l1.size(), l2.size());
		for (int i = 0, j = 0; i < maxSize && j < maxSize;) {
			if (l1.size() > i && l2.size() > j) {
				int l1Val = l1.get(i);
				int l2Val = l2.get(j);

				if (l1Val == l2Val) {
					l3.add(l2Val);
					i++;
					j++;
				} else if (l1Val > l2Val) {
					j++;
				} else {
					i++;
				}
			} else
				break;

		}
		return l3;
	}

	public static void main(String[] args) {

		List<Integer> l1 = new ArrayList<>();
		l1.add(1);
		l1.add(3);

		List<Integer> l2 = new ArrayList<>();
		l2.add(2);
		l2.add(3);
		l2.add(4);
		l2.add(7);
		l2.add(8);

		ListOperations ui = new ListOperations();
		System.out.println("union :" + Arrays.toString(ui.union(l1, l2).toArray()));

		System.out.println("intersction :" + Arrays.toString(ui.intersection(l1, l2).toArray()));

	}
}

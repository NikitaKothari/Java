package ctci.practice;

import java.util.HashSet;

import templete.custom.LinkedList;

public class LinkedListSol {

	void deleteDups(LinkedList<Integer> list) {
		HashSet<Integer> set = new HashSet<>();
		LinkedList<Integer> previous = null;
		while (list != null) {
			if (set.contains(list.val)) {
				previous.next = list.next;
			} else {
				set.add(list.val);
				previous = list;
			}
			list = list.next;
		}
	}

	int printKthLast(LinkedList<Integer> list, int k) {
		if (list == null)
			return 0;
		int index = printKthLast(list.next, k) + 1;
		if (index == k)
			System.out.println("Kth node" + list.val);
		return index;
	}

	int printKthLastNonRec(LinkedList<Integer> list, int k) {
		LinkedList<Integer> slow = list;
		LinkedList<Integer> fast = list;

		for (int i = 0; i < k; i++) {
			if (fast == null)
				return -1;
			fast = fast.next;
		}
		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow.val;
	}

	/**
	 * Not given access to the head node
	 * 
	 * @param list
	 * @return
	 */
	boolean deleteMiddleNode(LinkedList<Integer> list) {
		if (list == null || list.next == null)
			return false;
		list.val = list.next.val;
		list.next = list.next.next;
		return true;
	}

	class PartialSum {
		public LinkedList<Integer> sum = null;
		public int carry = 0;
	}

	LinkedList<Integer> addlist(LinkedList<Integer> l1, LinkedList<Integer> l2) {
		int len1 = l1.len(l1);
		int len2 = l2.len(l2);
		if (len1 < len2)
			l1 = padList(l1, len2 - len1);
		else if (len1 > len2)
			l2 = padList(l2, len1 - len2);

		PartialSum sum = addListHelper(l1, l2);
		if (sum.carry == 0)
			return sum.sum;
		else
			return insertBefore(sum.sum, sum.carry);
	}

	PartialSum addListHelper(LinkedList<Integer> l1, LinkedList<Integer> l2) {
		if (l1 == null && l2 == null)
			return new PartialSum();
		PartialSum sum = addListHelper(l1.next, l2.next);
		int val = sum.carry + l1.val + l2.val;
		LinkedList<Integer> full_result = insertBefore(sum.sum, val % 10);
		sum.sum = full_result;
		sum.carry = val / 10;
		return sum;
	}

	LinkedList<Integer> padList(LinkedList<Integer> list, int padding) {
		LinkedList<Integer> head = list;
		for (int i = 0; i < padding; i++) {
			head = insertBefore(head, 0);
		}
		return head;
	}

	LinkedList<Integer> insertBefore(LinkedList<Integer> list, int val) {
		LinkedList<Integer> node = new LinkedList<Integer>(val);
		if (list != null) {
			node.next = list;
		}
		return node;
	}

	boolean isEqual(LinkedList<Integer> l1, LinkedList<Integer> l2) {
		while (l1 != null & l2 != null) {
			if (l1.val != l2.val)
				return false;
			l1 = l1.next;
			l2 = l2.next;
		}
		return l1 == null && l2 == null;
	}

	LinkedList<Integer> reverse(LinkedList<Integer> list) {
		LinkedList<Integer> current = list;
		LinkedList<Integer> next = null, prev = null;
		while (current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		return prev;
	}

	static LinkedList<Integer> head;

	@SuppressWarnings("null")
	LinkedList<Integer> reverseRec(LinkedList<Integer> current, LinkedList<Integer> prev) {
		if (current == null) {
			head = current;
			current.next = prev;
			return null;
		}
		LinkedList<Integer> next = current.next;
		current.next = prev;
		reverseRec(next, prev);
		return head;
	}

	boolean isPalindrome(LinkedList<Integer> l1) {
		LinkedList<Integer> l2 = reverse(l1);
		return isEqual(l1, l2);
	}

	class Result {
		public Result(LinkedList<Integer> list, boolean result) {
			this.list = list;
			this.result = result;
		}

		public LinkedList<Integer> list;
		public boolean result;
	}

	Result isPalindromeRecurse(LinkedList<Integer> list, int length) {
		if (list == null || length <= 0)
			return new Result(list, true);
		else if (length == 1)
			return new Result(list.next, true);

		Result res = isPalindromeRecurse(list.next, length - 2);

		if (!res.result || res.list == null) {
			return res;
		}
		res.result = (list.val == res.list.val);
		res.list = res.list.next;
		return res;
	}

	boolean isPalindromeRec(LinkedList<Integer> list) {
		int length = list.len(list);
		Result p = isPalindromeRecurse(list, length);
		return p.result;
	}

	/**
	 * Not stable
	 * 
	 * @param list
	 * @param x
	 * @return
	 */
	LinkedList<Integer> partition(LinkedList<Integer> list, int x) {
		LinkedList<Integer> head = list;
		LinkedList<Integer> tail = list;

		while (list != null) {
			LinkedList<Integer> next = list.next;
			if (list.val < x) {
				list.next = head;
				head = list;
			} else {
				tail.next = list;
				tail = list;
			}
			list = next;
		}
		tail.next = null;
		return head;
	}

	LinkedList<Integer> cycleDetection(LinkedList<Integer> list) {
		LinkedList<Integer> slow = list;
		LinkedList<Integer> fast = list;
		while (fast == null || fast.next == null) {
			if (slow != fast)
				break;
			slow = slow.next;
			fast = fast.next;
		}
		// No loop
		if (fast == null || fast.next == null)
			return null;

		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return fast;
	}

	public static LinkedList<Integer> isIntersection(LinkedList<Integer> l1, LinkedList<Integer> l2) {
		int len1 = l1.len(l1);
		int len2 = l2.len(l2);

		if (len2 > len1) {
			while (len2 != len1) {
				l2 = l2.next;
				len2--;
			}
		}
		if (len1 > len2) {
			while (len2 != len1) {
				l1 = l1.next;
				len1--;
			}
		}

		while (l1.val != l2.val && l1 != null && l2 != null) {
			l1 = l1.next;
			l2 = l2.next;
		}
		return l1;
	}

	public static void main(String[] args) {
		LinkedList<Integer> l1 = new LinkedList<Integer>(4);
		l1.next = new LinkedList<Integer>(7);
		l1.next.next = new LinkedList<Integer>(9);
		l1.next.next.next = new LinkedList<Integer>(5);
		l1.next.next.next.next = new LinkedList<Integer>(3);

		LinkedList<Integer> l2 = new LinkedList<Integer>(5);
		l2.next = new LinkedList<Integer>(3);

		System.out.println(isIntersection(l1, l2).val);

	}

}

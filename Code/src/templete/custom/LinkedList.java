package templete.custom;

import java.util.Stack;

public class LinkedList<E> {
	public E val;
	public LinkedList<E> next;

	public LinkedList(E val) {
		this.val = val;
		next = null;
	}

	public int len(LinkedList<E> list) {
		int length = 0;
		while (list != null) {
			length++;
			list = list.next;
		}
		return length;
	}

	public void print(LinkedList<E> list) {
		while (list != null) {
			System.out.println(list.next);
			list = list.next;
		}
	}

	public LinkedList<E> removeNthFromEnd(LinkedList<E> head, int n) {
		if (head == null)
			return null;

		LinkedList<E> fast = head;
		LinkedList<E> slow = head;

		for (int i = 0; i < n; i++) {
			fast = fast.next;
		}

		// if remove the first node
		if (fast == null) {
			head = head.next;
			return head;
		}

		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}

		slow.next = slow.next.next;

		return head;
	}

	public boolean hasCycle(LinkedList<E> list) {
		LinkedList<E> slow = list;
		LinkedList<E> fast = list;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast)
				return true;
		}
		return false;
	}

	public LinkedList<E> reverseListIterative(LinkedList<E> head) {
		if (head == null || head.next == null)
			return head;

		LinkedList<E> p1 = head;
		LinkedList<E> p2 = p1.next;

		head.next = null;
		while (p1 != null && p2 != null) {
			LinkedList<E> t = p2.next;
			p2.next = p1;
			p1 = p2;
			p2 = t;
		}

		return p1;
	}

	public LinkedList<E> reverseListRecursive(LinkedList<E> head) {
		if (head == null || head.next == null)
			return head;

		// get second node
		LinkedList<E> second = head.next;
		// set first's next to be null
		head.next = null;

		LinkedList<E> rest = reverseListRecursive(second);
		second.next = head;

		return rest;
	}

	public void appendToTail(LinkedList<E> list, E value) {
		LinkedList<E> end = new LinkedList<E>(value);
		while (list.next != null)
			list = list.next;
		list.next = end;
	}

	public LinkedList<E> delete(LinkedList<E> head, E value) {
		LinkedList<E> list = head;
		if (list.val == value)
			return list.next;

		while (list.next != null) {
			if (list.next.val == value) {
				list.next = list.next.next;
				return head;
			}
			list = list.next;
		}
		return head;
	}

	public static void printrev(LinkedList<Integer> list) {
		if (list.next != null)
			printrev(list.next);
		System.out.println(list.val);
	}

	public static void printrevStack(LinkedList<Integer> list) {
		Stack<Integer> stack = new Stack<>();
		while (list != null) {
			stack.push(list.val);
			list = list.next;
		}
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>(5);
		list.next = new LinkedList<Integer>(7);
		list.next.next = new LinkedList<Integer>(9);
		list.next.next.next = new LinkedList<Integer>(11);
		printrevStack(list);
	}

}

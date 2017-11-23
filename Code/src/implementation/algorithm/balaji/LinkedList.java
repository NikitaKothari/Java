package implementation.algorithm.balaji;

import java.util.Stack;

public class LinkedList<E> {

	public E val;
	public LinkedList<E> next;

	public LinkedList(E val) {
		this.val = val;
		next = null;
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
		LinkedList<E> second = head.next;
		head.next = null;
		LinkedList<E> rest = reverseListRecursive(second);
		second.next = head;
		return rest;
	}

	public void printReverse(LinkedList<E> list) {
		if (list.next != null)
			printReverse(list.next);
		if (list.next == null)
			System.out.print(list.val);
		else
			System.out.print(" -> " + list.val);
	}

	public void printReverseStack(LinkedList<E> list) {
		Stack<E> stack = new Stack<>();
		while (list != null) {
			stack.push(list.val);
			list = list.next;
		}

		if (!stack.isEmpty())
			System.out.print(stack.pop());
		while (!stack.isEmpty())
			System.out.print(" -> " + stack.pop());
	}

	public void print(LinkedList<E> list) {
		if (list != null) {
			System.out.print(list.val);
			list = list.next;
		}

		while (list != null) {
			System.out.print(" -> " + list.val);
			list = list.next;
		}
	}

	public LinkedList<E> rev(LinkedList<E> list) {
		if (list == null || list.next == null)
			return list;

		LinkedList<E> p = list;
		list = list.next;

		p.next = null;
		while (list != null) {
			LinkedList<E> q = list;
			list = list.next;
			q.next = p;
			p = q;
		}
		return p;
	}

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>(5);
		list.next = new LinkedList<Integer>(7);
		list.next.next = new LinkedList<Integer>(9);
		list.next.next.next = new LinkedList<Integer>(11);
		list = list.rev(list);
		list.print(list);

	}
}

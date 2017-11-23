package implementation.algorithm.balaji;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author Mohanakrishna,Pradeep,Vinaya,Nikita
 *
 */
public class ReverseLinkedList<E> {

	/**
	 * logic: create new list from the right most node of the list. move head to
	 * right of the list with every recursive call until there is only one node
	 * left, then add the head to the reverse list (since everything after head
	 * is already in reverse list base case: list with one node, nothing to
	 * reverse so add to reverse list
	 * 
	 * @param head
	 *            : head node of the list
	 * @param rev
	 *            : empty reverse list which is the output
	 */
	private void reverseList(SinglyLinkedList.Entry<E> head, SinglyLinkedList<E> rev) {
		// call the function reverseList recursively moving head to right by one
		// node
		// stop when head is the last node
		if (head.next != null) {
			reverseList(head.next, rev);
		}
		// add head to reverse list
		if (head.element != null)
			rev.add(head.element);
	}

	/**
	 * function to reverse list recursively This is the helper function to call
	 * reverseList() recursive function
	 * 
	 * @param l
	 * @return reversed SinglyLinked list
	 */
	public SinglyLinkedList<E> reverseRecursive(SinglyLinkedList<E> l) {
		if (l == null)
			return l;
		SinglyLinkedList<E> rev = new SinglyLinkedList<>();

		reverseList(l.head, rev);
		return rev;
	}

	/**
	 * function to reverse list iteratively using 3 pointers: prevNode(previous
	 * node), curNode(current node) and nextNode reverse the link between
	 * prevNode and curNode and move to right of the list after all links are
	 * reversed, move the head to point to the prevNode (which is the first node
	 * in reversed list)
	 * 
	 * @param l
	 *            : input list
	 * @return reversed SinglyLinked list
	 */
	public SinglyLinkedList<E> reverseIterate(SinglyLinkedList<E> l) {
		if (l == null)
			return l;
		Iterator it = l.iterator();
		SinglyLinkedList.Entry<E> prevNode = null, curNode = l.head.next, nextNode;
		while (curNode != null) {
			nextNode = curNode.next;
			curNode.next = prevNode;
			prevNode = curNode;
			curNode = nextNode;
		}
		l.head.next = prevNode;
		return l;
	}

	/**
	 * logic: move head to right of the list with every recursive call until
	 * there is only one node left, then print the head node's element base
	 * case: list with one node, nothing to reverse so add to reverse list
	 * 
	 * @param head
	 *            : head node of the input list
	 */
	private void reversePrint(SinglyLinkedList.Entry<E> head) {
		if (head.next != null) {
			reversePrint(head.next);
		}
		if (head.element != null)
			System.out.print(" " + head.element);
	}

	/**
	 * function to reverse list recursively This is the helper function to call
	 * reversePrint() recursive function
	 * 
	 * @param l
	 *            : input list
	 */
	public void printRecursiveRev(SinglyLinkedList<E> l) {
		System.out.print(l.size + ":");
		reversePrint(l.head);
		System.out.println("");
	}

	/**
	 * function to print the list in reversed order iteratively using an
	 * external stack store all the elements as we traverse from left to right
	 * print the stack
	 * 
	 * @param l
	 *            : input list
	 */
	public void printIterativeRev(SinglyLinkedList<E> l) {
		Stack<E> st = new Stack<>();
		Iterator<E> it = l.iterator();
		while (it.hasNext()) {
			st.push(it.next());
		}
		System.out.print(st.size() + ":");
		while (!st.isEmpty()) {
			System.out.print(" " + st.pop());
		}
		System.out.println("");

	}

	public static void main(String[] args) {
		SinglyLinkedList<Integer> l = new SinglyLinkedList();
		l.add(11);
		l.add(12);
		l.add(13);
		l.add(14);
		l.add(15);
		l.add(16);
		l.add(17);
		System.out.println("original list");
		l.printList();

		ReverseLinkedList<Integer> list = new ReverseLinkedList();
		SinglyLinkedList<Integer> rev = list.reverseRecursive(l);
		System.out.println("list reversed recursively");
		rev.printList();

		System.out.println("Printing in reversed order iteratively");
		list.printIterativeRev(l);

		System.out.println("Printing in reversed order recursively");
		list.printRecursiveRev(l);

		System.out.println("list reversed iteratively");
		rev = list.reverseIterate(l);
		rev.printList();
	}

}

package implementation.algorithm.balaji;

/**
 * 
 * @author Mohanakrishna,Pradeep,Vinaya,Nikita
 *
 */
class Poly {
	int num;
	int pow;

	public Poly(int num, int pow) {
		this.num = num;
		this.pow = pow;
	}
}

public class SparsePolynomial extends SinglyLinkedList<Poly> {

	/**
	 * Addition of list1 and list2
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static SinglyLinkedList<Poly> addition(SinglyLinkedList.Entry<Poly> list1,
			SinglyLinkedList.Entry<Poly> list2) {

		SinglyLinkedList<Poly> ans = new SinglyLinkedList();
		while (list1 != null && list2 != null) {
			Poly p = new Poly(0, 0);
			Poly p1 = list1.element;
			Poly p2 = list2.element;
			if (p1.pow == p2.pow) {
				p.pow = p1.pow;
				p.num = p1.num + p2.num;
				list1 = list1.next;
				list2 = list2.next;
			} else if (p1.pow > p2.pow) {
				p.pow = p1.pow;
				p.num = p1.num;
				list1 = list1.next;
			} else {
				p.pow = p2.pow;
				p.num = p2.num;
				list2 = list2.next;
			}
			if (p.num != 0)
				ans.add(p);
		}
		while (list1 != null) {
			ans.add(list1.element);
			list1 = list1.next;
		}
		while (list2 != null) {
			ans.add(list2.element);
			list2 = list2.next;
		}
		return ans;
	}

	/**
	 * Subtraction of list1 and list
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static SinglyLinkedList<Poly> subtraction(SinglyLinkedList.Entry<Poly> list1,
			SinglyLinkedList.Entry<Poly> list2) {
		SinglyLinkedList<Poly> list3 = new SinglyLinkedList();
		while (list2 != null) {
			Poly p = list2.element;
			p.num = p.num * -1;
			list3.add(p);
			list2 = list2.next;
		}
		return addition(list1, list3.head.next);
	}

	/**
	 * Multiplication of list1 and list
	 * 
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static SinglyLinkedList<Poly> multiplication(SinglyLinkedList.Entry<Poly> list1,
			SinglyLinkedList.Entry<Poly> list2) {
		SinglyLinkedList<Poly> ans = new SinglyLinkedList();

		while (list2 != null) {
			SinglyLinkedList<Poly> temp = new SinglyLinkedList();
			Poly poly1 = list2.element;

			SinglyLinkedList.Entry<Poly> list3 = list1;

			while (list3 != null) {
				Poly poly2 = list3.element;
				Poly poly = new Poly(poly1.num * poly2.num, poly1.pow + poly2.pow);
				list3 = list3.next;
				temp.add(poly);
			}
			list2 = list2.next;
			ans = addition(ans.head.next, temp.head.next);
		}
		return ans;
	}

	public void printList(SinglyLinkedList.Entry<Poly> list) {
		if (list != null) {
			System.out.print("(" + list.element.num + "," + list.element.pow + ")");
			list = list.next;
		}
		while (list != null) {
			System.out.print(" -> ");
			System.out.print("(" + list.element.num + "," + list.element.pow + ")");
			list = list.next;
		}
	}

	public static void main(String[] args) {
		SinglyLinkedList<Poly> l1 = new SinglyLinkedList<>();
		Poly p = new Poly(2, 3);
		l1.add(p);
		p = new Poly(2, 2);
		l1.add(p);

		SinglyLinkedList<Poly> l2 = new SinglyLinkedList<>();
		p = new Poly(2, 3);
		l2.add(p);
		p = new Poly(2, 1);
		l2.add(p);

		SparsePolynomial sparsePolynomial = new SparsePolynomial();

		System.out.println("List1: ");
		sparsePolynomial.printList(l1.head.next);
		System.out.println();
		System.out.println("List2: ");
		sparsePolynomial.printList(l2.head.next);

		System.out.println();
		System.out.println();
		System.out.println("Addition of two singly linked list is: ");
		SinglyLinkedList<Poly> l3 = sparsePolynomial.addition(l1.head.next, l2.head.next);
		sparsePolynomial.printList(l3.head.next);

		System.out.println();
		System.out.println();
		System.out.println("Subtraction of two singly linked list is: ");
		l3 = sparsePolynomial.subtraction(l1.head.next, l2.head.next);
		sparsePolynomial.printList(l3.head.next);

		System.out.println();
		System.out.println();
		System.out.println("Multiplication of two singly linked list is: ");
		l3 = sparsePolynomial.multiplication(l1.head.next, l2.head.next);
		sparsePolynomial.printList(l3.head.next);
	}
	/**
	 * Output List1: (2,3) -> (2,2) List2: (2,3) -> (2,1)
	 * 
	 * Addition of two singly linked list is: (4,3) -> (2,2) -> (2,1)
	 * 
	 * Subtraction of two singly linked list is: (2,2) -> (-2,1)
	 * 
	 * Multiplication of two singly linked list is: (4,6) -> (4,5) -> (4,4) ->
	 * (4,3)
	 */
}

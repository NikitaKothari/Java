package implementation.algorithm.balaji;

import java.util.Scanner;

/**
 * @author Mohanakrishna,Pradeep,Vinaya,Nikita
 *
 */
public class MutiUnzipTest {
	public static void main(String[] args) {
		int n = 10;
		SinglyLinkedList<Integer> lst = new SinglyLinkedList<>();
		for (int i = 1; i <= n; i++) {
			lst.add(new Integer(i));
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Original list: ");
		lst.printList();
		System.out.println("Enter chaining factor");
		int k = in.nextInt();
		Timer t = new Timer();
		lst.multiUnzip(k);
		System.out.println("Rearranged list: ");
		lst.printList();
		System.out.println(t.end());

	}
}

// Sample code for Level 1 driver for lp1

// Change following line to your group number
package lp1.cs6301.g32;

/**
 * 
 * @author Vinaya, Pradeep, Mohan, Nikita
 */
public class LP1L1 {
	public static void main(String[] args) {
		Num x = new Num("16");
		Num y = new Num(3);

		x.printList();
		Num z = Num.add(x, y);
		System.out.println(z.toString());
		// Num z = new Num("987654321");

		// System.out.println(z.toString());
	}
}

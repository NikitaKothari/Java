// Sample code for Level 2 driver for lp1

// Change following line to your group number
package lp1.cs6301.g32;

/**
 * 
 * @author Vinaya, Pradeep, Mohan, Nikita
 */
public class LP1L2 {
	public static void main(String[] args) {
		Num x = new Num("9999999999");
		Num y = new Num("9");

		Num z = Num.divide(x, y);
		z.printList();
	}
}

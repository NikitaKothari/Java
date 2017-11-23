// Starter code for Level 4 driver for lp1

// Change following line to your group number
package lp1.cs6301.g32;

import java.util.Scanner;

/**
 * 
 * @author Vinaya, Pradeep, Mohan, Nikita
 */
public class LP1L4 {

	public static void main(String[] args) {
		Scanner in;
		if (args.length > 0) {

			int base = Integer.parseInt(args[0]);

			// Use above base for all numbers (except I/O, which is in base 10)
			Num.defaultBase = base;
		}

		in = new Scanner(System.in);
		L4Driver l4 = new L4Driver();

		l4.processInput(in);
	}
}

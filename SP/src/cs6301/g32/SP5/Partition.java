package cs6301.g32.SP5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import cs6301.g00.Timer;

/**
 *
 * @author Vinaya, Mohanakrishna, Nikita, Pradeep
 */
public class Partition {

	private void exchange(int[] A, int index1, int index2) {
		int temp = A[index1];
		A[index1] = A[index2];
		A[index2] = temp;
	}

	public int partition1(int[] A, int p, int r) {
		Random rn = new Random();
		int i = rn.nextInt(r - p + 1) + p;

		exchange(A, i, r);

		int x = A[r];
		i = p - 1;

		for (int j = p; j <= r - 1; j++) {
			if (A[j] <= x) {
				i++;
				exchange(A, i, j);
			}
		}
		exchange(A, i + 1, r);
		return i + 1;
	}

	void quickSort1(int[] A, int p, int r) {
		if (p < r) {
			int q = partition1(A, p, r);
			quickSort1(A, p, q - 1);
			quickSort1(A, q + 1, r);
		}
	}

	/**
	 * Using partition method 1
	 * 
	 * @param A
	 *            : Array to be sorted
	 */
	public void quickSort1(int[] A) {
		quickSort1(A, 0, A.length - 1);
	}

	public int partition2(int[] A, int p, int r) {
		Random rn = new Random();
		int x = A[rn.nextInt(r - p + 1) + p];

		int i = p - 1, j = r + 1;

		while (true) {
			do {
				i++;
			} while (A[i] < x);

			do {
				j--;
			} while (A[j] > x);

			if (i >= j) {
				return j;
			}
			exchange(A, i, j);
			// i++; j--;
		}
	}

	void quickSort2(int[] A, int p, int r) {
		if (p < r) {
			int q = partition2(A, p, r);
			quickSort1(A, p, q - 1);
			quickSort1(A, q, r);
		}
	}

	/**
	 * Using partition method 2
	 * 
	 * @param A
	 *            : Array to be sorted
	 */
	public void quickSort2(int[] A) {
		quickSort2(A, 0, A.length - 1);
	}

	public void printList(int[] A) {

		for (int a : A) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner in;
		try {
			if (args.length > 0) {
				File inputFile = new File(args[0]);
				in = new Scanner(inputFile);
			} else {
				in = new Scanner(System.in);
			}

			int count = in.nextInt();

			int[] A = new int[count];

			for (int i = 0; i < count; i++) {
				A[i] = in.nextInt();
			}

			int[] B = Arrays.copyOfRange(A, 0, count);
			Partition p1 = new Partition();

			Timer t = new Timer();
			t.start();
			p1.quickSort1(B);
			System.out.println(t.end());
			System.out.println("Output partition1:");

			Partition p2 = new Partition();

			t = new Timer();
			t.start();
			p2.quickSort2(A);
			System.out.println(t.end());
			System.out.println("Output partition2:");

		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		}
	}
}

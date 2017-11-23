package cs6301.g32.SP5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import cs6301.g00.Timer;

/**
 *
 * @author Vinaya, Mohanakrishna, Nikita, Pradeep
 */
public class KthLargestElements {

	public class MaxComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer x, Integer y) {
			return y - x;
		}
	}

	/**
	 * Using priority queue of size of array
	 * 
	 * @param A
	 *            : array - contains number
	 * @param k
	 *            : kth largest element
	 * @return
	 * 
	 */
	public int[] method1(int[] A, int k) {
		if (k <= 0) {
			return null;
		} else if (k >= A.length) {
			return A;
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>(new MaxComparator());

		for (int i = 0; i < A.length; i++) {
			pq.add(A[i]);
		}

		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = pq.remove();
		}
		return res;
	}

	/**
	 * Using priority queue of size of k
	 * 
	 * @param A
	 *            : array - contains number
	 * @param k
	 *            : kth largest element
	 * @return
	 */
	public int[] method2(int[] A, int k) {
		if (k <= 0) {
			return null;
		} else if (k >= A.length) {
			return A;
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < k; i++) {
			pq.add(A[i]);
		}
		for (int i = k; i < A.length; i++) {
			if (A[i] > pq.peek()) {
				pq.remove();
				pq.add(A[i]);
			}
		}

		int[] res = new int[k];
		for (int i = 0; i < k; i++) {
			res[i] = pq.remove();
		}
		return res;
	}

	void insertionSort(int[] arr, int p, int r) {
		for (int i = p + 1; i < r; i++) {
			int j = i - 1;
			int key = arr[i];
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = key;
		}
	}

	int select(int[] A, int p, int n, int k) {
		int T = 7;
		int r = p + n - 1;
		if (n < T) {
			insertionSort(A, p, r);
			return p + n - k;
		} else {
			Partition pt = new Partition();
			int q = pt.partition1(A, p, r);
			int left = q - p;
			int right = r - q;

			if (right >= k) {
				return select(A, q + 1, right, k);
			} else if (right + 1 == k) {
				return q;
			} else {
				return select(A, p, left, k - (1 + right));
			}
		}

	}

	/**
	 * Using select algorithm
	 * 
	 * @param A
	 *            : array - contains number
	 * @param k
	 *            : kth largest element
	 * @return
	 */
	public int[] method3(int[] A, int k) {
		int n = A.length;

		if (k <= 0) {
			return null;
		} else if (k >= n) {
			return A;
		}
		int index = select(A, 0, n, k);

		return Arrays.copyOfRange(A, index, n);
	}

	public static void main(String[] args) {
		Scanner in;
		try {
			int count, k;
			if (args.length > 0) {
				File inputFile = new File(args[0]);
				in = new Scanner(inputFile);
				count = Integer.parseInt(args[1]);
				k = Integer.parseInt(args[2]);
			} else {
				in = new Scanner(System.in);
				count = in.nextInt();
				k = in.nextInt();
			}

			int[] A = new int[count];
			for (int i = 0; i < count; i++) {
				A[i] = in.nextInt();
			}

			KthLargestElements s = new KthLargestElements();
			Timer t = new Timer();
			t.start();

			int[] res = s.method1(A, k);

			System.out.println(t.end());
			t.start();
			res = s.method2(A, k);
			System.out.println(t.end());

			t.start();
			res = s.method3(A, k);
			System.out.println(t.end());

		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		}
	}
}

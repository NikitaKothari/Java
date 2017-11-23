package cs6301.g32.SP6;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author nikita, Mohanakrishna, Nikita, Pradeep
 */
public class PrimeFactors {

	class Factor implements Comparable<Factor> {
		int[] factors;
		int val;

		Factor(int[] factors, int val) {
			this.factors = factors;
			this.val = val;
		}

		@Override
		public int compareTo(Factor o) {
			if (this.val > o.val)
				return 1;
			if (this.val < o.val)
				return -1;
			return 0;
		}
	}

	/**
	 * Returns x power n
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	int power(int x, int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return x;
		int s = power(x, n / 2);
		return n % 2 == 0 ? s * s : s * s * x;
	}

	/**
	 * 
	 * @param nums
	 *            : prime factors
	 * @param max
	 *            : Number of prime factors to be generated
	 */
	public void primeFactors(int[] nums, int max) {
		PriorityQueue<Factor> pq = new PriorityQueue<>();
		Set<Integer> set = new TreeSet<>();
		int[] fact = new int[nums.length];
		pq.add(new Factor(fact, 1));
		while (set.size() < max) {
			Factor factor = pq.remove();
			int[] factors = factor.factors;
			int result = factor.val;
			set.add(result);
			for (int k = 0; k < nums.length; k++) {
				factors[k]++;
				pq.add(new Factor(factors.clone(), result * nums[k]));
				factors[k]--;
			}
		}
		for (int n : set)
			System.out.println(n);
	}

	public static void main(String[] args) {
		PrimeFactors primeFactors = new PrimeFactors();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of Prime factors ");
		int n = sc.nextInt();
		int[] fact = new int[n];
		for (int i = 0; i < n; i++)
			fact[i] = sc.nextInt();
		System.out.println("Enter max number of factors ");
		int max = sc.nextInt();
		primeFactors.primeFactors(fact, max);
	}
}

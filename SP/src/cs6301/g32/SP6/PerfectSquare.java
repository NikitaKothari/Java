package cs6301.g32.SP6;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * @author nikita, Mohanakrishna, Nikita, Pradeep
 */
public class PerfectSquare {

	class Square implements Comparable<Square> {
		int a;
		int b;
		int c;

		public Square(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Square o) {
			if (this.b > o.b)
				return 1;
			if (this.b == o.b)
				return 0;
			return -1;
		}
	}

	int power(int x, int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return x;
		int s = power(x, n / 2);
		return n % 2 == 0 ? s * s : s * s * x;
	}

	void perfectsquare(int start, int max) {
		PriorityQueue<Square> pq = new PriorityQueue<>();
		pq.add(new Square(start, 2, power(start, 2)));
		int i = 0;
		Timer t = new Timer();
		t.start();
		while (!pq.isEmpty() && max > i) {
			Square s = pq.remove();
			i++;
			System.out.println(s.a + " " + s.b + " " + s.c);
			if (s.a == 2) {
				pq.add(new Square(s.a, s.b + 1, s.c * s.a));
				pq.add(new Square(3, s.b, power(3, s.b)));
			} else
				pq.add(new Square(s.a + 1, s.b, power(s.a + 1, s.b)));
		}
	}

	public static void main(String[] args) {
		PerfectSquare perfectSquare = new PerfectSquare();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter starting number ");
		int start = sc.nextInt();
		System.out.println("Enter max number ");
		int max = sc.nextInt();
		perfectSquare.perfectsquare(start, max);
	}
}

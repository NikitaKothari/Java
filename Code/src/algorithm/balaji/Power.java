package algorithm.balaji;

public class Power {

	public static int power(int x, int n) {
		if (n == 0)
			return 1;
		if (n == 1)
			return x;
		int p = power(x, n / 2);
		if (n % 2 == 1)
			return p * p * x;
		else
			return p * p;
	}
}

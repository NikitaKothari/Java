package dynamic.programming.practice;

public class Fibonacci {

	public static int fibRec(int n) {
		if (n == 0 || n == 1)
			return n;
		return fib(n - 1) + fib(n - 2);
	}

	public static int fib(int n) {
		int[] f = new int[n + 1];
		f[0] = 0;
		f[1] = 1;
		for (int i = 2; i <= n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f[n];
	}

	public static int fibMemo(int n, int[] f) {
		if (f[n] != -1)
			return f[n];
		else {
			if (n == 0 || n == 1)
				return n;
			else
				f[n] = fibMemo(n - 1, f) + fibMemo(n - 2, f);
			return f[n];
		}
	}

}

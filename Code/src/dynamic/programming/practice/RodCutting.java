package dynamic.programming.practice;

import sorting.searching.Utilities;

public class RodCutting {

	public static int[] nSqure(int[] profit) {
		int n = profit.length;
		int[] r = new int[n + 1];
		int[] cut = new int[n + 1];
		r[0] = 0;
		cut[0] = 0;

		for (int j = 1; j <= n; j++) {
			r[j] = profit[0] + r[j - 1];
			cut[j] = 1;
			for (int i = 2; i <= j; i++) {
				if (r[j] < (profit[i - 1] + r[j - i])) {
					r[j] = profit[i - 1] + r[j - i];
					cut[j] = i;
				}
			}
		}
		Utilities.print(cut);
		return r;
	}

	public static void main(String[] args) {
		int[] p = { 1, 5, 8, 10, 13, 17, 17, 20, 24, 26 };
		Utilities.print(nSqure(p));
	}

}

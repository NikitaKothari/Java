package dynamic.programming.practice;

public class LongestCommonSubsequence {

	public static int lcs(char[] str1, char[] str2, int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		if (str1[m - 1] != str2[n - 1])
			return Math.max(lcs(str1, str2, m - 1, n), lcs(str1, str2, m, n - 1));
		return lcs(str1, str2, m - 1, n - 1) + 1;
	}

	public static int lcs(char[] str1, char[] str2) {
		int m = str1.length, n = str2.length;
		int[][] l = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++)
			l[i][0] = 0;
		for (int i = 0; i <= n; i++)
			l[0][n] = 0;

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (str1[i - 1] == str2[j - 1])
					l[i][j] = 1 + l[i - 1][j - 1];
				else
					l[i][j] = Math.max(l[i - 1][j], l[i][j - 1]);
			}
		}
		return l[m][n];
	}

	public static void main(String[] args) {
		String s1 = "GATTACA";
		String s2 = "GALACTICA";

		int i = lcs(s1.toCharArray(), s2.toCharArray());
		System.out.println(i);
	}

}

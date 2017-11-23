package dynamic.programming.practice;

public class LongestPalindromicSequence {

	public static int lps(char[] s, int i, int j) {
		if (j == i - 1)
			return 0;
		if (i == j)
			return 1;
		if (s[i] == s[j])
			return 2 + lps(s, i + 1, j - 1);
		return Math.max(lps(s, i + 1, j), lps(s, i, j - 1));
	}

	public static void main(String[] args) {
		String s = "NIKIKITA";
		System.out.println(lps(s.toCharArray(), 0, s.length() - 1));
	}

}

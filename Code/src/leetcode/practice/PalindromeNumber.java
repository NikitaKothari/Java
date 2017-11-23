package leetcode.practice;

public class PalindromeNumber {

	/**
	 * Determine whether an integer is a palindrome. Do this without extra
	 * space.
	 * 
	 * @param x
	 * @return
	 * 
	 * 		reverse half of the value and check
	 * 
	 *         Time complexity : O(log​​ n). We divided the input by 10 for
	 *         every iteration, so the time complexity is O(log ​10 ​​ n)
	 * 
	 *         Space complexity : O(1).
	 */
	public static boolean IsPalindrome(int x) {

		if (x < 0 || (x % 10 == 0 && x != 0))
			return false;

		int rev = 0;
		while (x > rev) {
			rev += rev % 10;
			x = x / 10;
		}

		return x == rev || x == (rev / 10);
	}

	public static void main(String[] args) {
		System.out.println(IsPalindrome(20));
	}
}

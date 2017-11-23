package algorithm.balaji;

public class Search {

	/**
	 * Best case time complexity o(1) Average and Worst o(n)
	 * 
	 * @param nums
	 * @param x
	 *            : element needs to be search
	 * @return
	 */
	public static boolean linear(int[] nums, int x) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == x)
				return true;
		}
		return false;
	}

	public static boolean binary(int nums[], int x, int start, int end) {
		int mid = (start + end) / 2;

		if (start > end)
			return false;
		if (nums[mid] == x)
			return true;
		else if (nums[mid] > x)
			return binary(nums, x, start, mid - 1);
		else
			return binary(nums, x, mid + 1, end);
	}

	public static void main(String[] args) {
		int[] nums = { 1, 3, 5, 6 };
		System.out.println(binary(nums, 5, 0, 3));
	}

}

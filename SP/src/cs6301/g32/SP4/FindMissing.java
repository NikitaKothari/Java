package cs6301.g32.SP4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikita,Vinaya,Pradeep,Mohanakrishna
 */
public class FindMissing {

	/**
	 * 
	 * @param nums
	 *            array of n distinct integers, in sorted order, starting at 1
	 *            and ending with n+k
	 * @param low
	 *            Start Index
	 * @param high
	 *            End index
	 * @param res
	 *            It stores missing numbers
	 */
	public void findMissing(int[] nums, int low, int high, List<Integer> res) {
		if ((nums[high] - nums[low]) == (high - low))
			return;
		int mid = (high + low) / 2;

		findMissing(nums, low, mid, res);

		if (nums[mid] != (nums[mid + 1] - 1)) {
			int k = nums[mid + 1] - 1 - nums[mid];
			for (int i = 1; i <= k; i++) {
				res.add(nums[mid] + i);
			}
		}

		findMissing(nums, mid + 1, high, res);
	}

	private List<Integer> missingElements(int[] nums) {
		List<Integer> res = new ArrayList<>();
		findMissing(nums, 0, nums.length - 1, res);
		return res;
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 6, 8, 12, 15, 19 };
		FindMissing findMissing = new FindMissing();
		List<Integer> result = findMissing.missingElements(nums);
		if (result == null || result.size() == 0)
			System.out.println("No number is missing");
		else {
			System.out.println("Missing numsbers are");
			System.out.println(result);
		}

	}

}

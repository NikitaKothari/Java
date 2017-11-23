package algorithm.balaji;

public class MaxSubArray {
	public static int sum(int[] nums) {
		int sum = nums[0], ans = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (sum < 0)
				sum = nums[i];
			else
				sum += nums[i];
			ans = Math.max(ans, sum);
		}
		return ans;
	}
}

package test.practice;

import java.util.HashSet;
import java.util.Set;

public class Test {
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (set.contains(num))
				return true;
			else
				set.add(num);
		}
		return false;
	}

	public static boolean membershipInStrings(String input1, String input2) {
		return ("" + input1.charAt(0)).equals(input2);
	}

	public int maxSubArray(int[] nums) {
		int max = Integer.MIN_VALUE, sum = 0;
		for (int i = 0; i < nums.length; i++) {
			if ((sum + nums[i]) > 0)
				sum = sum + nums[i];
			else
				sum = nums[i];
			max = Integer.max(max, sum);
		}
		return max;
	}

}

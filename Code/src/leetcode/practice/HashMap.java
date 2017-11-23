package leetcode.practice;

import java.util.HashSet;
import java.util.Set;

public class HashMap {

	/**
	 * Given an array of integers, find if the array contains any duplicates.
	 * Your function should return true if any value appears at least twice in
	 * the array, and it should return false if every element is distinct.
	 * 
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i]))
				return true;
			set.add(nums[i]);
		}
		return false;
	}

	/**
	 * Given an array of integers and an integer k, find out whether there are
	 * two distinct indices i and j in the array such that nums[i] = nums[j] and
	 * the absolute difference between i and j is at most k.
	 * 
	 * 
	 * @param nums
	 * @param k
	 * @return
	 */
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < nums.length; i++) {
			if (i > k)
				set.remove(nums[i - k - 1]);
			if (!set.add(nums[i]))
				return true;
		}
		return false;
	}

	public int singleNumber(int[] nums) {

		Set<Integer> set = new HashSet<>();
		int totalSum = 0;
		for (int i = 0; i < nums.length; i++) {
			set.add(nums[i]);
			totalSum += nums[i];
		}
		int sum = 0;
		for (int x : set) {
			sum += x;
		}
		return (3 * sum - totalSum) / 2;
	}

}

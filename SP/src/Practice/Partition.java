package Practice;

import java.util.ArrayList;
import java.util.List;

public class Partition<T> {

	public static List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> l = new ArrayList<>();
		permute(nums, 0, nums.length - 1, l);
		return l;

	}

	private static void permute(int[] nums, int low, int high, List<List<Integer>> l) {
		if (low == high) {
			print(nums);
			List<Integer> arrList = new ArrayList<>();
			for (int i : nums) {
				arrList.add(i);
			}
			l.add(arrList);
		}
		for (int i = low; i <= high; i++) {
			swap(nums, i, low);
			permute(nums, low + 1, high, l);
			swap(nums, i, low);
		}
	}

	public static void swap(int[] c, int i, int j) {
		int temp = c[i];
		c[i] = c[j];
		c[j] = temp;
	}

	public static void permute(int[] nums, int c) {
		int k = nums.length - 1;
		if (c == 0) {
			print(nums);
		} else {
			int d = k - c;
			permute(nums, c - 1);
			for (int i = d + 1; i < nums.length; i++) {
				int temp = nums[d];
				nums[d] = nums[i];
				nums[i] = temp;
				permute(nums, c - 1);
				nums[i] = nums[d];
				nums[d] = temp;
			}
		}
	}

	public static void heap(int[] nums, int g) {
		if (g == 1)
			print(nums);
		else {
			for (int i = 0; i < g - 1; i++) {
				heap(nums, g - 1);
				if (g % 2 == 0)
					swap(nums, i, g - 1);
				else
					swap(nums, 0, g - 1);
			}
			heap(nums, g - 1);
		}
	}

	public static void combination(int[] nums, int i, int c, int[] chosen, int k) {
		if (c == 0)
			print(chosen);
		else {
			chosen[k - c] = nums[i];
			combination(nums, i + 1, c - 2, chosen, k);
			if (nums.length - i > c)
				combination(nums, i + 1, c, chosen, k);
		}
	}

	private static void print(int[] nums) {
		for (int i : nums)
			System.out.print(i + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		permute(nums);
	}

}
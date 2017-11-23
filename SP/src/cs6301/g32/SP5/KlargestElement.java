package cs6301.g32.SP5;

import java.util.Collections;
import java.util.PriorityQueue;

public class KlargestElement {

	public static int[] method1(int[] nums, int k) {
		if (k < 0)
			return null;
		else if (k >= nums.length)
			return nums;
		PriorityQueue<Integer> heap = new PriorityQueue<>(nums.length, Collections.reverseOrder());
		for (int i : nums)
			heap.add(i);
		int[] kLargest = new int[k];
		for (int i = 0; i < k; i++)
			kLargest[i] = heap.poll();
		return kLargest;
	}

	public static int[] method2(int[] nums, int k) {
		if (k < 0)
			return null;
		else if (k >= nums.length)
			return nums;
		PriorityQueue<Integer> heap = new PriorityQueue<>(k);
		for (int i = 0; i < k; i++)
			heap.add(nums[i]);
		for (int i = k; i < nums.length; i++) {
			if (nums[i] > heap.peek()) {
				heap.remove();
				heap.add(nums[i]);
			}
		}
		int[] kLargest = new int[k];
		int i = 0;
		while (!heap.isEmpty())
			kLargest[i++] = heap.poll();
		return kLargest;
	}

	public static int[] method3(int[] nums, int k) {
		if (k < 0)
			return null;
		else if (k >= nums.length)
			return nums;
		select(nums, 0, nums.length - 1, k);
		int[] kLargest = new int[k];
		for (int i = 0; i < k; i++)
			kLargest[i] = nums[i];
		return kLargest;

	}

	public static void insertionSort(int[] nums) {
		int n = nums.length;
		for (int j = 1; j < n; j++) {
			int key = nums[j];
			int i = j - 1;
			while ((i > -1) && (nums[i] > key)) {
				nums[i + 1] = nums[i];
				i--;
			}
			nums[i + 1] = key;
		}
	}

	private static int select(int[] nums, int low, int high, int k) {
		int pivot = nums[high];
		int left = low;
		int right = high;
		while (true) {
			while (pivot <= nums[left] && left < right)
				left++;
			while (pivot > nums[right] && left < right)
				right--;
			if (left == right)
				break;
			int temp = nums[left];
			nums[left] = nums[right];
			nums[right] = temp;
		}

		int temp = nums[left];
		nums[left] = nums[high];
		nums[high] = temp;

		if (k == left + 1)
			return pivot;
		else if (k < left + 1)
			return select(nums, low, left - 1, k);
		return select(nums, left + 1, high, k);
	}

	public static void main(String[] args) {
		int[] nums = { 12, 11, -13, -5, 6, -7, 5, -3, -6 };

		int[] ans = method2(nums, 2);
		for (int i : ans)
			System.out.println(i + " ");
		// System.out.println(method1(nums, 2));
		// System.out.println(method2(nums, 2));
	}

}

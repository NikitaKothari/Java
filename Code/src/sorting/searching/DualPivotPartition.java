package sorting.searching;

import java.util.Arrays;

public class DualPivotPartition {

	public void quickSort(int[] nums, int low, int high) {

		if (nums == null || nums.length == 0)
			return;

		if (low >= high)
			return;

		if (nums[low] > nums[high])
			swap(nums, low, high);

		int x1 = nums[low];
		int x2 = nums[high];

		int k = low + 1;
		int i = low + 1;
		int j = high - 1;

		while (i <= j) {
			while (nums[i] >= x1 && nums[i] <= x2 && i <= j) {
				i++;
			}

			while (nums[i] < x1 && i <= j && k <= j) {
				swap(nums, i, k);
				i++;
				k++;
			}

			while (nums[j] > x2 && j >= i) {
				j--;
			}

			while (nums[i] > x2 && nums[j] < x1 && i <= j && k <= j) {
				int temp = nums[k];
				nums[k] = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				k++;
				i++;
				j--;
			}
			while (nums[i] > x2 && nums[j] >= x1 && nums[j] <= x2 && i <= j) {
				swap(nums, i, j);
				i++;
				j--;
			}

		}

		swap(nums, low, k - 1);
		swap(nums, j + 1, high);

		quickSort(nums, low, k - 1);
		quickSort(nums, j + 1, high);
		if (x1 != x2)
			quickSort(nums, k, i);

	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public static void main(String[] args) {
		int[] nums = { 99, 28, 33, 78, 18, 92, 79, 6, 54, 13, 54, 3, 99, 20, 75, 47, 40, 55, 43, 44, 51, 55, 54, 87, 51,
				88, 15, 14, 53, 77, 76, 45, 71, 48, 25, 45, 52, 99, 91, 18, 4, 80, 43, 35, 57, 41, 62, 30, 48, 4, 95,
				41, 67, 10, 22, 99, 52, 17, 47, 88, 98, 96, 58, 52, 11, 79, 14, 45, 55, 63, 64, 84, 40, 19, 59, 61, 7,
				34, 54, 27, 15, 17, 67, 5, 49, 26, 43, 84, 4, 14, 43, 67, 19, 8, 22, 44, 17, 51, 69, 50, 99, 28, 33, 78,
				18, 92, 79, 6, 54, 13, 54, 3, 99, 20, 75, 47, 40, 55, 43, 44, 51, 55, 54, 87, 51, 88, 15, 14, 53, 77,
				76, 45, 71, 48, 25, 45, 52, 99, 91, 18, 4, 80, 43, 35, 57, 41, 62, 30, 48, 4, 95, 41, 67, 10, 22, 99,
				52, 17, 47, 88, 98, 96, 58, 52, 11, 79, 14, 45, 55, 63, 64, 84, 40, 19, 59, 61, 7, 34, 54, 27, 15, 17,
				67, 5, 49, 26, 43, 84, 4, 14, 43, 67, 19, 8, 22, 44, 17, 51, 69, 50 };

		int[] nums1 = { 99, 28, 33, 78, 18, 92, 79, 6, 54, 13, 54, 3, 99, 20, 75, 47, 40, 55, 43, 44, 51, 55, 54, 87,
				51, 88, 15, 14, 53, 77, 76, 45, 71, 48, 25, 45, 52, 99, 91, 18, 4, 80, 43, 35, 57, 41, 62, 30, 48, 4,
				95, 41, 67, 10, 22, 99, 52, 17, 47, 88, 98, 96, 58, 52, 11, 79, 14, 45, 55, 63, 64, 84, 40, 19, 59, 61,
				7, 34, 54, 27, 15, 17, 67, 5, 49, 26, 43, 84, 4, 14, 43, 67, 19, 8, 22, 44, 17, 51, 69, 50, 99, 28, 33,
				78, 18, 92, 79, 6, 54, 13, 54, 3, 99, 20, 75, 47, 40, 55, 43, 44, 51, 55, 54, 87, 51, 88, 15, 14, 53,
				77, 76, 45, 71, 48, 25, 45, 52, 99, 91, 18, 4, 80, 43, 35, 57, 41, 62, 30, 48, 4, 95, 41, 67, 10, 22,
				99, 52, 17, 47, 88, 98, 96, 58, 52, 11, 79, 14, 45, 55, 63, 64, 84, 40, 19, 59, 61, 7, 34, 54, 27, 15,
				17, 67, 5, 49, 26, 43, 84, 4, 14, 43, 67, 19, 8, 22, 44, 17, 51, 69, 50 };

		Arrays.sort(nums1);

		DualPivotPartition qs = new DualPivotPartition();
		Utilities util = new Utilities();
		util.print(nums);
		qs.quickSort(nums, 0, nums.length - 1);
		util.print(nums1);
		util.print(nums);
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != nums1[i])
				System.out.println("i is " + i);
		}

	}

}

package cs6301.g32.SP5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Vinaya, Mohanakrishna, Nikita, Pradeep
 */
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
				if (i != k) {
					int temp = nums[k];
					nums[k] = nums[j];
					nums[j] = nums[i];
					nums[i] = temp;
				} else {
					swap(nums, k, j);
				}
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

	public static void print(int[] nums) {
		String numsString = Arrays.toString(nums);
		System.out.print(numsString);
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner in;
		try {
			int count;
			if (args.length > 0) {
				File inputFile = new File(args[0]);
				in = new Scanner(inputFile);
				count = Integer.parseInt(args[1]);
			} else {
				in = new Scanner(System.in);
				count = in.nextInt();
			}
			int[] nums = new int[count];
			for (int i = 0; i < count; i++) {
				nums[i] = in.nextInt();
			}

			int[] nums1 = Arrays.copyOfRange(nums, 0, count);
			Arrays.sort(nums1);
			DualPivotPartition qs = new DualPivotPartition();

			qs.quickSort(nums, 0, nums.length - 1);
			Object[] obj = { nums };
			Object[] obj1 = { nums1 };
			if (Arrays.deepEquals(obj1, obj)) {
				System.out.println("Same");
			} else {
				System.out.println("Not Same");
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File not found");
		}

	}

}

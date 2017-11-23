package implementation.algorithm.balaji;

import sorting.searching.MergeSort;
import sorting.searching.Utilities;

public class GenericMergeSort {

	static <T extends Comparable<? super T>> void sort(T[] arr, int low, int high) {
		if (low < high) {
			// Find the middle point
			int m = (low + high) / 2;

			// Sort first and second halves
			sort(arr, low, m);
			sort(arr, m + 1, high);

			// Merge the sorted halves
			sortMerge(arr, low, m, high);
		}
	}

	static <T extends Comparable<? super T>> void sortMerge(T[] arr, int low, int mid, int high) {
		Object[] tmp = new Object[high - low + 1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= high) {
			if (arr[i].compareTo(arr[j]) <= 0)
				tmp[k] = arr[i++];
			else
				tmp[k] = arr[j++];
			k++;
		}
		if (i <= mid && j > high) {
			while (i <= mid)
				tmp[k++] = arr[i++];
		} else {
			while (j <= high)
				tmp[k++] = arr[j++];
		}
		for (k = 0; k < tmp.length; k++) {
			arr[k + low] = (T) (tmp[k]); // this is the line that would generate
											// the warning
		}
	}

	public static void main(String[] args) {

		Integer[] nums = { 4, 6, 7, 9, 1, 3 };
		MergeSort ms = new MergeSort();
		Utilities util = new Utilities();
		sort(nums, 0, nums.length - 1);
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}

}

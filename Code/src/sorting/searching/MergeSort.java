package sorting.searching;

public class MergeSort {

	static <T extends Comparable<? super T>> void mergeSort(T[] arr, int low, int high) {
		if (low < high) {
			// Find the middle point
			int m = (low + high) / 2;

			// Sort first and second halves
			mergeSort(arr, low, m);
			mergeSort(arr, m + 1, high);

			// Merge the sorted halves
			mergeSortMerge(arr, low, m, high);
		}
	}

	static <T extends Comparable<? super T>> void mergeSortMerge(T[] arr, int low, int mid, int high) {
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

	void sort(int nums[], int low, int high) {
		if (low < high) {
			// Find the middle point
			int m = (low + high) / 2;

			// Sort first and second halves
			sort(nums, low, m);
			sort(nums, m + 1, high);

			// Merge the sorted halves
			merge(nums, low, m, high);
		}
	}

	void merge(int nums[], int low, int mid, int high) {

		int[] tmp = new int[high - low + 1];
		int i = low;
		int j = mid + 1;
		int k = 0;
		while (i <= mid && j <= high) {
			if (nums[i] <= nums[j])
				tmp[k] = nums[i++];
			else
				tmp[k] = nums[j++];
			k++;
		}
		if (i <= mid && j > high) {
			while (i <= mid)
				tmp[k++] = nums[i++];
		} else {
			while (j <= high)
				tmp[k++] = nums[j++];
		}
		for (k = 0; k < tmp.length; k++) {
			nums[k + low] = tmp[k];
		}
	}

	public static void main(String[] args) {
		int[] nums = { 4, 6, 7, 9, 1, 3 };
		MergeSort ms = new MergeSort();
		Utilities util = new Utilities();

		ms.sort(nums, 0, nums.length - 1);
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}

}

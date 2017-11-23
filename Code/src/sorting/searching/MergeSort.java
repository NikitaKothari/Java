package sorting.searching;

import java.io.FileNotFoundException;

public class MergeSort {

	static <T extends Comparable<? super T>> void sort(T[] arr, int low, int high) {
		if (low < high) {
			// Find the middle point
			int m = (low + high) / 2;

			// Sort first and second halves
			sort(arr, low, m);
			sort(arr, m + 1, high);

			// Merge the sorted halves
			merge(arr, low, m, high);
		}
	}

	@SuppressWarnings("unchecked")
	static <T extends Comparable<? super T>> void merge(T[] arr, int low, int mid, int high) {
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
			arr[k + low] = (T) tmp[k];
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

	public static void mergeSortMethod1(int[] nums, int low, int high) {
		if (low < high) {
			int mid = (high + low) / 2;
			mergeSortMethod1(nums, low, mid);
			mergeSortMethod1(nums, mid + 1, high);
			mergeMethod1(nums, low, mid, high);
		}
	}

	public static void mergeMethod1(int[] nums, int low, int mid, int high) {
		int[] l = new int[mid - low + 1];
		int[] r = new int[high - mid];
		int k = low;
		for (int i = 0; i < (mid - low + 1); i++)
			l[i] = nums[k++];
		for (int i = 0; i < (high - mid); i++)
			r[i] = nums[k++];
		int i = 0, j = 0;

		for (k = low; k <= high; k++) {
			if ((j >= r.length) || (i < l.length && l[i] <= r[j]))
				nums[k] = l[i++];
			else
				nums[k] = r[j++];
		}

	}

	public static void insertionSort(int[] nums, int low, int high) {
		for (int j = low; j <= high; j++) {
			int key = nums[j];
			int i = j - 1;
			while ((i > low - 1) && (nums[i] > key)) {
				nums[i + 1] = nums[i];
				i--;
			}
			nums[i + 1] = key;
		}
	}

	public static void mergeSortMethod2(int[] nums, int low, int high) {
		if (high - low < 2 && low < high) {
			insertionSort(nums, low, high);
		} else if (low < high) {
			int mid = (high + low) / 2;
			mergeSortMethod2(nums, low, mid);
			mergeSortMethod2(nums, mid + 1, high);
			mergeMethod2(nums, low, mid, high);
		}
	}

	private static void mergeMethod2(int[] nums, int low, int mid, int high) {
		int[] b = new int[high - low + 1];
		int k = low;

		for (int i = 0; i < (high - low + 1); i++)
			b[i] = nums[k++];

		mid = (b.length - 1) / 2;
		int i = 0, j = mid + 1;
		for (k = low; k <= high; k++) {
			if (j >= b.length || (i <= mid && b[i] <= b[j]))
				nums[k] = b[i++];
			else
				nums[k] = b[j++];
		}
	}

	public static void mergeSortMethod3(int[] nums) {
		int[] b = new int[nums.length];
		b = nums.clone();
		mergeSortMethod3(b, nums, 0, nums.length - 1);
	}

	public static void mergeSortMethod3(int[] b, int[] nums, int low, int high) {
		if ((high - low) < 3 && low < high) {
			insertionSort(nums, low, high);
			insertionSort(b, low, high);
		} else if (low < high) {
			int mid = (high + low) / 2;
			mergeSortMethod3(b, nums, low, mid);
			mergeSortMethod3(b, nums, mid + 1, high);
			mergeMethod3(b, nums, low, mid, high);
		}
	}

	private static void mergeMethod3(int[] nums, int[] b, int low, int mid, int high) {
		int i = low, j = mid + 1;
		for (int k = low; k <= high; k++) {
			if (j > high || (i <= mid && nums[i] <= nums[j]))
				b[k] = nums[i++];
			else
				b[k] = nums[j++];
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		int[] nums = { 4, 6, 7, 9, 1, 3 };
		mergeSortMethod2(nums, 0, nums.length - 1);
		for (int i = 0; i < nums.length; i++) {
			System.out.println(nums[i]);
		}
	}

}

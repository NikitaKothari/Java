package sorting.searching;

public class QuickSort {

	public void quickSort(int[] nums, int low, int high) {
		if (nums == null || nums.length == 0)
			return;

		if (low >= high)
			return;

		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = nums[middle];

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (nums[i] < pivot) {
				i++;
			}

			while (nums[j] > pivot) {
				j--;
			}

			if (i <= j) {
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
				i++;
				j--;
			}
		}

		// recursively sort two sub parts
		if (low < j)
			quickSort(nums, low, j);

		if (high > i)
			quickSort(nums, i, high);
	}

	public void quickSort1(int[] nums, int low, int high) {
		int q = partition(nums, low, high);
		if (q != -1) {
			quickSort1(nums, low, q - 1);
			quickSort1(nums, q + 1, high);
		}
	}

	private int partition(int[] nums, int low, int high) {
		if (nums == null || nums.length == 0)
			return -1;

		if (low >= high)
			return -1;

		// pick the pivot
		int middle = low + (high - low) / 2;

		int temp = nums[middle];
		nums[middle] = nums[high];
		nums[high] = temp;

		int pivot = nums[high];

		// make left < pivot and right > pivot
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (nums[j] <= pivot) {
				i++;
				temp = nums[j];
				nums[j] = nums[i];
				nums[i] = temp;
			}
		}

		temp = nums[high];
		nums[high] = nums[i + 1];
		nums[i + 1] = temp;
		return i + 1;

	}

	public static void main(String[] args) {
		int[] nums = { 12, 11, -13, -5, 6, -7, 5, -3, -6 };
		QuickSort qs = new QuickSort();
		Utilities util = new Utilities();
		util.print(nums);
		qs.quickSort1(nums, 0, nums.length - 1);
		util.print(nums);
	}

}

package sorting.searching;

public class QuickSort {

	public void sort(int[] nums, int low, int high) {
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
			sort(nums, low, j);

		if (high > i)
			sort(nums, i, high);
	}

	public static void main(String[] args) {
		int[] nums = { 4, 6, 7, 9, 1, 3 };
		QuickSort qs = new QuickSort();
		Utilities util = new Utilities();
		util.print(nums);
		qs.sort(nums, 0, nums.length - 1);
		util.print(nums);
	}

}

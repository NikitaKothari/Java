package cs6301.g32.SP5;

public class QuickSort {

	public static <T extends Comparable<? super T>> void quickSort(T[] nums, int low, int high) {
		if (nums == null || nums.length == 0)
			return;

		if (low >= high)
			return;

		// pick the pivot
		int middle = low + (high - low) / 2;
		T pivot = nums[middle];

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (nums[i].compareTo(pivot) < 0) {
				i++;
			}

			while (nums[j].compareTo(pivot) > 0) {
				j--;
			}

			if (i <= j) {
				T temp = nums[i];
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

	public <T extends Comparable<? super T>> void quickSort1(T[] nums, int low, int high) {
		int q = partition(nums, low, high);
		if (q != -1) {
			quickSort1(nums, low, q - 1);
			quickSort1(nums, q + 1, high);
		}
	}

	private static <T extends Comparable<? super T>> int partition(T[] nums, int low, int high) {
		if (nums == null || nums.length == 0)
			return -1;

		if (low >= high)
			return -1;

		// pick the pivot
		int middle = low + (high - low) / 2;

		T temp = nums[middle];
		nums[middle] = nums[high];
		nums[high] = temp;

		T pivot = nums[high];

		// make left < pivot and right > pivot
		int i = low - 1;

		for (int j = low; j < high; j++) {
			if (nums[j].compareTo(pivot) <= 0) {
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

	public static <T extends Comparable<? super T>> void print(T[] generic) {
		for (int i = 0; i < generic.length; i++)
			System.out.print(generic[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) {
		Integer[] nums = { 12, 11, -13, -5, 6, -7, 5, -3, -6 };
		QuickSort qs = new QuickSort();
		print(nums);
		qs.quickSort(nums, 0, nums.length - 1);
		print(nums);
	}

}

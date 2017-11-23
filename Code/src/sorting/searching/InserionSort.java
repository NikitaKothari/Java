package sorting.searching;

public class InserionSort {

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

	public static <T extends Comparable<? super T>> void nSquareSort(T[] arr) {
		int n = arr.length;
		for (int j = 1; j < n; j++) {
			T key = arr[j];
			int i = j - 1;
			while ((i > -1) && (arr[i].compareTo(key) > 0)) {
				arr[i + 1] = arr[i];
				i--;
			}
			arr[i + 1] = key;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 4, 6, 7, 9, 1, 3 };
		Integer[] numsObject = { 4, 6, 7, 9, 1, 3 };
		Utilities util = new Utilities();
		util.print(nums);
		insertionSort(nums);
		nSquareSort(numsObject);
		util.print(nums);
		util.print(numsObject);
	}

}

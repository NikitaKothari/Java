package sorting.searching;

public class InserionSort {

	public void sort(int[] nums) {
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

	public static void main(String[] args) {
		int[] nums = { 4, 6, 7, 9, 1, 3 };
		InserionSort is = new InserionSort();
		Utilities util = new Utilities();
		util.print(nums);
		is.sort(nums);
		util.print(nums);
	}

}

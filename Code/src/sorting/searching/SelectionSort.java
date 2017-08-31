package sorting.searching;

public class SelectionSort {

	public void sort(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] < nums[index]) {
					index = j;// searching for lowest index
				}
			}
			int smallerNumber = nums[index];
			nums[index] = nums[i];
			nums[i] = smallerNumber;
		}
	}

	public static void main(String[] args) {
		int[] nums = { 4, 6, 7, 9, 1, 3 };
		SelectionSort ss = new SelectionSort();
		Utilities util = new Utilities();
		util.print(nums);
		ss.sort(nums);
		util.print(nums);
	}
}

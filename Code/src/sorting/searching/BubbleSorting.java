package sorting.searching;

public class BubbleSorting {

	public void sort(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] > nums[j]) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 4, 6, 7, 9, 1, 3 };
		BubbleSorting bs = new BubbleSorting();
		Utilities util = new Utilities();
		util.print(nums);
		bs.sort(nums);
		util.print(nums);
	}
}

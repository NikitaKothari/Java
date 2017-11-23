package sorting.searching;

public class CountingSort {

	public void sort(int[] nums) {
		int[] count = new int[10];
		for (int i = 0; i < nums.length; i++) {
			count[nums[i]]++;
		}
		int k = 0;
		for (int i = 0; i < count.length; i++) {
			while (count[i] > 0) {
				nums[k] = i;
				k++;
				count[i]--;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = { 4, 6, 7, 9, 1, 3 };
		CountingSort cs = new CountingSort();
		Utilities util = new Utilities();
		util.print(nums);
		cs.sort(nums);
		util.print(nums);
	}
}

package sorting.searching;

public class CountingSort {

	public void sort(int[] nums) {

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

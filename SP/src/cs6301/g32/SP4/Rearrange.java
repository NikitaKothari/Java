package cs6301.g32.SP4;

/**
 *
 * @author Nikita,Vinaya,Pradeep,Mohanakrishna
 */
public class Rearrange {

	/**
	 * @param arr
	 *            Reorder an int array A[] by moving negative elements to the
	 *            front, followed by its positive elements.
	 * @param low
	 *            Start index
	 * @param high
	 *            End index
	 */
	void arrange(int[] arr, int low, int high) {
		if (high > low) {
			int m = (low + high) / 2;
			arrange(arr, low, m);
			arrange(arr, m + 1, high);
			arrangeMinusPlus(arr, low, m, high);
		}
	}

	/**
	 * Placed all the negative element in front followed by positive numbers
	 * 
	 * @param arr
	 * @param low
	 * @param m
	 * @param high
	 */
	private void arrangeMinusPlus(int[] arr, int low, int m, int high) {
		for (int j = low + 1; j <= high; j++) {
			int key = arr[j];
			if (key < 0) {
				int i = j - 1;
				while ((i > -1) && (arr[i] > 0)) {
					arr[i + 1] = arr[i];
					i--;
				}
				arr[i + 1] = key;
			}
		}
	}

	void rearrangeMinusPlus(int[] arr) {
		if (arr.length == 0 || arr.length == 1)
			return;
		arrange(arr, 0, arr.length - 1);
	}

	public static void main(String[] args) {
		int[] nums = { 12, 11, -13, -5, 6, -7, 5, -3, -6 };
		Rearrange rearrange = new Rearrange();
		rearrange.rearrangeMinusPlus(nums);
		System.out.println("Rearranged Numbers ");
		for (int i = 0; i < nums.length; i++)
			System.out.print(nums[i] + " ");
	}
}

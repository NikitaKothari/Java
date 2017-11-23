package cs6301.g32.SP4;

/**
 *
 * @author Nikita,Vinaya,Pradeep,Mohanakrishna
 */
public class BinarySearch {

	/**
	 * Return the index of the largest element that is less than or equal to x.
	 * 
	 * @param arr
	 *            Sorted array Preconditions: arr[0..n-1] is sorted, and arr[0]
	 *            <= x < arr[n-1]
	 * @param x
	 *            Element index i such that arr[i] <= x < arr[i+1].
	 * 
	 * @return
	 * 
	 */
	public static <T extends Comparable<? super T>> int binarySearchRec(T[] arr, T x) {
		return binarySearch(arr, 0, arr.length, x);
	}

	private static <T extends Comparable<? super T>> int binarySearch(T[] arr, int low, int high, T x) {
		if (arr.length == 0)
			return -1;
		if (arr.length == 1)
			return 0;

		if (low <= high) {
			int mid = (high + low) / 2;
			if (arr[mid].compareTo(x) == 0)
				return mid;

			if (arr[mid].compareTo(x) > 0) {
				if (mid > 0 && arr[mid - 1].compareTo(x) < 0)
					return mid - 1;
				else
					return binarySearch(arr, low, mid - 1, x);
			} else if (arr[mid].compareTo(x) < 0) {
				if (mid < arr.length - 2 && arr[mid + 1].compareTo(x) > 0)
					return mid;
				else
					return binarySearch(arr, mid + 1, high, x);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Integer[] nums = { 1, 5, 6, 7, 9, 11, 13, 15 };
		System.out.println(binarySearchRec(nums, 12));
	}

}

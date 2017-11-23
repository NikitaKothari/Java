package cs6301.g32.SP7;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Pair {

	public static int findPair(int[] a, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int res = 0;
		for (int num : a) {
			int targetRemaining = k - num;
			if (map.containsKey(num)) {
				res += map.get(num);
			}
			if (map.containsKey(targetRemaining)) {
				map.put(targetRemaining, map.get(targetRemaining) + 1);
			} else
				map.put(targetRemaining, 1);
		}
		return res;
	}

	static String[] uniqueSubstrings(String inputString, int substringLength) {
		if (inputString == null || inputString.isEmpty())
			return null;
		Set<String> set = new TreeSet<>();
		int k = 0;
		while (k + 2 < inputString.length()) {
			String elem = inputString.substring(k, k + substringLength);
			if (!set.contains(elem)) {
				set.add(elem);
			}
			k++;
		}
		String[] strs = new String[set.size()];
		int i = 0;
		for (String elem : set) {
			strs[i++] = elem;
		}
		return strs;
	}

	public static void main(String[] args) {
		int[] nums = { 3, 3, 4, 5, 3, 5 };
		System.out.println(findPair(nums, 8));
	}

}

package templete.custom;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class MapOperations {

	public static <K, V extends Collection<V>> List<K> convertToList(Map<K, V> map) {
		return new ArrayList(map.keySet());
	}

	public static <K, V extends Collection<V>> void iterate_1(Map<K, V> map) {
		for (Entry entry : map.entrySet()) {
			// get key
			K key = (K) entry.getKey();
			// get value
			V value = (V) entry.getValue();
		}
	}

	public static <K, V extends Collection<V>> void iterate_2(Map<K, V> map) {
		Iterator itr = map.entrySet().iterator();
		while (itr.hasNext()) {
			Entry entry = (Entry) itr.next();
			// get key
			K key = (K) entry.getKey();
			// get value
			V value = (V) entry.getValue();
		}
	}

	public int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(k);
		for (int i : nums) {
			queue.offer(i);
			if (queue.size() > k)
				queue.poll();
		}
		return queue.peek();
	}

	public static void main(String[] args) {

	}
}

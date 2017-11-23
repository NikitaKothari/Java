
/** @author 
 *  Binary search tree map (starter code)
 *  Implement this class using one of the BST implementations: BST, AVLTree, RedBlackTree, or, SplayTree.
 *  Do not use TreeMap or any of Java's maps.
 **/

package cs6301.g32.SP7;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * 
 * @author nikita, Mohanakrishna, Nikita, Pradeep
 */

public class BSTMap<K extends Comparable<? super K>, V extends Comparable<? super V>>
		extends AVLTree<BSTMap.Item<K, V>> {

	/**
	 * 
	 * @author nikita, Mohanakrishna, Nikita, Pradeep
	 *
	 * @param <Item>
	 *            : Item contains key, value pair
	 */
	static class Entry<Item> extends AVLTree.Entry<Item> {
		private Entry(Item x, Entry<Item> left, Entry<Item> right) {
			super(x, left, right);
		}

		public Entry(Item x) {
			super(x);
		}
	}

	/**
	 * 
	 * @author nikita, Mohanakrishna, Nikita, Pradeep
	 *
	 * @param <T>
	 *            : Key
	 * @param <E>
	 *            : Value
	 */
	static class Item<T extends Comparable<? super T>, E> implements Comparable<Item> {
		T key;
		E value;

		Item(T x, E y) {
			this.key = x;
			this.value = y;
		}

		@Override
		public int compareTo(Item o) {
			return o.key.compareTo(this.key) * -1;
		}
	}

	BSTMap() {
	}

	/**
	 * Add key value to BST tree map
	 * 
	 * @param key
	 *            : Unique key
	 * @param value
	 *            : Value corresponding to key
	 * @return : returns the added value
	 */
	public boolean put(K key, V value) {
		Item t = new Item(key, value);
		return super.add(t);
	}

	@Override
	protected Entry createEntry(Item x) {
		return new Entry<>(x);
	}

	@Override
	protected Stack createStack() {
		return new Stack<Entry<Item>>();
	}

	/**
	 * Removes entry from BST tree map
	 * 
	 * @param key
	 * @return : Returns value corresponds to key
	 */
	public V removeAVLMap(K key) {
		Item t = new Item(key, 0);
		t = super.remove(t);
		return t == null ? null : (V) t.value;
	}

	/**
	 * Checks whether key is available or not
	 * 
	 * @param key
	 * @return : Returns true or false
	 */
	public boolean containsKey(K key) {
		Item t = new Item(key, 0);
		return contains(t);
	}

	/**
	 * Checks whether values is available or not
	 * 
	 * @param value
	 * @return : Returns true or false
	 */
	public boolean containsValue(V value) {
		Iterator<Item<K, V>> iterator = iterator();
		while (iterator.hasNext()) {
			V val = iterator.next().value;
			if (val.compareTo(value) == 0)
				return true;
		}
		return false;
	}

	/**
	 * @return : Returns smallest key
	 */
	public K firstKey() {
		Item t = min();
		return (K) t.key;
	}

	/**
	 * @return : Returns largest key
	 */
	public K lastKey() {
		Item t = max();
		return (K) t.key;
	}

	/**
	 * 
	 * @param key
	 * @return : Returns values corresponds to key
	 */
	public V getValue(K key) {
		Item t = new Item(key, 0);
		t = super.get(t);
		if (t != null)
			return (V) t.value;
		return null;
	}

	/**
	 * Add all map values to BST tree map
	 * 
	 * @param map
	 */
	void putAll(Map<K, V> map) {
		for (K key : map.keySet()) {
			V value = map.get(key);
			put(key, value);
		}
	}

	/**
	 * @return : Returns set of all keys
	 */
	Set<K> entrySet() {
		Set<K> set = new TreeSet<>();
		Iterator<Item<K, V>> iterator = iterator();
		while (iterator.hasNext())
			set.add(iterator.next().key);
		return set;
	}

	/**
	 * @return : returns sizes
	 */
	int size() {
		return super.size;
	}

	@Override
	public void printTree() {
		System.out.print("[" + size + "]");
		this.printTreeAVLMap((Entry<Item<K, V>>) root);
		System.out.println();
	}

	// Inorder traversal of tree
	void printTreeAVLMap(Entry<Item<K, V>> node) {
		if (node != null) {
			printTreeAVLMap((Entry<Item<K, V>>) node.left);
			System.out.print(" " + node.element.key + "->" + node.element.value);
			printTreeAVLMap((Entry<Item<K, V>>) node.right);
		}
	}

	public static void main(String[] args) {
		BSTMap<Integer, Integer> t = new BSTMap<>();
		Scanner in = new Scanner(System.in);
		int y = 0;
		while (in.hasNext()) {
			int x = in.nextInt();
			if (x > 0)
				y = in.nextInt();
			if (x > 0) {
				System.out.print("Add " + x + " : ");
				t.put(x, y);
				t.printTree();
				t.lastKey();
			} else if (x < 0) {
				y = t.removeAVLMap(-x);
				System.out.print("Remove " + x);
				t.printTree();
			} else {
				Comparable[] arr = t.toArray();
				System.out.print("Final: ");
				for (int i = 0; i < t.size; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
				return;
			}
		}

	}
}

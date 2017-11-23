/** @author 
 *  Binary search tree (starter code)
 **/

package cs6301.g32.SP7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> implements Iterable<T> {
	static class Entry<T> {
		T element;
		Entry<T> left, right;

		Entry(T x, Entry<T> left, Entry<T> right) {
			this.element = x;
			this.left = left;
			this.right = right;
		}

		Entry(T x) {
			this.element = x;
		}
	}

	protected Entry<T> createEntry(T x) {
		return new Entry<>(x);
	}

	protected Stack createStack() {
		return new Stack<Entry<T>>();
	}

	Entry<T> root;
	int size;

	public BST() {
		root = null;
		size = 0;
	}

	public Stack<Entry<T>> stack;

	/**
	 * 
	 * @param x
	 * @return
	 */
	Entry<T> find(T x) {
		stack = createStack();
		stack.push(null);
		return find(root, x);
	}

	/**
	 * 
	 * @param t
	 * @param x
	 * @return
	 */
	Entry<T> find(Entry<T> t, T x) {
		if (t == null || t.element.compareTo(x) == 0) {
			return t;
		}
		OUTER: while (true) {
			switch (x.compareTo(t.element)) {
			case -1:
				if (t.left == null) {
					break OUTER;
				} else {
					stack.push(t);
					t = t.left;
				}
				break;
			case 0:
				break OUTER;
			default:
				if (t.right == null) {
					break OUTER;
				} else {
					stack.push(t);
					t = t.right;
				}
			}
		}
		return t;
	}

	/**
	 * @param x
	 * @return
	 */
	public boolean contains(T x) {
		Entry<T> t = find(x);
		return (t != null && t.element == x);
	}

	/**
	 * TO DO: Is there an element that is equal to x in the tree? Element in
	 * tree that is equal to x is returned, null otherwise.
	 * 
	 * @param x
	 * @return
	 */
	public T get(T x) {
		Entry<T> t = find(x);
		return (t != null && t.element.compareTo(x) == 0) ? t.element : null;
	}

	/**
	 * TO DO: Add x to tree. If tree contains a node with same key, replace
	 * element by x. Returns true if x is a new element added to tree.
	 * 
	 * @param x
	 * @return
	 */
	public boolean add(T x) {
		if (root == null) {
			root = createEntry(x);
		} else {
			Entry<T> t = find(x);
			switch (x.compareTo(t.element)) {
			case 0:
				t.element = x;
				return false;
			case -1:
				t.left = createEntry(x);
				break;
			default:
				t.right = createEntry(x);
				break;
			}
		}
		size++;
		return true;
	}

	/**
	 * TO DO: Remove x from tree. Return x if found, otherwise return null
	 * 
	 * @param x
	 * @return
	 */
	public T remove(T x) {
		if (root == null)
			return null;
		Entry<T> t = find(x);
		if (t.element.compareTo(x) != 0)
			return null;
		T result = t.element;
		if (t.left == null || t.right == null) {
			bypass(t);
		} else {
			stack.push(t);
			Entry<T> minRight = find(t.right, t.element);
			t.element = minRight.element;
			bypass(minRight);
		}
		size--;
		return result;
	}

	void bypass(Entry<T> t) {
		Entry<T> pt = stack.peek();
		Entry<T> c = t.left == null ? t.right : t.left;
		if (pt == null)
			root = c;
		else if (pt.left == t)
			pt.left = c;
		else
			pt.right = c;
	}

	/**
	 * 
	 * @return
	 */
	public T max() {
		if (root == null)
			return null;
		Entry<T> t = root;
		while (t.right != null) {
			t = t.right;
		}
		return t.element;
	}

	/**
	 * 
	 * @return
	 */
	public T min() {
		if (root == null)
			return null;
		Entry<T> t = root;
		while (t.left != null) {
			t = t.left;
		}
		return t.element;
	}

	List<T> sortedList;

	void inOrderTraversal(Entry<T> root) {
		if (root != null) {
			inOrderTraversal(root.left);
			visited(root);
			inOrderTraversal(root.right);
		}
	}

	void visited(Entry<T> x) {
		sortedList.add(x.element);
	}

	/**
	 * TO DO: Iterate elements in sorted order of keys
	 */
	@Override
	public Iterator<T> iterator() {
		sortedList = new ArrayList<>();
		inOrderTraversal(root);
		return sortedList.iterator();
	}

	public static void main(String[] args) {
		BST<Integer> t = new BST<>();
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int x = in.nextInt();
			if (x > 0) {
				System.out.print("Add " + x + " : ");
				t.add(x);
				t.printTree();
			} else if (x < 0) {
				System.out.print("Remove " + x + " : ");
				t.remove(-x);
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

	// TODO: Create an array with the elements using in-order traversal of tree
	public Comparable[] toArray() {
		Comparable[] arr = new Comparable[size];
		int i = 0;
		Iterator it = iterator();
		while (it.hasNext()) {
			arr[i++] = (Comparable) it.next();
		}

		return arr;
	}

	public void printTree() {
		System.out.print("[" + size + "]");
		printTree(root);
		System.out.println();
	}

	// Inorder traversal of tree
	void printTree(Entry<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.print(" " + node.element);
			printTree(node.right);
		}
	}

}
/*
 * Sample input: 1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0
 * 
 * Output: Add 1 : [1] 1 Add 3 : [2] 1 3 Add 5 : [3] 1 3 5 Add 7 : [4] 1 3 5 7
 * Add 9 : [5] 1 3 5 7 9 Add 2 : [6] 1 2 3 5 7 9 Add 4 : [7] 1 2 3 4 5 7 9 Add 6
 * : [8] 1 2 3 4 5 6 7 9 Add 8 : [9] 1 2 3 4 5 6 7 8 9 Add 10 : [10] 1 2 3 4 5 6
 * 7 8 9 10 Remove -3 : [9] 1 2 4 5 6 7 8 9 10 Remove -6 : [8] 1 2 4 5 7 8 9 10
 * Remove -3 : [8] 1 2 4 5 7 8 9 10 Final: 1 2 4 5 7 8 9 10
 * 
 */

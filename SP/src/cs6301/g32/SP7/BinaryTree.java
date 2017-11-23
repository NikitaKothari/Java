package cs6301.g32.SP7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class BinaryTree<T extends Comparable<? super T>> {
	class Entry<T extends Comparable<? super T>> {
		T element;
		Entry<T> left, right;

		public Entry(T element) {
			this.element = element;
			this.left = null;
			this.right = null;
		}
	}

	Entry<T> root;
	int size;
	Stack<Entry<T>> stack;

	public Entry<T> find(T x) {
		stack = new Stack<>();
		return find(root, x);
	}

	private Entry<T> find(Entry<T> t, T x) {
		if (t == null || t.element.compareTo(x) == 0)
			return t;
		while (true) {
			if (t.element.compareTo(x) > 0) {
				if (t.left == null)
					break;
				stack.push(t);
				t = t.left;
			}
			if (t.element.compareTo(x) == 0)
				break;
			else {
				if (t.right == null)
					break;
				stack.push(t);
				t = t.right;
			}
		}
		return t;
	}

	public boolean contains(T x) {
		Entry<T> t = find(x);
		return t != null && t.element.compareTo(x) == 0;
	}

	public T min() {
		if (root == null)
			return null;
		Entry<T> t = root;
		while (t.left != null)
			t = t.left;
		return t.element;
	}

	public T max() {
		if (root == null)
			return null;
		Entry<T> t = root;
		while (t.right != null)
			t = t.right;
		return t.element;
	}

	public boolean add(T x) {
		if (root == null) {
			root = new Entry<>(x);
			size = 1;
			return true;
		}
		Entry<T> t = find(x);
		if (x == t.element) {
			t.element = x;
			return false;
		} else if (t.element.compareTo(x) > 0)
			t.left = new Entry<>(x);
		else
			t.right = new Entry<>(x);
		size++;
		return true;
	}

	public T remove(T x) {
		if (root == null)
			return null;
		Entry<T> t = find(x);
		if (t == null)
			return null;
		T result = t.element;
		if (t.left == null || t.right == null)
			bypass(t);
		else {
			stack.push(t);
			Entry<T> minRight = find(t.right, t.element);
			t.element = minRight.element;
			bypass(minRight);
		}
		size--;
		return result;
	}

	private void bypass(Entry<T> t) {

		Entry<T> pt = !stack.isEmpty() ? stack.peek() : null;
		Entry<T> c = t.left == null ? t.right : t.left;
		if (pt == null)
			root = c;
		else if (pt.left == t)
			pt.left = c;
		else
			pt.right = c;
	}

	public T get(T x) {
		Entry<T> t = find(x);
		if (t != null && t.element.equals(x))
			return t.element;
		else
			return null;
	}

	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Entry<T> root) {
		if (root != null) {
			System.out.println(root.element);
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(Entry<T> root) {
		if (root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.println(root.element);
		}
	}

	void inOrder() {
		inOrder(root);
	}

	private void inOrder(Entry<T> root) {
		if (root != null) {
			inOrder(root.left);
			System.out.println(root.element);
			inOrder(root.right);
		}
	}

	private void inOder(Entry<T> root, ArrayList<Entry<T>> list) {
		if (root != null) {
			inOder(root.left, list);
			list.add(root);
			inOder(root.right, list);
		}
	}

	private void inOrderStack() {
		if (root != null) {
			Stack<Entry<T>> stck = new Stack<>();
			Entry<T> init = root;
			while (!stck.isEmpty() || init != null) {
				if (init != null) {
					stck.add(init);
					init = init.left;
				} else {
					init = stck.pop();
					System.out.print(init.element + " ");
					init = init.right;
				}
			}
		}
	}

	private void preOrderStack() {
		if (root != null) {
			Stack<Entry<T>> stck = new Stack<>();
			Entry<T> init = root;
			stck.add(root);
			while (!stck.isEmpty()) {
				Entry<T> t = stck.pop();
				System.out.println(t.element);
				if (t.left != null)
					stck.push(t.left);
				if (t.right != null)
					stck.push(t.right);
			}
		}
	}

	private void postOrderStack() {
		if (root != null) {
			Stack<Entry<T>> stck = new Stack<>();
			Entry<T> init = root;
			stack.push(root);
			while (!stck.isEmpty()) {

			}
		}
	}

	public int height(Entry<T> u) {
		if (u == null)
			return -1;
		int left = height(u.left);
		int right = height(u.right);
		return 1 + Math.max(left, right);
	}

	public Iterator<Entry<T>> iterator() {
		ArrayList<Entry<T>> list = new ArrayList<>();
		inOder(root, list);
		return list.iterator();
	}

	static <T extends Comparable<? super T>> T[] exactlyOnce(T[] A) {
		if (A == null)
			return null;
		BinaryTree<T> bt = new BinaryTree<>();
		for (T element : A) {
			if (bt.find(element).element != null)
				bt.remove(element);
			else
				bt.add(element);
		}
		return null;
	}

	public void getTree(T[] nums) {
		root = buildTree(nums, 0, nums.length - 1);
	}

	private Entry<T> buildTree(T[] nums, int low, int high) {
		if (low > high)
			return null;
		int mid = (high + low) / 2;
		Entry<T> node = new Entry<T>(nums[mid]);
		node.left = buildTree(nums, low, mid - 1);
		node.right = buildTree(nums, mid + 1, high);
		return node;
	}

	T closest(Entry<T> tree, T k) {
		Entry<T> t = find(k);
		if (t != null && t.element.compareTo(k) == 0) {
			return k;
		}
		return stack.pop().element;
	}

	class Index {
		int index;
	}

	public void reconstruct(T[] inOrder, T[] postOrder) {
		Index index = new Index();
		index.index = postOrder.length - 1;
		root = reconstruct(inOrder, 0, inOrder.length - 1, postOrder, index);
	}

	Entry<T> reconstruct(T[] inOrder, int low, int high, T[] postOrder, Index index) {
		if (index.index < 0 || low > high)
			return null;
		Entry<T> tree = new Entry<>(postOrder[index.index]);
		index.index--;
		if (low == high)
			return tree;
		int iIndex = search(inOrder, low, high, tree.element);
		tree.right = reconstruct(inOrder, iIndex + 1, high, postOrder, index);
		tree.left = reconstruct(inOrder, low, iIndex - 1, postOrder, index);
		return tree;
	}

	int search(T arr[], int strt, int end, T value) {
		int i;
		for (i = strt; i <= end; i++) {
			if (arr[i].compareTo(value) == 0)
				break;
		}
		return i;
	}

	public static void main(String[] args) {
		BinaryTree<Integer> tree = new BinaryTree<>();
		Integer in[] = { 4, 8, 2, 5, 1, 6, 3, 7 };
		Integer post[] = { 8, 4, 5, 2, 6, 7, 3, 1 };
		tree.reconstruct(in, post);
		tree.preOrder();

	}
}

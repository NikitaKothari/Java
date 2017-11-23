
/** Starter code for AVL Tree
 */
package cs6301.g32.SP7;

import java.util.Scanner;
import java.util.Stack;

public class AVLTree<T extends Comparable<? super T>> extends BST<T> {
	static class Entry<T> extends BST.Entry<T> {
		int height;

		Entry(T x, Entry<T> left, Entry<T> right) {
			super(x, left, right);
			height = 0;
		}

		Entry(T x) {
			super(x);
			height = 0;
		}
	}

	int height(Entry<T> x) {
		if (x == null)
			return -1;
		return x.height;
	}

	AVLTree() {
		super();
	}

	@Override
	protected Entry<T> createEntry(T x) {
		return new Entry<>(x);
	}

	@Override
	protected Stack createStack() {
		return new Stack<Entry<T>>();
	}

	@Override
	public boolean add(T x) {
		super.add(x);
		restore(x);
		return true;
	}

	int updateHeight(Entry<T> node) {
		if (node != null) {
			node.height = Math.max(updateHeight((Entry<T>) node.left), updateHeight((Entry<T>) node.right)) + 1;
		} else {
			return -1;
		}
		return node.height;
	}

	void restore(T x) {
		if (stack != null) {
			if (stack.size() == 1)
				((Entry<T>) root).height = Math.max(height((Entry<T>) root.left), height((Entry<T>) root.right)) + 1;
			else {
				updateHeight((Entry<T>) stack.peek());
				Entry<T> prev = null;
				while (!stack.isEmpty()) {
					Entry<T> temp = (Entry<T>) stack.pop();

					if (prev != null) {
						if (temp == null) {
							root = prev;
						} else if (prev.element.compareTo(temp.element) > 0) {
							temp.right = prev;
						} else if (prev.element.compareTo(temp.element) < 0) {
							temp.left = prev;
						}
					}
					if (temp != null) {
						temp.height = Math.max(height((Entry<T>) temp.left), height((Entry<T>) temp.right)) + 1;
						if (Math.abs(height((Entry<T>) temp.left) - height((Entry<T>) temp.right)) >= 2) {
							if (height((Entry<T>) temp.left) > height((Entry<T>) temp.right)) {
								if (temp.left.left != null)
									temp = rotateFromLeft(temp);
								else
									temp = doubleRotationLR(temp);
							} else {
								if (temp.right.right != null)
									temp = rotateFromRight(temp);
								else
									temp = doubleRotationRL(temp);
							}
						}
						prev = temp;

					}

				}

			}
		}
	}

	public T remove(T x) {
		T result = super.remove(x);
		if (root != null)
			restore(x);
		return result;
	}

	private Entry<T> rotateFromRight(Entry<T> node) {
		Entry<T> temp = (Entry<T>) node.right;
		node.right = temp.left;
		temp.left = node;
		node.height = Math.max(height((Entry<T>) node.left), height((Entry<T>) node.right)) + 1;
		temp.height = Math.max(height((Entry<T>) temp.left), height((Entry<T>) temp.right)) + 1;
		return temp;
	}

	private Entry<T> rotateFromLeft(Entry<T> node) {
		Entry<T> temp = (Entry<T>) node.left;
		node.left = temp.right;
		temp.right = node;
		node.height = Math.max(height((Entry<T>) node.left), height((Entry<T>) node.right)) + 1;
		temp.height = Math.max(height((Entry<T>) temp.left), height((Entry<T>) temp.right)) + 1;
		return temp;
	}

	private Entry<T> doubleRotationRL(Entry<T> node) {
		node.right = rotateFromLeft((Entry<T>) node.right);
		return rotateFromRight(node);
	}

	private Entry<T> doubleRotationLR(Entry<T> node) {
		node.left = rotateFromRight((Entry<T>) node.left);
		return rotateFromLeft(node);
	}

	@Override
	public void printTree() {
		System.out.print("[" + size + "]");
		this.printTreeAVL((Entry<T>) root);
		System.out.println();
	}

	// Inorder traversal of tree
	void printTreeAVL(Entry<T> node) {
		if (node != null) {
			printTreeAVL((Entry<T>) node.left);
			System.out.print(" " + node.element + ":(" + node.height + ")");
			printTreeAVL((Entry<T>) node.right);
		}
	}

	public static void main(String[] args) {
		AVLTree<Integer> t = new AVLTree<>();
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

}

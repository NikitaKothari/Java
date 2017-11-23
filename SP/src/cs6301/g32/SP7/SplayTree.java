package cs6301.g32.SP7;

import java.util.Scanner;

/**
 * 
 * @author nikita, Mohanakrishna, Nikita, Pradeep
 */
public class SplayTree<T extends Comparable<? super T>> extends BST<T> {

	/**
	 * Restores the node
	 * 
	 * @param node
	 */
	private void splay(Entry<T> node) {
		if (node == null || root == null)
			return;
		if (node == root)
			return;

		while (!stack.isEmpty() && stack.peek() != null) {
			Entry<T> parent1 = stack.pop();
			Entry<T> parent2 = null;

			if (parent1.element.compareTo(node.element) > 0) {
				parent1.left = node;
			}
			if (parent1.element.compareTo(node.element) < 0) {
				parent1.right = node;
			}

			if (!stack.isEmpty())
				parent2 = stack.pop();

			if (parent2 == null) {
				if (parent1.element.compareTo(node.element) > 0) {
					node = zigL(parent1);
				}
				if (parent1.element.compareTo(node.element) < 0) {
					node = zigR(parent1);
				}
			} else {
				if (node.element.compareTo(parent1.element) < 0 && parent1.element.compareTo(parent2.element) < 0) {
					// LL
					node = zigZigLL(parent2);
				} else if (node.element.compareTo(parent1.element) > 0
						&& parent1.element.compareTo(parent2.element) > 0) {
					// RR
					node = zigZigRR(parent2);
				} else if (node.element.compareTo(parent1.element) < 0
						&& parent1.element.compareTo(parent2.element) > 0) {
					// RL
					node = zigZagRL(parent2);
				} else if (node.element.compareTo(parent1.element) > 0
						&& parent1.element.compareTo(parent2.element) < 0) {
					// LR
					node = zigZagLR(parent2);
				}
			}
		}
		root = node;
	}

	/**
	 * Rotate left
	 * 
	 * @param node
	 * @return
	 */
	private Entry<T> zigL(Entry<T> node) {
		Entry<T> temp = (Entry<T>) node.left;
		node.left = temp.right;
		temp.right = node;
		return temp;
	}

	/**
	 * Rotate right
	 * 
	 * @param node
	 * @return
	 */
	private Entry<T> zigR(Entry<T> node) {
		Entry<T> temp = (Entry<T>) node.right;
		node.right = temp.left;
		temp.left = node;
		return temp;
	}

	/**
	 * Rotate left right
	 * 
	 * @param node
	 * @return
	 */
	private Entry<T> zigZagLR(Entry<T> node) {
		node.left = zigR(node.left);
		return zigL(node);
	}

	/**
	 * Rotate right left
	 * 
	 * @param node
	 * @return
	 */
	private Entry<T> zigZagRL(Entry<T> node) {
		node.right = zigL(node.right);
		return zigR(node);
	}

	/**
	 * Rotate left left
	 * 
	 * @param node
	 * @return
	 */
	private Entry<T> zigZigLL(Entry<T> node) {
		node = zigL(node);
		return zigL(node);
	}

	/**
	 * rotate right right
	 * 
	 * @param node
	 * @return
	 */
	private Entry<T> zigZigRR(Entry<T> node) {
		node = zigR(node);
		return zigR(node);
	}

	@Override
	public boolean add(T x) {
		boolean isadded = super.add(x);
		if (isadded) {
			splay(find(x));
		}
		return isadded;
	}

	@Override
	public T get(T x) {
		T element = super.get(x);
		if (element != null)
			splay(find(x));
		else {
			if (!stack.isEmpty() && stack.peek() != null) {
				Entry<T> parent = stack.peek();
				if (parent.element.compareTo(x) > 0)
					splay(parent.left);
				else
					splay(parent.right);
			}
		}
		return element;
	}

	@Override
	public T remove(T x) {
		T element = super.remove(x);
		if (!stack.isEmpty() && stack.peek() != null) {
			if (element == null) {
				Entry<T> parent = stack.peek();
				if (parent.element.compareTo(x) > 0)
					splay(parent.left);
				else
					splay(parent.right);

			} else {
				splay(stack.pop());
			}
		}
		return element;
	}

	@Override
	public T min() {
		T element = super.min();
		if (element != null)
			splay(find(element));
		return element;
	}

	@Override
	public T max() {
		T element = super.max();
		if (element != null)
			splay(find(element));
		return element;
	}

	@Override
	public boolean contains(T x) {
		boolean iscontained = super.contains(x);
		if (iscontained)
			splay(find(x));
		else {
			if (!stack.isEmpty() && stack.peek() != null) {
				Entry<T> parent = stack.peek();
				if (parent.element.compareTo(x) > 0)
					splay(parent.left);
				else
					splay(parent.right);
			}
		}
		return iscontained;
	}

	public void preOrder() {
		preOrder(root);
		System.out.println();
	}

	private void preOrder(Entry<T> root) {
		if (root != null) {
			System.out.print(root.element + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public static void main(String[] args) {
		SplayTree<Integer> t = new SplayTree<>();
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

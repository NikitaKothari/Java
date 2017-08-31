package templete.custom;

public class TreeTemplate<E> {
	public E val;
	public TreeTemplate<E> right, left;

	public TreeTemplate(E val) {
		this.val = val;
		right = null;
		left = null;
	}

	int maxDepth(TreeTemplate<E> tree) {
		if (tree == null)
			return 0;
		else {
			int lDepth = maxDepth(tree.left);
			int rDepth = maxDepth(tree.right);

			if (lDepth > rDepth)
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}
	}

	void printPreOrder(TreeTemplate<E> tree) {
		if (tree != null) {
			System.out.println(tree.val);
			printPreOrder(tree.left);
			printPreOrder(tree.right);
		}
	}

	void printPostOrder(TreeTemplate<E> tree) {
		if (tree != null) {
			printPostOrder(tree.left);
			printPostOrder(tree.right);
			System.out.println(tree.val);
		}
	}

	void printInOrder(TreeTemplate<E> tree) {
		if (tree != null) {
			printInOrder(tree.left);
			System.out.println(tree.val);
			printInOrder(tree.right);
		}
	}
}

package ctci.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import templete.custom.TreeTemplate;

public class TreeSol {
	TreeTemplate<Integer> createMinimumBST(int array[]) {
		return createMinimumBST(array, 0, array.length - 1);
	}

	TreeTemplate<Integer> createMinimumBST(int array[], int start, int end) {
		if (start > end)
			return null;
		int mid = (start + end) / 2;
		TreeTemplate<Integer> n = new TreeTemplate<Integer>(array[mid]);
		n.left = createMinimumBST(array, start, mid - 1);
		n.right = createMinimumBST(array, mid + 1, end);
		return n;
	}

	void createLevelLinkedList(TreeTemplate<Integer> root, ArrayList<LinkedList<TreeTemplate<Integer>>> lists,
			int level) {
		if (root == null)
			return;

		LinkedList<TreeTemplate<Integer>> list = null;
		if (lists.size() == level) {
			list = new LinkedList<>();
			lists.add(list);
		} else {
			list = lists.get(level);
		}
		list.add(root);
		createLevelLinkedList(root.left, lists, level + 1);
		createLevelLinkedList(root.right, lists, level + 1);
	}

	ArrayList<LinkedList<TreeTemplate<Integer>>> createLevelLinkedList(TreeTemplate<Integer> root) {
		ArrayList<LinkedList<TreeTemplate<Integer>>> lists = new ArrayList<>();
		LinkedList<TreeTemplate<Integer>> list = new LinkedList<>();
		if (root != null) {
			list.add(root);
		}
		while (list.size() > 0) {
			lists.add(list);
			LinkedList<TreeTemplate<Integer>> parent = list;
			list = new LinkedList<>();
			for (TreeTemplate<Integer> node : parent) {
				if (node.left != null)
					list.add(node.left);
				if (node.right != null)
					list.add(node.right);
			}
		}
		return lists;
	}

	int checkHeight(TreeTemplate<Integer> root) {
		if (root == null)
			return -1;
		int leftHeight = checkHeight(root.left);
		if (leftHeight == Integer.MIN_VALUE)
			return Integer.MIN_VALUE;

		int rightHeight = checkHeight(root.right);
		if (rightHeight == Integer.MIN_VALUE)
			return Integer.MAX_VALUE;

		if (Math.abs(leftHeight - rightHeight) > 1)
			return Integer.MIN_VALUE;
		else
			return Math.max(leftHeight, rightHeight) + 1;

	}

	boolean isBalance(TreeTemplate<Integer> root) {
		return checkHeight(root) == Integer.MIN_VALUE;
	}

	boolean isValidBST(TreeTemplate<Integer> root, Integer min, Integer max) {
		if (root == null)
			return true;
		if ((min != null && root.val <= min) || (max != null && root.val > max))
			return false;
		if (!isValidBST(root.left, root.val, max) || !isValidBST(root.right, min, root.val))
			return false;
		return true;
	}

	TreeTemplate<Integer> lowestCommonAncestor(TreeTemplate<Integer> root, TreeTemplate<Integer> n1,
			TreeTemplate<Integer> n2) {
		if (root == null || n1 == null || n2 == null)
			return null;
		if (root == n1 || root == n2)
			return root;

		TreeTemplate<Integer> left = lowestCommonAncestor(root.left, n1, n2);
		TreeTemplate<Integer> right = lowestCommonAncestor(root.right, n1, n2);

		if (left != null && right != null)
			return root;

		if (left == null && right == null)
			return null;

		return left != null ? left : right;
	}

	int countPathWithSum(TreeTemplate<Integer> root, int targetSum, int runningSum,
			HashMap<Integer, Integer> pathCount) {
		if (root == null)
			return 0;
		runningSum += root.val;
		int sum = targetSum - runningSum;
		int totalPath = pathCount.getOrDefault(sum, 0);
		if (targetSum == runningSum)
			totalPath++;
		increamentHashCout(pathCount, runningSum, 1);
		totalPath += countPathWithSum(root.left, targetSum, runningSum, pathCount);
		totalPath += countPathWithSum(root.right, targetSum, runningSum, pathCount);
		increamentHashCout(pathCount, runningSum, -1);

		return totalPath;
	}

	void increamentHashCout(HashMap<Integer, Integer> map, int key, int delta) {
		int newCount = map.get(key) + delta;
		if (newCount == 0)
			map.remove(key);
		else
			map.put(key, newCount);
	}

}

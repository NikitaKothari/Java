package leetcode.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class Test {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> lists = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		int size = 1;
		boolean order = false;
		queue.add(root);

		while (!queue.isEmpty()) {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (order) {
					list.add(node.val);
				} else {
					list.add(0, node.val);
				}
				if (node.left != null)
					queue.add(node.left);
				if (node.right != null)
					queue.add(node.right);
			}
			lists.add(list);
			size = queue.size();
			order = !order;
		}
		return lists;
	}

	public int findPeakElement(int[] nums) {
		if (nums == null || nums.length == 0)
			return Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if ((i - 1) > nums.length || nums[i] > nums[i - 1])
				if ((i + 1) > nums[i] || nums[i] >= nums[i + 1])
					return i;
		}
		return Integer.MIN_VALUE;
	}

}

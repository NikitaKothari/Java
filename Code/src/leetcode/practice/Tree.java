package leetcode.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Tree {

	public List<Double> averageOfLevels(TreeNode root) {
		List<Double> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (root == null)
			return result;
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			double sum = 0;
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				sum += node.val;
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
			}
			result.add(sum / size);
		}
		return result;
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new LinkedList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (root == null)
			return result;
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				list.add(node.val);
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
			}
			// result.add(0, list); reverse
			result.add(list);
		}
		return result;
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (root == null)
			return result;
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean order = true;
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (order)
					list.add(node.val);
				else
					list.add(0, node.val);
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
			}
			// result.add(0, list); reverse
			result.add(list);
			order = order ? false : true;
		}
		return result;
	}

	public List<Integer> largestValues(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		if (root == null)
			return result;
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			int largestValue = queue.peek().val;
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				largestValue = Math.max(largestValue, node.val);
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
			}
			result.add(largestValue);
		}
		return result;
	}

	public void postorder(List<Integer> res, TreeNode root) {
		if (root == null)
			return;
		if (root.left != null)
			preorder(res, root.left);
		if (root.right != null)
			preorder(res, root.right);
		res.add(root.val);
	}

	public void inorder(List<Integer> res, TreeNode root) {
		if (root == null)
			return;
		if (root.left != null)
			inorder(res, root.left);
		res.add(root.val);
		if (root.right != null)
			inorder(res, root.right);
	}

	public void preorder(List<Integer> res, TreeNode root) {
		if (root == null)
			return;
		res.add(root.val);
		if (root.left != null)
			preorder(res, root.left);
		if (root.right != null)
			preorder(res, root.right);
	}

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null)
			return res;
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			root = stack.pop();
			res.add(root.val);
			if (root.right != null)
				stack.push(root.right);
			if (root.left != null)
				stack.push(root.left);
		}
		return res;
	}

	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		postorder(res, root);
		return res;
	}

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> answer = new ArrayList<String>();
		if (root != null)
			searchBT(root, "", answer);
		return answer;
	}

	private void searchBT(TreeNode root, String path, List<String> answer) {
		if (root.left == null && root.right == null)
			answer.add(path + root.val);
		if (root.left != null)
			searchBT(root.left, path + root.val + "->", answer);
		if (root.right != null)
			searchBT(root.right, path + root.val + "->", answer);
	}

	public int findBottomLeftValue(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			root = queue.poll();
			if (root.right != null)
				queue.offer(root.right);
			if (root.left != null)
				queue.offer(root.left);
		}
		return root.val;
	}

}

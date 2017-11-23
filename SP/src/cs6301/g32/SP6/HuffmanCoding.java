package cs6301.g32.SP6;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 
 * @author nikita, Mohanakrishna, Nikita, Pradeep
 */
class Tree implements Comparable<Tree> {
	float val;
	char name;
	Tree left;
	Tree right;

	public Tree(Float val, char name) {
		this.val = val;
		this.name = name;
		this.left = null;
		this.right = null;
	}

	@Override
	public int compareTo(Tree t1) {
		if (this.val > t1.val)
			return 1;
		if (this.val < t1.val)
			return -1;
		return 0;
	}
}

public class HuffmanCoding {

	float ans = 0;

	/**
	 * construct huffman tree
	 * 
	 * @param freq
	 *            : Frequency of each character
	 * @param characters
	 *            : All corresponding characters
	 * @return Returns root of tree
	 */
	public Tree constructTree(float[] freq, char[] characters) {
		PriorityQueue<Tree> queue = new PriorityQueue<>();
		for (int i = 0; i < freq.length; i++) {
			Tree tree = new Tree(freq[i], characters[i]);
			queue.add(tree);
		}
		while (queue.size() > 1) {
			Tree t1 = queue.remove();
			Tree t2 = queue.remove();
			Tree t3 = new Tree(t1.val + t2.val, 'x');
			if (t1.val > t2.val) {
				t3.left = t2;
				t3.right = t1;
			} else {
				t3.left = t1;
				t3.right = t2;
			}
			queue.add(t3);
		}
		return queue.remove();
	}

	void inorder(Tree t, String path) {
		if (t != null) {
			if (t.left == null && t.right == null) {
				System.out.println(t.name + " " + t.val + " " + path);
				ans += path.length() * t.val;
			}
			inorder(t.left, path + "0");
			inorder(t.right, path + "1");
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of characters ");
		int n = sc.nextInt();
		System.out.println("Enter freq followed by character ");
		float[] freq = new float[n];
		char[] chars = new char[n];
		for (int i = 0; i < n; i++) {
			freq[i] = sc.nextFloat();
			chars[i] = sc.next().charAt(0);
		}

		HuffmanCoding h = new HuffmanCoding();
		Tree t = h.constructTree(freq, chars);
		System.out.println("______________");
		h.inorder(t, "");
		System.out.println("______________");

		System.out.println("Answer is " + h.ans);
	}
	/**
	 * Enter number of characters 5 Enter freq followed by character 0.2 a 0.1 b
	 * 0.15 c 0.3 d 0.25 e ______________ a 0.2 00 b 0.1 010 c 0.15 011 e 0.25
	 * 10 d 0.3 11 ______________ Answer is 2.25
	 */
}

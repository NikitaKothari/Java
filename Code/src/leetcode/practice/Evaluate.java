package leetcode.practice;

import java.util.Stack;

public class Evaluate {

	public static int prostfix(String s) {
		char[] chars = s.toCharArray();
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == '+' || c == '-' || c == '/' || c == '*') {
				int a2 = stack.pop();
				int a1 = stack.pop();
				int ans = 0;
				switch (c) {
				case '+':
					ans = a1 + a2;
					break;
				case '-':
					ans = a1 + a2;
					break;
				case '*':
					ans = a1 * a2;
					break;
				case '/':
					ans = a1 / a2;
					break;
				default:
					break;
				}
				stack.push(ans);
			} else
				stack.push(Character.getNumericValue(c));
		}
		return stack.pop();
	}

	public static void main(String[] args) {
		System.out.println(prostfix("234+*"));
	}

}

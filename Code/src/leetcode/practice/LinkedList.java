package leetcode.practice;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

public class LinkedList {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode res = new ListNode(0);
		int carry = 0;
		ListNode head = res;
		while (carry != 0 || l1 != null || l2 != null) {
			int ans = 0;
			if (l1 != null) {
				ans += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				ans += l2.val;
				l2 = l2.next;
			}
			ListNode cur = new ListNode(ans % 10);
			res.next = cur;
			res = cur;
			carry = ans / 10;
		}
		return head.next;
	}

	public ListNode addTwoNumbersN(ListNode l1, ListNode l2) {
		ListNode l = new ListNode(0);
		ListNode r = l;
		int carry = 0;
		while (l1 != null && l2 != null && carry != 0) {
			int sum = 0;
			if (l1 != null && l2 != null)
				sum = l1.val + l2.val;
			else if (l1 == null)
				sum = l2.val;
			else if (l2 == null)
				sum = l1.val;
			sum += carry;
			carry = sum / 10;
			l.next = new ListNode(sum / 10);
			l = l.next;
		}
		return r.next;
	}

	public static int myAtoi(String str) {
		if (str == null || str.isEmpty())
			return 0;
		int num = 0;
		boolean isNegative = false;
		int index = 0;

		while (str.charAt(index) == ' ' && index < str.length())
			index++;
		char firstChar = str.charAt(index);
		if (firstChar == '-' || firstChar == '+') {
			isNegative = firstChar == '-' ? true : false;
			index++;
		}
		for (; index < str.length(); index++) {
			int digit = str.charAt(index) - '0';
			if (digit < 0 || digit > 9)
				break;
			if ((Integer.MAX_VALUE - digit) / 10 < num) {
				if (isNegative)
					return Integer.MIN_VALUE;
				else
					return Integer.MAX_VALUE;
			}
			num = num * 10 + digit;
		}
		return isNegative ? num * -1 : num;
	}

	public static void main(String[] args) {
		System.out.println(myAtoi("     +004500"));
	}

}

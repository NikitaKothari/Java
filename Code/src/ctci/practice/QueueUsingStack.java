package ctci.practice;

import java.util.Stack;

public class QueueUsingStack {

	Stack<Integer> temp = new Stack<Integer>();
	Stack<Integer> value = new Stack<Integer>();

	public void enqueue(int x) {
		if (value.isEmpty())
			value.push(x);
		else {
			while (!value.isEmpty())
				temp.push(value.pop());
			value.push(x);
			while (!temp.isEmpty())
				value.push(temp.pop());
		}
	}

	public int dequeue() {
		return value.pop();
	}

	public int peek() {
		return value.peek();
	}

	boolean isEmpty() {
		return value.isEmpty();
	}

}

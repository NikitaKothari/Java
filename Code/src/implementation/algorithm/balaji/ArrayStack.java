package implementation.algorithm.balaji;

import java.util.NoSuchElementException;

/**
 * @author Mohanakrishna,Pradeep,Vinaya,Nikita
 *
 */
public class ArrayStack<T> {

	/**
	 * Function to return the topmost element of stack if it is not empty, else
	 * it throws an exception
	 * 
	 * @return topmost element of stack
	 */
	private T stack[];
	private int capacity;
	private int top;

	public ArrayStack(int size) {
		capacity = size;
		stack = (T[]) new Object[capacity];

		// Top is assigned -1 when the stack is empty.
		top = -1;
	}

	public ArrayStack() {
		this(10); // default capacity of the stack is 10
	}

	/**
	 * Function to return the topmost element of stack if it is not empty, else
	 * it throws an exception
	 * 
	 * @return topmost element of stack
	 */
	public T peek() {
		if (top == -1) {
			throw new NoSuchElementException("Stack is empty");
		}

		return stack[top];
	}

	/**
	 * Function to remove the topmpost element of stack if it is not empty. If
	 * the stack is empty it throws an exception stating that the stack is empty
	 * 
	 * @return removes & returns the topmost element
	 */
	public T pop() {
		if (top == -1) {
			throw new NoSuchElementException("Stack is empty");
		} else {
			top -= 1;
			return stack[top];
		}
	}

	/**
	 * Function to add a new element to the topmost position of the stack
	 * 
	 * @param item
	 *            : item to be pushed into the stack
	 */
	public void push(T item) {
		if (capacity == size()) {
			throw new StackFullException("Stack is full");

		}
		stack[top + 1] = item;

		// After the new element is added, we increment the top by 1
		top += 1;

	}

	/**
	 * Function to check if the stack is empty
	 * 
	 * @return true if stack is empty
	 */
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Function to return the size of the stack
	 * 
	 * @return size of the stack
	 */
	public int size() {
		return (top + 1);
	}

	/**
	 * Function to print the stack
	 */
	public void print() {
		if (top == -1) {
			return;
		} else {
			for (int i = 0; i <= top; i++) {
				System.out.print(stack[i] + " ");
			}

		}
		System.out.println();
	}

	public static void main(String[] args) {
		ArrayStack<Integer> s = new ArrayStack<Integer>();
		s.push(10);
		s.push(20);
		s.push(30);
		System.out.println(s.peek());

	}

}

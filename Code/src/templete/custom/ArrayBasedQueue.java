package templete.custom;

import java.util.NoSuchElementException;

public class ArrayBasedQueue<E> {
	private E[] arr = null;
	private int CAP;
	private int front = -1;
	private int rear = -1;

	public ArrayBasedQueue() {
		this.CAP = 16;
		this.arr = (E[]) new Object[this.CAP];
	}

	public boolean isEmpty() {
		return rear == front;
	}

	private boolean isFull() {
		return rear + 1 > arr.length;
	}

	public boolean offer(E x) {
		if (isFull())
			resize(2 * front);
		arr[(front + rear) % arr.length] = x;
		rear++;
		return true;
	}

	private void resize(int cap) {
		E[] temp = (E[]) new Object[cap];
		for (int k = 0; k < front; k++)
			temp[k] = arr[(front + rear) % arr.length];
		arr = temp;
		front = 0;
	}

	public E poll() {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		E x = arr[front];
		front = (front + 1) % arr.length;
		rear--;
		if (arr.length >= 4 * rear && (rear / 4) >= 16)
			resize(rear / 4);
		return x;
	}

}

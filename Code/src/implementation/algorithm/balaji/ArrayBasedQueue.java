package implementation.algorithm.balaji;

import java.util.NoSuchElementException;

/**
 * @author Mohanakrishna,Pradeep,Vinaya,Nikita
 *
 */
public class ArrayBasedQueue<E> {
	private E[] arr = null;
	private int CAP;
	private int front = 0;
	private int rear = 0;

	public ArrayBasedQueue() {
		this.CAP = 16;
		this.arr = (E[]) new Object[this.CAP];
	}

	public boolean isEmpty() {
		return rear == front || rear == 0;
	}

	private boolean isFull() {
		return rear + 1 > arr.length;
	}

	/**
	 * Add element to the queue and if it is full double the size
	 * 
	 * @param x
	 * @return
	 */
	boolean offer(E x) {
		if (isFull())
			resize(2 * front);
		arr[(front + rear) % arr.length] = x;
		rear++;
		return true;
	}

	/**
	 * Remove element from queue and if it is 75% empty resize it by 1/4th of it
	 * size
	 * 
	 * @return
	 */
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

	/**
	 * Returns first element without removing it
	 * 
	 * @return
	 */
	public E peek() {
		if (isEmpty())
			throw new NoSuchElementException("Underflow Exception");
		return arr[front];
	}

	/**
	 * Resize array and put all the element back
	 * 
	 * @param cap
	 */
	private void resize(int cap) {
		E[] temp = (E[]) new Object[cap];
		for (int k = 0; k < front; k++)
			temp[k] = arr[(front + rear) % arr.length];
		arr = temp;
		front = 0;
	}

	public static void main(String[] args) {
		ArrayBasedQueue<Integer> queue = new ArrayBasedQueue<>();
		queue.offer(5);
		queue.offer(10);
		queue.offer(15);
		queue.offer(20);
		queue.offer(22);
		queue.peek();

		while (!queue.isEmpty()) {
			System.out.println(queue.poll());
		}
	}
	/**
	 * Output : 5 10 15 20 22
	 * 
	 */
}

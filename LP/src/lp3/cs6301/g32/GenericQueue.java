package lp3.cs6301.g32;

import java.util.LinkedList;

class GenericQueue<E> {
	private LinkedList<E> list = new LinkedList<E>();

	public void enqueue(E item) {
		list.addLast(item);
	}

	public E dequeue() {
		return list.poll();
	}

	public boolean hasItems() {
		return !list.isEmpty();
	}

	public int size() {
		return list.size();
	}

	public void addItems(GenericQueue<? extends E> q) {
		while (q.hasItems())
			list.addLast(q.dequeue());
	}

	public static void main(String[] args) {
		GenericQueue<Integer> queue = new GenericQueue<>();

		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);

		while (queue.hasItems()) {
			System.out.println(queue.dequeue());
		}
	}
}

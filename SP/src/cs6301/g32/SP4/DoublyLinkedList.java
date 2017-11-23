package cs6301.g32.SP4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {

	static class Entry<T> {
		T element;
		Entry<T> prev, next;

		Entry(T x, Entry<T> nxt) {
			element = x;
			next = nxt;
		}
	}

	Entry<T> head, tail;
	int size;

	public DoublyLinkedList() {
		head = new Entry<>(null, null);
		tail = head;
		size = 0;
	}

	public void add(T x) {
		tail.next = new Entry<>(x, null);
		tail = tail.next;
		size++;
	}

	int countNodes(DoublyLinkedList<T> head) {
		int count = 0;
		DoublyLinkedList<T> temp = head;
		while (temp != null) {
			// temp = temp.next;
			count++;
		}
		return count;
	}

	@Override
	public Iterator<T> iterator() {
		return new DLLIterator<>(this);
	}

	private class DLLIterator<E> implements Iterator<E> {
		DoublyLinkedList<E> list;
		Entry<E> cursor, prev;
		boolean ready; // is item ready to be removed?

		DLLIterator(DoublyLinkedList<E> list) {
			this.list = list;
			cursor = list.head;
			prev = null;
			ready = false;
		}

		public boolean hasNext() {
			return cursor.next != null;
		}

		public E next() {
			prev = cursor;
			cursor = cursor.next;
			ready = true;
			return cursor.element;
		}

		// Removes the current element (retrieved by the most recent next())
		// Remove can be called only if next has been called and the element has
		// not been removed
		public void remove() {
			if (!ready) {
				throw new NoSuchElementException();
			}
			prev.next = cursor.next;
			// Handle case when tail of a list is deleted
			if (cursor == list.tail) {
				list.tail = prev;
			}
			cursor = prev;
			ready = false; // Calling remove again without calling next will
							// result in exception thrown
			size--;
		}
	}

}

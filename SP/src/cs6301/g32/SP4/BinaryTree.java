package cs6301.g32.SP4;

import java.util.Iterator;

public class BinaryTree<T> implements Iterable<T> {
	static class Entry<T> {
		T element;
		Entry<T> left, right;

		Entry(T x, Entry<T> left, Entry<T> right) {
			element = x;
			left = left;
			right = right;
		}
	}

	public void add(T x) {

	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}

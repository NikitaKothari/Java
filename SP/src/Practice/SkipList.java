package Practice;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class SkipList<T extends Comparable<? super T>> {
	int level;
	Entry<T> head;
	int size = 0;

	class Entry<T extends Comparable<? super T>> {
		T element;
		Entry<T>[] next;

		public Entry(T element, int level) {
			this.element = element;
			this.next = (Entry<T>[]) Array.newInstance(Entry.class, level);
		}
	}

	private class SLIterator<T extends Comparable<? super T>> implements Iterator<T> {

		SkipList<T> sl;
		Entry<T> cursor, prev;
		boolean ready;

		@Override
		public boolean hasNext() {
			return cursor.next[0] != null;
		}

		@Override
		public T next() {
			cursor = cursor.next[0];
			return cursor.element;
		}

		public void remove() {
			prev = cursor;
			cursor = cursor.next[0];
			sl.remove(prev.element);
		}

	}

	public SkipList(int level) {
		this.level = level;
		this.size = 0;
		head = new Entry<>(null, level);
	}

	/**
	 * return prev[0..maxLevel] of nodes at which search went down one level,
	 * looking for x
	 * 
	 * @param x
	 * @return
	 */
	Entry<T>[] find(T x) {
		Entry<T> p = head;
		Entry<T>[] prev = (Entry<T>[]) Array.newInstance(Entry.class, level);
		for (int i = level - 1; i >= 0; i--) {
			while (p.next[i] != null && p.next[i].element.compareTo(x) < 0) {
				p = p.next[i];
			}
			prev[i] = p;
		}
		return prev;
	}

	/**
	 * Choose number of levels for a new node randomly
	 * 
	 * @param lev
	 * @return
	 */
	int chooseLevel() {
		Random random = new Random();
		int mask = (1 << level) - 1;
		int lev = Integer.numberOfTrailingZeros(random.nextInt() & mask);
		if (lev > level) {
			// level = level + 1;
			return level;
		}
		return lev;
		/*
		 * int i = 0; Random randomno = new Random(); while (i < lev) { boolean
		 * b = randomno.nextBoolean(); if (b) i++; else break; } return i;
		 */
	}

	void add(T x) {
		Entry<T>[] prev = find(x);
		if (prev[0].next[0] != null && prev[0].next[0].element.compareTo(x) == 0) {
			// already present -> replace
			prev[0].next[0].element = x;
		} else {
			int lev = chooseLevel();
			if (lev == 0)
				lev = 1;
			Entry<T> n = new Entry<>(x, lev);
			System.out.println(lev);
			for (int i = 0; i < lev; i++) {
				n.next[i] = prev[i].next[i];
				prev[i].next[i] = n;
			}
			size++;
		}
	}

	boolean contains(T x) {
		Entry<T>[] prev = find(x);
		if (prev[0] == null || prev[0].next[0] == null)
			return false;
		return prev[0].next[0].element.compareTo(x) == 0;
	}

	T remove(T x) {
		Entry<T>[] prev = find(x);
		if (prev[0] == null || prev[0].next[0] == null)
			return null;
		Entry<T> n = prev[0].next[0];
		if (n.element != x) {
			return null;
		} else {
			for (int i = 0; i < level; i++) {
				if (prev[i].next[i] != null && prev[i].next[i].equals(n)) {
					prev[i].next[i] = n.next[i];
				} else {
					break;
				}
			}
			size--;
			return n.element;
		}
	}

	T first() {
		if (head.next[0] == null)
			return null;
		return head.next[0].element;
	}

	T last() {
		Entry<T> p = head;
		for (int i = level - 1; i >= 0; i--) {
			while (p.next[i] != null) {
				p = p.next[i];
			}
		}
		if (p == null)
			return null;
		return p.element;
	}

	boolean isEmpty() {
		return size <= 0;
	}

	int size() {
		return size;
	}

	T get(int n) {
		Entry<T> p = head;
		int index = 0;
		for (int i = level - 1; i >= 0; i--) {
			int levelIndex = (int) Math.pow(2, i);
			while (p.next[i] != null && (levelIndex + index) < n) {
				p = p.next[i];
				index = index + levelIndex;
			}
		}
		return index == (n - 1) ? p.element : null;
	}

	T ceiling(T x) {
		Entry<T>[] prev = find(x);
		if (prev[0] != null && prev[0].element.compareTo(x) == 0)
			return prev[0].element;
		if (prev[0] == null || prev[0].next[0].element == null)
			return null;
		return prev[0].next[0].element;
	}

	T floor(T x) {
		Entry<T>[] prev = find(x);
		if (prev[0] != null)
			return prev[0].element;
		return null;
	}

	void print() {
		Entry<T> p = head;
		for (int i = level - 1; i >= 0; i--) {
			p = head;
			while (p.next[i] != null) {
				System.out.print(p.next[i].element + "->");
				p = p.next[i];
			}
			System.out.println();
		}
	}

	public void rebuild() {
		Entry<T> p = head;
		int lev = 1;
		int levDiff = 2;
		while (lev <= level) {
			int i = 0;
			while (p.next[lev - 1] != null) {
				i++;
				if ((i / 2) == 0) {
					Entry<T> next = p.next[lev].next[lev];
					p.next[lev] = p.next[lev - 1];
					p.next[lev].next[lev] = next;
					p.next[lev] = p.next[lev - 1] = p.next[lev - 1].next[lev - 1];
				} else {
					p.next[lev - 1] = p.next[lev - 1].next[lev - 1];
				}
			}
		}
	}

	public static void main(String[] args) {
		SkipList<Integer> sl = new SkipList<>(4);
		Scanner sc = new Scanner(System.in);
		while (true) {
			int num = sc.nextInt();
			if (num >= 0)
				sl.add(num);
			else
				sl.remove(num * -1);
			sl.print();
		}
	}
}

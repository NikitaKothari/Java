package cs6301.g32.SP6;

public class GenericPriorityQueue<T extends Comparable<? super T>> {

	T[] pq = null;
	int size = 0;
	int capacity = 0;
	T x;

	public T getT() {
		return x;
	}

	public GenericPriorityQueue(int capacity) {
		pq = (T[]) new Comparable[capacity];
		this.capacity = capacity;
	}

	private void procolatedown(int i) {
		x = pq[i];
		int c = 2 * i + 1;
		while (c <= size - 1) {
			if (c < size - 1 && pq[c].compareTo(pq[c + 1]) > 0)
				c++;
			if (x.compareTo(pq[c]) <= 0)
				break;
			pq[i] = pq[c];
			i = c;
			c = 2 * i + 1;
		}
		pq[i] = x;
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private void procolateup(int i) {
		x = pq[i];
		while (i > 0 && x.compareTo(pq[parent(i)]) < 0) {
			pq[i] = pq[parent(i)];
			i = parent(i);
		}
		pq[i] = x;
	}

	public T peek() {
		return pq[0];
	}

	public boolean isEmpty() {
		return size <= 0;
	}

	public T remove() {
		T min = pq[0];
		pq[0] = pq[--size];
		procolatedown(0);
		return min;
	}

	public void add(T x) {
		if (size == pq.length) {

		}
		pq[size] = x;
		procolateup(size);
		size++;
	}

	public static void main(String[] args) {
		GenericPriorityQueue<Integer> nums = new GenericPriorityQueue<>(10);
		nums.add(new Integer(42));
		nums.add(46);
		nums.add(2);
		nums.add(20);
		nums.add(50);
		nums.add(6);
		nums.add(18);
		nums.add(24);
		nums.add(2);

		System.out.println(nums.remove());
		System.out.println(nums.remove());
		System.out.println(nums.remove());
		System.out.println(nums.remove());
		System.out.println(nums.remove());
		System.out.println(nums.remove());
		System.out.println(nums.remove());
		System.out.println(nums.remove());
		System.out.println(nums.remove());
	}

}

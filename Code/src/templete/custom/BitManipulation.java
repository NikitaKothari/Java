package templete.custom;

public class BitManipulation {

	public int repeatedArithmaticShift(int x, int count) {
		for (int i = 1; i < count; i++)
			x >>= 1;
		return x;
	}

	public int repeatedLogicalShift(int x, int count) {
		for (int i = 1; i < count; i++)
			x >>>= 1;
		return x;
	}

	public boolean getBit(int num, int i) {
		return ((num & (1 << i)) != 0);
	}

	public int setBit(int num, int i) {
		return num | (1 << i);
	}

	public int clearBitsMSBThroughI(int num, int i) {
		int mask = 1 << i - 1;
		return num & mask;
	}

	public int clearBitsThrough0(int num, int i) {
		int mask = (-1 << (i + 1));
		return num & mask;
	}

	public int update(int num, int i, boolean bit) {
		int value = bit ? 1 : 0;
		int mask = ~(1 << i);
		return (num & mask) | (value << i);
	}
}

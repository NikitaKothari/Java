package ctci.practice;

public class MathANdLogic {

	int breakingPoint = 0;
	int countDrops = 0;

	boolean drop(int floor) {
		countDrops++;
		return floor >= breakingPoint;
	}

	int findBreakingPoint(int floors) {
		int interval = 14;
		int prevFloor = 0;

		/* drop egg1 at decreasing order */
		int egg1 = interval;
		while (!drop(egg1) && egg1 <= floors) {
			interval -= 1;
			prevFloor = egg1;
			egg1 += interval;
		}

		/* drop egg2 at 1 unit increasing order */
		int egg2 = prevFloor + 1;

		while (egg2 < egg1 && egg2 <= floors && !drop(egg2)) {
			egg2 += 1;
		}

		/* If it didn't break, return -1. */
		return egg2 > floors ? -1 : egg2;
	}

}

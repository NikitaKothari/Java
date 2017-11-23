package lp3.cs6301.g32;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prady Ver 1.0: 10/18/17
 */

public class Test {
	List<Test> tp;
	int in;

	Test(int i) {
		in = i;
		tp = new ArrayList<>();
		tp.add(this);

	}

	public static void main(String[] args) {
		Test t = new Test(5);
		System.out.println(t.tp.get(0).in);
	}
}

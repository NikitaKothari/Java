package lp1.cs6301.g32;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;
import java.util.regex.Pattern;

import cs6301.g00.Tokenizer;

/**
 * @author Vinaya, Pradeep, Mohan, Nikita
 * @version 1.0: 2017/09/14
 */
public class Num implements Comparable<Num> {

	static long defaultBase = (long) Math.pow(2, 16);
	public static final Num ZERO; // constant for 0, 0 in any base has the same
									// representation
	public static final Num ONE; // constant for 1, 1 in any base from 2..2^31
									// has the same representation
	public static final Num NEGATIVE_ONE; // constant for 1, 1 in any base from
											// 2..2^31 has the same
											// representation
	private static final Pattern pattern;

	long base = defaultBase;
	LinkedList<Long> numberList; // list holding the number in base format
	boolean sign; // has value FALSE for positive numbers and TRUE for negative
					// numbers

	static {
		ZERO = new Num(0L);
		ONE = new Num(1L);
		NEGATIVE_ONE = new Num(-1L);
		pattern = Pattern.compile("^[+\\-]?\\d+$");
	}

	/* Start of Level 1 */

	/**
	 * Constructor for storing string in Num list
	 *
	 * @param s
	 *            : String : string of numbers
	 */
	Num(String s) {
		Num input_base = new Num(10);
		// int numDigits = 1;
		this.numberList = new LinkedList<>();
		if (!pattern.matcher(s).matches()) {
			throw new RuntimeException("String is not a number");
		}
		if (s.charAt(0) == '-') {
			sign = true;
			s = s.substring(1);
		} else if (s.charAt(0) == '+') {
			sign = false;
			s = s.substring(1);
		}

		Num res = new Num(0);
		long d;
		for (int i = 0; i < s.length(); i++) {
			if (s.length() >= (i + 1))
				d = Long.parseLong(s.substring(i, i + 1));
			else
				d = Long.parseLong(s.substring(i));
			res = add(product(res, input_base), new Num(d));
		}
		this.numberList = res.numberList;
	}

	/**
	 * Constructor for storing long in base representation
	 *
	 * @param x
	 *            : long : number to be stored
	 */
	Num(long x) {
		this.numberList = new LinkedList<>();
		if (x < 0) {
			x = Math.abs(x);
			this.sign = true;
		}
		do {
			numberList.add(x % defaultBase);
			x = x / defaultBase;
		} while (x > 0);
	}

	Num() {
		numberList = new LinkedList<>();
	}

	/**
	 * this method removes zeros from MSB of the calling object
	 */
	void dropLeadingZeros() {
		while (numberList.size() > 1 && numberList.getLast() == 0L) {
			numberList.removeLast();
		}
	}

	/**
	 * This method return a Number representation of a shifted left x times.
	 * This method does not change the original number
	 * <p>
	 * Example: shiftLeft(8, 2) --> 0->0->8
	 *
	 * @param a
	 *            : Num : number to be shifted
	 * @param x
	 *            : int : times a has to be shifter
	 * @return : Num : x times shifted Num representation of a
	 */
	static Num shiftLeft(Num a, int x) {
		Num result = new Num();
		result.sign = a.sign;
		for (int i = 0; i < x; i++)
			result.numberList.add(0L);
		result.numberList.addAll(a.numberList);
		return result;
	}

	/**
	 * This method shifts right in default base by x and returns results in a
	 * new Num without changing the original.
	 *
	 * @param a
	 *            : Num : number to be shifted
	 * @param x
	 *            : int : times number has to be shifted
	 * @return : Num : Num representation of original shifted right x times
	 */
	static Num shiftRight(Num a, int x) {
		Num result = new Num();
		result.numberList.addAll(a.numberList);
		for (int i = 0; i < x; i++) {
			Long r = result.numberList.removeFirst();
			result = add(product(result, (defaultBase / 2)), new Num(r / 2));
		}
		if (result.numberList.isEmpty()) {
			return ZERO;
		}
		result.sign = a.sign;
		return result;
	}

	/**
	 * This method will add two Num and return a Num representation of the
	 * result.
	 *
	 * @param a
	 *            : Num : first operand for addition
	 * @param b
	 *            : Num : second operand for addition
	 * @return : Num : Num representation in base of first operand
	 */
	static Num add(Num a, Num b) {
		// if numbers have opposite sign then represent the numbers
		// as positive numbers and subtract
		if (a.sign ^ b.sign) {
			Num temp1 = new Num();
			Num temp2 = new Num();
			// set numberList and base
			temp1.numberList = a.numberList;
			temp1.base = a.base;
			temp2.numberList = b.numberList;
			temp2.base = b.base;
			Num res = subtract(temp1, temp2);
			// if temp1 is greater meaning you have to retain sign of a
			// else if temp2 retain sign of b, else if both numbers are equal do
			// nothing
			int num_sign = temp1.compareTo(temp2);
			if (num_sign > 0) {
				res.sign = a.sign;
			} else if (num_sign < 0) {
				res.sign = b.sign;
			}
			return res;
		}
		// both numbers are positive or negative
		Num out = new Num();
		out.base = a.base;
		out.sign = a.sign;
		Iterator<Long> it1 = a.numberList.iterator();
		Iterator<Long> it2 = b.numberList.iterator();
		Long carry = 0L;
		// int i = 0;
		while (it1.hasNext() || it2.hasNext() || carry != 0L) {
			Long ans = 0L;
			if (it1.hasNext())
				ans += it1.next();
			if (it2.hasNext())
				ans += it2.next();
			ans += carry;
			out.numberList.add(ans % defaultBase);
			carry = ans / defaultBase;
			// i++;
		}
		// if (it1.hasNext()) {
		// // out.numberList.add(it1.next());
		// out.numberList.addAll(a.numberList.subList(i, a.numberList.size()));
		// }
		// if (it2.hasNext()) {
		// // out.numberList.add(it2.next());
		// out.numberList.addAll(b.numberList.subList(i, b.numberList.size()));
		// }
		return out;
	}

	/**
	 * Subtracts the second number from the first number and returns a Num
	 * representation of the result.
	 *
	 * @param a
	 *            : Num : first operand for subtraction
	 * @param b
	 *            : Num : second operand for addition
	 * @return : Num : Num representation in base of first operand
	 */
	static Num subtract(Num a, Num b) {
		// if second operand is a negative number
		if (b.sign && !a.sign) {
			Num temp1 = new Num();
			Num temp2 = new Num();
			temp1.numberList = a.numberList;
			temp2.numberList = b.numberList;
			return add(temp1, temp2);
		}
		if (a.sign && !b.sign) {
			Num temp1 = new Num();
			Num temp2 = new Num();
			temp1.numberList = a.numberList;
			temp2.numberList = b.numberList;
			Num res = add(temp1, temp2);
			res.sign = true;
			return res;
		}
		Num temp1 = new Num();
		Num temp2 = new Num();
		Num out = new Num();
		Long carry = 0L;

		temp1.numberList = a.numberList;
		temp2.numberList = b.numberList;
		Iterator<Long> it1;
		Iterator<Long> it2;
		if (temp1.compareTo(temp2) > 0) {
			it1 = a.numberList.iterator();
			it2 = b.numberList.iterator();
			out.sign = a.sign;
		} else if (temp1.compareTo(temp2) < 0) {
			it1 = b.numberList.iterator();
			it2 = a.numberList.iterator();
			out.sign = !b.sign;
		} else
			return ZERO;
		while (it1.hasNext() || it2.hasNext()) {
			long ans = 0;
			if (it1.hasNext())
				ans += it1.next();
			if (it2.hasNext())
				ans -= it2.next();
			ans -= carry;
			if (ans < 0) {
				ans += defaultBase;
				carry = 1L;
			} else {
				carry = 0L;
			}
			out.numberList.add(ans);
		}
		return out;
	}

	/**
	 * This method is simple multiplication, used if the it is easy to just
	 * multiply using simple multiplication
	 *
	 * @param a
	 *            : Num : A number in Num representation. First operand for
	 *            multiplication
	 * @param b
	 *            : Long : second operand for multiplication
	 * @return : Num : Result of multiplication in Num representation
	 */
	static Num product(Num a, Long b) {
		Num out = new Num();
		// copy a in out
		// out.numberList = new LinkedList<>(a.numberList);
		Long carry = 0L;
		Iterator<Long> it = a.numberList.iterator();
		while (it.hasNext()) {
			Long l = it.next();
			Long pd = (l * b) + carry;
			out.numberList.add(pd % defaultBase);
			carry = pd / defaultBase;
		}
		if (carry != 0L) {
			out.numberList.add(carry);
		}
		return out;
	}

	/**
	 * This method implements Karastuba's multiplication and returns the result
	 * in Num representation
	 *
	 * @param a
	 *            : Num : First operand for multiplication
	 * @param b
	 *            : Num : Second operand for multiplication
	 * @return : Num : Result of the multiplication
	 */
	static Num product(Num a, Num b) {
		Num res = new Num();
		if (a.numberList.isEmpty() || b.numberList.isEmpty()) {
			return ZERO;
		}
		if ((a.numberList.size() == 1 && a.numberList.getFirst() == 0L)
				|| (b.numberList.size() == 1 && b.numberList.getFirst() == 0L)) {
			return ZERO;
		}
		if (a.numberList.size() == 1 && a.numberList.getFirst() == 1L) {
			res.numberList = b.numberList;
			res.sign = a.sign ^ b.sign;
			return res;
		}
		if (b.numberList.size() == 1 && b.numberList.getFirst() == 1L) {
			res.numberList = a.numberList;
			res.sign = a.sign ^ b.sign;
			return res;
		}
		if (a.numberList.size() == 1) {
			res = product(b, a.numberList.getFirst());
			res.sign = a.sign ^ b.sign;
			return res;
		}
		if (b.numberList.size() == 1) {
			res = product(a, b.numberList.getFirst());
			res.sign = a.sign ^ b.sign;
			return res;
		}
		if (b.numberList.size() > a.numberList.size()) {
			res = productRecursive(b, a);
		} else {
			res = productRecursive(a, b);
		}
		res.dropLeadingZeros();
		res.sign = a.sign ^ b.sign;
		return res;
	}

	private static Num productRecursive(Num a, Num b) {
		int n1 = a.numberList.size();
		int n2 = b.numberList.size();
		int k = Math.min(n1, n2) / 2;

		Num al = new Num();
		Num ah = new Num();
		Num bl = new Num();
		Num bh = new Num();

		if (n1 == 1 && n2 == 1) { // base case
			return new Num(a.numberList.get(0) * b.numberList.get(0));
		}
		// if (n2 == 1) {
		// k = 1;
		// if (b.numberList.getFirst() == 1) {
		// return a;
		// } else if (b.numberList.getFirst() == 0) {
		// return new Num(0);
		// }
		// bh = new Num(0);
		// bl.numberList = new LinkedList<>(b.numberList);
		// } else {
		// bl.numberList = new LinkedList<>(b.numberList.subList(0, k));
		// bh.numberList = new LinkedList<>(b.numberList.subList(k, n2));
		// }

		if (n2 == 1) {
			return product(a, b.numberList.getFirst());
		}
		bl.numberList = new LinkedList<>(b.numberList.subList(0, k));
		bh.numberList = new LinkedList<>(b.numberList.subList(k, n2));
		al.numberList = new LinkedList<>(a.numberList.subList(0, k));
		ah.numberList = new LinkedList<>(a.numberList.subList(k, n1));

		Num al_product_bl = product(al, bl);
		Num ah_product_bh = product(ah, bh);

		Num ah_product_bh_shift_2k = shiftLeft(ah_product_bh, 2 * k);

		Num al_add_ah = add(al, ah);
		Num bl_add_bh = add(bl, bh);

		Num middle_term = product(al_add_ah, bl_add_bh);
		middle_term = subtract(middle_term, add(ah_product_bh, al_product_bl));
		middle_term = shiftLeft(middle_term, k);

		return add(ah_product_bh_shift_2k, add(middle_term, al_product_bl));
	}

	// Use divide and conquer

	/**
	 * This method returns powers with positive integer exponent. In case of
	 * negative exponent returns 0 And if both the numbers are 0 then thorws
	 * RuntimeException
	 *
	 * @param a
	 *            : Num : a number
	 * @param n
	 *            : long : exponent
	 * @return : Num : exponentiation of the number
	 */
	static Num power(Num a, long n) {
		if (n < 0)
			return ZERO;
		if (n == 0) {
			if (a.numberList.size() == 1 && a.numberList.getFirst() == 0L)
				throw new RuntimeException("0 power 0 is not defined");
			return ONE;
		} else if (n == 1)
			return a;
		if (a.numberList.size() == 1 && (a.numberList.getFirst() == 0L || a.numberList.getFirst() == 1L)) {
			return a;
		} else {
			Num s = power(a, n / 2);
			if (n % 2 == 0) // if n is even
				return product(s, s);
			else
				return product(product(s, s), a);
		}
	}
	/* End of Level 1 */

	/* Start of Level 2 */

	static Num divideBy2(Num a) {
		return shiftRight(a, 1);
	}

	/**
	 * This method performs integer division using recursive strategy. If the
	 * divisor is 0 then throws RuntimeException
	 *
	 * @param a
	 *            : Num : Dividend
	 * @param b
	 *            : Num : Divisor
	 * @return : Num : Quotient of the division operation
	 */
	static Num divide(Num a, Num b) {
		if (b.numberList.size() == 1 && b.numberList.getFirst() == 0L) {
			throw new RuntimeException("Divide by 0");
		}
		Num tmp1 = new Num();
		Num tmp2 = new Num();
		tmp1.numberList = a.numberList;
		tmp2.numberList = b.numberList;
		if (tmp1.compareTo(tmp2) < 0)
			return new Num(0L);
		if (tmp1.compareTo(tmp2) == 0) {
			Num res = new Num(1L);
			res.sign = a.sign ^ b.sign;
			return res;
		}
		Num res = recursiveDiv(tmp1, tmp2, new Num(1L), tmp1);
		res.sign = a.sign ^ b.sign;
		return res;
	}

	private static Num recursiveDiv(Num a, Num b, Num low, Num high) {
		Num mid = divideBy2(add(low, high));
		Num temp = product(mid, b);
		Num temp1 = product(add(mid, new Num(1L)), b);
		if (temp.compareTo(a) <= 0 && a.compareTo(temp1) < 0) {
			return mid;
		}
		if (temp.compareTo(a) < 0) {
			return recursiveDiv(a, b, add(mid, new Num(1L)), high);
		}
		return recursiveDiv(a, b, low, mid);
	}

	/**
	 * Retruns the remainder of the division operation. Throws RuntimeException
	 * if divisor is 0
	 *
	 * @param a
	 *            : Num : Dividend
	 * @param b
	 *            : Num : Divisor
	 * @return : Remainder of the division operation
	 */
	static Num mod(Num a, Num b) {
		return subtract(a, product(divide(a, b), b));
	}

	// Use divide and conquer

	/**
	 * This method returns powers with positive integer exponent. In case of
	 * negative exponent returns 0 And if both the numbers are 0 then thorws
	 * RuntimeException
	 *
	 * @param a
	 *            : Num : a number
	 * @param n
	 *            : Num : exponent
	 * @return : Num : exponentiation of the number
	 */
	static Num power(Num a, Num n) {
		if (n.sign)
			return ZERO;
		if (n.numberList.size() == 1) {
			return power(a, n.numberList.getFirst());
		}
		if (a.numberList.size() == 1 && (a.numberList.getFirst() == 1L || a.numberList.getFirst() == 0L)) {
			return a;
		}
		Num s = divideBy2(n);
		long n0 = n.numberList.getFirst();
		Num res = product(power(power(a, s), defaultBase), power(a, n0));
		return res;
	}

	/**
	 * This method returns square root of positive number if number is negative
	 * it throws an runtime error
	 *
	 * @param a
	 *            : number
	 * @return : returns square root of number
	 */
	static Num squareRoot(Num a) {
		if (a.sign)
			throw new RuntimeException("Square root of negative number is imaginary");
		if (a.numberList.size() == 1 && (a.numberList.getFirst() == 0L || a.numberList.getFirst() == 1L)) {
			return a;
		}
		Num res = squareRootRec(a, new Num(1L), a);
		return res;
	}

	private static Num squareRootRec(Num a, Num low, Num high) {
		Num mid = divideBy2(add(low, high));
		Num temp = product(mid, mid);
		Num mid1 = add(mid, new Num(1L));
		Num temp1 = product(mid1, mid1);
		if (temp.compareTo(a) <= 0 && a.compareTo(temp1) < 0) {
			return mid;
		}
		if (temp.compareTo(a) < 0) {
			return squareRootRec(a, mid1, high);
		}
		return squareRootRec(a, low, mid);
	}
	/* End of Level 2 */

	/**
	 * compare "this" to "other": return +1 if this is greater, 0 if equal, -1
	 * otherwise
	 * <p>
	 * Utility functions
	 *
	 * @param num
	 * @return
	 */
	public int compareTo(Num num) {
		if (this.sign && !num.sign) {
			return -1;
		}
		if (!this.sign && num.sign) {
			return 1;
		}
		if (this.numberList.size() > num.numberList.size()) {
			return !this.sign ? 1 : -1;
		}
		if (this.numberList.size() < num.numberList.size()) {
			return !this.sign ? -1 : 1;
		}
		// if both list are of same size
		ListIterator<Long> it1 = this.numberList.listIterator(this.numberList.size());
		ListIterator<Long> it2 = num.numberList.listIterator(num.numberList.size());
		while (it1.hasPrevious()) {
			Long item1 = it1.previous();
			Long item2 = it2.previous();
			if (item1 > item2) {
				return !this.sign ? 1 : -1;
			}
			if (item1 < item2) {
				return !this.sign ? -1 : 1;
			}
		}
		// numbers are equal
		return 0;
	}

	public boolean equals(Num num) {
		return this.compareTo(num) == 0;
	}

	/**
	 * Output using the format "base: elements of list ..." For example, if
	 * base=100, and the number stored corresponds to 10965, then the output is
	 * "100: 65 9 1"
	 */
	void printList() {
		System.out.print(this.base + ": " + (this.sign ? "- " : "+"));
		for (Long aLong : this.numberList) {
			System.out.print(aLong + " ");
		}
		System.out.println();
	}

	// Return number to a string in base 10
	@Override
	public String toString() {
		long temp = defaultBase;
		defaultBase = 10;
		Num b = new Num(temp);

		Num res = new Num(0);
		for (int i = numberList.size() - 1; i >= 0; i--) {
			res = add(product(res, b), new Num(numberList.get(i)));
		}
		defaultBase = temp;
		res.sign = this.sign;
		StringBuilder sb = new StringBuilder();
		for (Long l : res.numberList) {
			sb.insert(0, l);
		}
		return (this.sign ? "-" : "+") + sb.toString();
	}

	/**
	 * Start of Level 3
	 */

	/**
	 * It evaluates the postfix expression
	 *
	 * @param expression
	 *            : Postfix expression
	 * @return : Returns answer of postfix expression
	 */
	static Num evaluatePostfixExpression(String expression) {
		String[] tokens = expression.split(" ");

		Stack<Num> stack = new Stack<>();

		for (int i = 0; i < tokens.length; i++) {
			try {
				if (Tokenizer.tokenize(tokens[i]) == Tokenizer.Token.OP) {

					Num b = stack.pop();
					Num a = stack.pop();
					Num res = new Num();
					switch (tokens[i]) {
					case "+":
						res = add(a, b);
						break;
					case "-":
						res = subtract(a, b);
						break;

					case "*":
						res = product(a, b);
						break;
					case "/":
						res = divide(a, b);
						break;
					case "%":
						res = mod(a, b);
						break;
					case "^":
						res = power(a, b);
						break;
					default:
						break;
					}
					stack.push(res);
				} else {
					stack.push(new Num(tokens[i]));
				}
			} catch (Exception e) {
				// unexpected input then process next token
			}
		}
		Num res = stack.pop();
		res.dropLeadingZeros();
		return res;
	}

	public long base() {
		return base;
	}
}

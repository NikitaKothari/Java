package implementation.algorithm.balaji;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @author Mohanakrishna,Pradeep,Vinaya,Nikita
 *
 */
public class ShuntingYard {

	/**
	 * checks if the token is an operator
	 * 
	 * @param c:
	 *            input token
	 * @return true if token is an operator
	 */
	private boolean isOperator(char c) {
		return (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^' || c == '!');
	}

	private boolean isLeftAssociative(char op) {
		return (op != '^');
	}

	/**
	 * returns the priority of the operator
	 * 
	 * @param op:
	 *            operator to determine priority
	 * @return higher integer if higher priority
	 */
	private int getPriority(char op) {

		switch (op) {
		case '!':
			return 5;
		case '^':
			return 4;
		case '*':
		case '/':
		case '%':
			return 3;
		default:
			// for '+' and '-'
			return 2;
		}
	}

	/**
	 * op1 is operator in stack top op2 is the token currently being processed
	 * returns true : if second argument(op2) is left associative and op1 has
	 * greater or equal priority if second argument(op2) is right associative
	 * and op1 has greater priority
	 * 
	 * @param op1:
	 *            operator
	 * @param op2:
	 *            operator
	 * @return true if op1 has higher or equal priority than op2
	 */
	private boolean checkPriority(char op1, char op2) {
		if (isLeftAssociative(op2))
			return getPriority(op1) >= getPriority(op2);
		else
			return getPriority(op1) > getPriority(op2);
	}

	/**
	 * converts infix expression to postfix
	 * 
	 * @param expression
	 *            : input infix expression
	 * @return postfix expression
	 */
	public String infixToPostfix(String expression) {

		// resulting postfix expression
		StringBuilder output = new StringBuilder();

		char[] expArr = expression.toCharArray();

		// stack to store operators
		Stack<Character> stack = new Stack();

		for (char c : expArr) {
			// if the token is a number, then push it to the output queue.
			if (Character.isAlphabetic(c) || Character.isDigit(c)) {
				output.append(c);
			} else if (isOperator(c)) {

				// while there is an operator at the top of the operator stack
				// with
				// greater than or equal to precedence
				while (!stack.isEmpty() && isOperator(stack.peek()) && checkPriority(stack.peek(), c)) {
					output.append(stack.pop());
				}

				// push the read operator onto the operator stack.
				stack.push(c);
			} else if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				// while the operator at the top of the operator stack is not a
				// left bracket
				while (!stack.isEmpty() && stack.peek() != '(') {
					output.append(stack.pop());
				}
				// pop left bracket
				char bracket = stack.pop();
				if (bracket != '(')
					throw new NoSuchElementException("unmatched brackets");
			}
		}

		// while there are still operator tokens on the stack
		while (!stack.isEmpty()) {
			output.append(stack.pop());
		}
		return output.toString();
	}

	public static void main(String[] args) {
		String input = "3+4*2/(1-5)^2^3";
		ShuntingYard sy = new ShuntingYard();

		System.out.println(sy.infixToPostfix(input));

	}
}

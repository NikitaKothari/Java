package lp1.cs6301.g32;

import java.util.NoSuchElementException;
import java.util.Stack;

import cs6301.g00.Tokenizer;

/**
 * @author Mohanakrishna,Pradeep,Vinaya,Nikita
 *
 */
public class ShuntingYard {

	private static boolean isLeftAssociative(char op) {
		return (op != '^');
	}

	/**
	 * returns the priority of the operator
	 * 
	 * @param op:
	 *            operator to determine priority
	 * @return higher integer if higher priority
	 */
	private static int getPriority(char op) {

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
	private static boolean checkPriority(char op1, char op2) {
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
	public static String infixToPostfix(String expression) {

		// resulting postfix expression
		StringBuilder output = new StringBuilder();

		String[] expArr = expression.split(" ");

		// stack to store operators
		Stack<String> stack = new Stack();

		for (String literal : expArr) {
			try {
				// if the token is a number, then push it to the output queue.
				if (Tokenizer.tokenize(literal) == Tokenizer.Token.VAR
						|| Tokenizer.tokenize(literal) == Tokenizer.Token.NUM) {
					output.append(literal);
					output.append(" ");
				} else if (Tokenizer.tokenize(literal) == Tokenizer.Token.OP) {

					// while there is an operator at the top of the operator
					// stack with
					// greater than or equal to precedence
					while (!stack.isEmpty() && (Tokenizer.tokenize(stack.peek()) == Tokenizer.Token.OP)
							&& checkPriority(stack.peek().charAt(0), literal.charAt(0))) {
						output.append(stack.pop());
						output.append(" ");
					}

					// push the read operator onto the operator stack.
					stack.push(literal);
				} else if (literal.equals("(")) {
					stack.push(literal);
				} else if (literal.equals(")")) {
					// while the operator at the top of the operator stack is
					// not a left bracket
					while (!stack.isEmpty() && !stack.peek().equals("(")) {
						output.append(stack.pop());
						output.append(" ");
					}
					// pop left bracket
					String bracket = stack.pop();
					if (!bracket.equals("("))
						throw new NoSuchElementException("unmatched brackets");
				}
			} catch (Exception e) {

			}
		}

		// while there are still operator tokens on the stack
		while (!stack.isEmpty()) {
			output.append(stack.pop());
		}

		return output.toString();
	}

}

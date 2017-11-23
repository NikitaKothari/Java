package lp1.cs6301.g32;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import cs6301.g00.Tokenizer;

/**
 *
 * @author Vinaya, Pradeep, Mohan, Nikita
 */
public class L3Driver {

	// hashmap to store values of all variables
	HashMap<Integer, String> variables;

	L3Driver() {
		variables = new HashMap<>();
	}

	/**
	 * This functions processes each line, substitutes numerical values for
	 * variables and evaluates and returns the output in string format
	 * 
	 * @param line
	 *            input line
	 * @return processed numerical output
	 */
	String processLine(String line) {
		String outputStr = "";
		LinkedList<String> list = new LinkedList<>();
		int hashKey;
		String[] tokens = line.split(" ");
		boolean postfix = false;

		// processing line by each token
		for (String s : tokens) {
			try {
				if (!s.equals(" ")) {
					Tokenizer.Token t = Tokenizer.tokenize(s);

					// if token if operator then this line contains postfix
					// expression
					if (t == Tokenizer.Token.OP) {
						postfix = true;
					}

					// if not end of line ass to list
					if (t != Tokenizer.Token.EOL)
						list.add(s);
					else {
						// if postfix send it to Num.evaluatePostfixExpression
						if (postfix) {
							StringBuilder sb = new StringBuilder();

							/**
							 * if line was "p = p x *" then list will be [p] [=]
							 * [x] [*] if variable found then getting variable's
							 * values and adding it to string
							 */

							String var = list.removeFirst(); // variable to
																// assign the
																// result of
																// postfix
																// expression
							hashKey = var.charAt(0) - 'a';

							list.removeFirst(); // for "="

							while (!list.isEmpty()) {
								String token = list.removeFirst();
								if (Tokenizer.tokenize(token) == Tokenizer.Token.VAR) {
									sb.append(variables.get(token.charAt(0) - 'a'));
									sb.append(" ");
								} else {
									sb.append(token);
									sb.append(" ");
								}
							}

							Num res = Num.evaluatePostfixExpression(sb.toString());
							outputStr = res.toString();

							// output of evaluation is updated to the assigned
							// variable
							// if "p = p x *" is the line then p value is
							// updated
							variables.put(hashKey, outputStr);

						}
						// if line is an assignment "p = 10"
						else {

							String var = list.removeFirst();
							hashKey = var.charAt(0) - 'a';
							list.removeFirst(); // for "="
							outputStr = list.removeFirst();

							// update the variable value
							variables.put(hashKey, outputStr);

						}
					}

				}
			} catch (Exception e) {

			}
		}
		return outputStr;
	}

	/**
	 * Function reads the input line by line and sends it to processLine for
	 * evaluation
	 * 
	 * @param in
	 *            Scanner to read input
	 */
	void evaluateExp(Scanner in) {
		String output = "";

		while (in.hasNextLine()) {

			String line = in.nextLine();

			if (line.equals(";"))
				break;
			else {
				output = processLine(line);
				System.out.println(output);
			}
		}

		// last line of output is printed using Num.printList()
		Num n = new Num(output);
		n.printList();
	}

}

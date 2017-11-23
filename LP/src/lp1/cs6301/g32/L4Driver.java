package lp1.cs6301.g32;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import cs6301.g00.Tokenizer;

/**
 *
 * @author Vinaya, Pradeep, Mohan, Nikita
 */
public class L4Driver {

	L3Driver l3 = new L3Driver();

	// hashmap to store values of all variables
	HashMap<Integer, String> variables;

	// hashmap to store linenumbers of lines
	HashMap<Integer, Integer> lineNumber;

	// array to store all lines
	ArrayList<String> lines;

	// pointer to the current line of execution
	int control = 0;

	/**
	 * initializing all the HashMap and arrays
	 */
	L4Driver() {
		// L3 and L4 share the same hashmap to store variables
		variables = l3.variables;
		lineNumber = new HashMap<>();
		lines = new ArrayList<>();
	}

	// String to track the last line of output
	String output = "";

	/**
	 * 
	 * @param line
	 *            : line of format "VAR ;"
	 * @param lineNum
	 *            : lineNum of the parameter line if lineNum is -1 then lineNum
	 *            parameter is ignored
	 * @param addToArray
	 *            : if true then the parameter line will be added to the lines
	 *            Array
	 * @return true if it is of format "VAR ;"
	 * @throws Exception
	 */
	boolean checkprintLine(String line, int lineNum, boolean addToArray) throws Exception {
		String[] splitOnSp = line.split(" ");
		/**
		 * on split by space [VAR][;] will be the format so if splitOnSp[0] is
		 * VAR and splitOnSp[1] is EOL then print the variable value and return
		 * true
		 */
		if (Tokenizer.tokenize(splitOnSp[0]) == Tokenizer.Token.VAR
				&& Tokenizer.tokenize(splitOnSp[1]) == Tokenizer.Token.EOL) {

			if (addToArray)
				lines.add(line);
			if (lineNum != -1) {
				lineNumber.put(lineNum, lines.size() - 1);
			}

			output = variables.get(splitOnSp[0].charAt(0) - 'a');
			System.out.println(output);
			return true;
		} else // if not in format then return false
			return false;
	}

	/**
	 * 
	 * @param index
	 *            : the to be processed line's index in the lines array
	 * @return true if control is moved in this function
	 * @throws Exception
	 */
	boolean processLine(int index) throws Exception {
		String line = lines.get(index);

		// index of first occurance of " " in input line
		int firstSpaceIndex = line.indexOf(" ");

		// separating expression from line number
		String expression = line.substring(firstSpaceIndex + 1);

		// if first character is of type NUM then line contains line number
		if (Tokenizer.tokenize(line.charAt(0) + "") == Tokenizer.Token.NUM) {
			int lineNum = Integer.parseInt(line.substring(0, line.indexOf(" ")));
			String[] splitOnSp = expression.split(" ");

			// loop inside loop
			if (line.contains("?")) {

				// loop variable in hashkey format
				int leftVar = splitOnSp[0].charAt(0) - 'a';

				// value of loop variable
				Long leftVarVal = Long.parseLong(variables.get(leftVar));

				int toIndex;
				if (leftVarVal != 0) {
					toIndex = Integer.parseInt(splitOnSp[2]);
					if (lineNumber.containsKey(toIndex)) {
						control = lineNumber.get(toIndex) - 1;
						return true;
					}
				} else if (splitOnSp.length > 4) {
					toIndex = Integer.parseInt(splitOnSp[4]);
					if (lineNumber.containsKey(toIndex)) {
						control = lineNumber.get(toIndex) - 1;
						return true;
					}
				}
			} else if (line.contains("=")) {
				// assignment or postfix expression
				output = l3.processLine(expression);

			} else {
				// if line does not contain "=" or "?" then it should be of
				// format
				// "VAR ;"
				checkprintLine(line, -1, false);

			}
		}
		/**
		 * lines without line number can be of format: "VAR ;" or postfix or
		 * assignment expression (not loop)
		 */
		else {
			// if not of type "VAR ;"
			if (!checkprintLine(line, -1, false)) {
				// for assignment or postfix types
				output = l3.processLine(line);
				// if no line number then print output
				System.out.println(output);

			}
		}

		return false;

	}

	/**
	 * this functions feeds the line to be processed to the processLine function
	 * it updates the control after each line execution
	 * 
	 * @param line
	 *            : the loop statement
	 * @throws Exception
	 *             if unknown token is found
	 */
	void executeLoop(String line) throws Exception {
		for (int i = control; i < lines.size(); i++) {
			if (processLine(i))
				i = control;
		}

	}

	/**
	 * Function processes and execute every line, outputs if line does not have
	 * line number
	 * 
	 * @param in
	 *            Scanner to read input
	 */
	void processInput(Scanner in) {

		while (in.hasNextLine()) {

			String line = in.nextLine();

			// line is of format ";" then stop processing input
			if (line.equals(";"))
				break;
			else {
				try {
					// if first character is of type NUM then line contains line
					// number
					if (Tokenizer.tokenize(line.charAt(0) + "") == Tokenizer.Token.NUM) {

						// index of first occurance of " " in input line
						int firstSpaceIndex = line.indexOf(" ");

						// getting linenum of input line
						int lineNum = Integer.parseInt(line.substring(0, firstSpaceIndex));

						// separating expression from line number
						String expression = line.substring(firstSpaceIndex + 1);

						// assignment or infix expression
						if (expression.contains("=")) {
							/**
							 * if infix is of type VAR = VAR1 OP VAR2 the VAR1
							 * OP VAR2 is converted to postfix
							 */

							String[] splitOnEQ = expression.split(" = ");

							// convert infix to postfix expression
							String postfix = ShuntingYard.infixToPostfix(splitOnEQ[1]);

							/**
							 * remake the expression to form VAR = VAR1 VAR2 OP
							 * ;
							 */
							expression = splitOnEQ[0] + " = " + postfix + " ;";

							// add it to lines array with line number
							lines.add(lineNum + " " + expression);

							/**
							 * add line number and index of this line in lines
							 * array to lineNumber hashmap
							 */
							lineNumber.put(lineNum, lines.size() - 1);

							// update output to track last processed line
							output = l3.processLine(expression);
						} else if (expression.contains("?")) {
							// contains loop

							lines.add(line);
							lineNumber.put(lineNum, lines.size() - 1);
							control = lines.size() - 1;
							executeLoop(line);
						} else {
							// if line does not contain "=" or "?" then it
							// should be of format
							// "VAR ;"
							checkprintLine(expression, lineNum, true);
						}

					} else {

						// if not of type "VAR ;"
						if (!checkprintLine(line, -1, true)) {
							// for assignment or postfix types
							lines.add(line);
							output = l3.processLine(line);
							// if no line number then print output
							System.out.println(output);
						}

					}

				} catch (Exception e) {
					// unexpected input then process next line
				}
			}
		}
		Num n = new Num(output); // last line of output
		n.printList();
	}

}

package ctci.practice;

public class StringAndArray {

	/**
	 * Time complexity o(n) and space o(1)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isUnique(String str) {
		if (str.length() > 128)
			return false;
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0)
				return false;
			checker |= 1 << val;
		}
		return true;
	}

	public static boolean isPermutation(String s, String t) {
		if (s.length() != t.length())
			return false;
		int[] letter = new int[128];
		char[] s_char = s.toCharArray();

		for (char c : s_char) {
			letter[c]++;
		}

		for (int i = 0; i < t.length(); i++) {
			int c = (int) t.charAt(i);
			letter[c]--;
			if (letter[c] < 0)
				return false;
		}
		return true;
	}

	public static void URLify(char[] str, int trueLength) {
		int spaceCout = 0, index, i = 0;
		for (i = 0; i < trueLength; i++) {
			if (str[i] == ' ')
				spaceCout++;
		}
		index = trueLength + spaceCout * 2;
		if (trueLength < str.length)
			str[trueLength] = '\0';
		for (i = trueLength - 1; i < 0; i--) {
			if (str[i] == ' ') {
				str[index - 1] = '0';
				str[index - 2] = '2';
				str[index - 3] = '%';
				index -= 3;
			} else {
				str[index - 1] = str[i];
				index--;
			}
		}
	}

	public static boolean isPermutationOfPalindrome(String pharse) {
		int countOdd = 0;
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		for (char c : pharse.toCharArray()) {
			int x = Character.getNumericValue('z') - Character.getNumericValue(c);
			if (x != -1) {
				table[x]++;
				if (table[x] % 2 == 1)
					countOdd++;
				else
					countOdd--;
			}
		}
		return countOdd <= 1;
	}

	public static boolean oneEditAway(String first, String second) {
		if (first.length() == second.length())
			return oneEditRepalce(first, second);
		else if (first.length() - 1 == second.length())
			return oneEditInsert(first, second);
		else if (first.length() == second.length() - 1)
			return oneEditInsert(second, first);
		return false;
	}

	public static boolean oneEditRepalce(String first, String second) {
		boolean foundDifference = false;
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != second.charAt(i)) {
				if (foundDifference)
					return false;
				foundDifference = true;
			}
		}
		return true;
	}

	public static boolean oneEditInsert(String first, String second) {
		int index1 = 0, index2 = 2;
		while (index1 < first.length() && index2 < second.length()) {
			if (first.charAt(index1) != second.charAt(index2)) {
				if (index1 != index2)
					return false;
				index2++;
			} else {
				index1++;
				index2++;
			}
		}
		return true;
	}

	public static String conpressedString(String str) {
		if (str == null || str.isEmpty())
			return str;
		int compressedChar = 0;
		String compressedString = "";
		for (int i = 0; i < str.length(); i++) {
			if ((i + 1) == str.length() || str.charAt(i) != str.charAt(i + 1)) {
				compressedChar++;
				compressedString += str.charAt(i) + compressedChar;
				compressedChar = 0;
			}
			compressedChar++;
		}
		return compressedString;
	}

	public static boolean isRotation(String str1, String str2) {
		if (str1.length() != str2.length())
			return false;
		String str1str1 = str1 + str1;
		return str1str1.contains(str2);
	}

	public static void main(String[] args) {
		System.out.println(isPermutationOfPalindrome("ababbaaccc"));
	}

}

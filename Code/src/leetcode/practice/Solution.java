package leetcode.practice;

import java.util.Arrays;
import java.util.List;

/*
* New Grad - Full time
*/
public class Solution {
	public static List<List<String>> create_workflow_stages(List<List<String>> precursor_steps) {
		// IMPLEMENTATION GOES HERE

		return null;
	}

	/*
	 * START TEST CASES You can add test cases in the two lists below. Each test
	 * case input/expected output should correspond to the same index in the
	 * respective lists returned.
	 */
	static List<List<List<String>>> testInputs = Arrays.asList(
			Arrays.asList(Arrays.asList("clean", "build"), Arrays.asList("metadata", "binary"),
					Arrays.asList("build", "link"), Arrays.asList("link", "binary"), Arrays.asList("clean", "metadata"),
					Arrays.asList("build", "resources")),
			Arrays.asList(Arrays.asList("boil", "serve"), Arrays.asList("chop", "boil"), Arrays.asList("stir", "boil"),
					Arrays.asList("set table", "serve")));

	static List<List<List<String>>> testOutputs = Arrays.asList(
			Arrays.asList(Arrays.asList("clean"), Arrays.asList("build", "metadata"),
					Arrays.asList("resources", "link"), Arrays.asList("binary")),
			Arrays.asList(Arrays.asList("chop", "stir", "set table"), Arrays.asList("boil"), Arrays.asList("serve")));
	// END TEST CASES

	// DO NOT MODIFY BELOW THIS LINE
	public static boolean equalOutputs(List<List<String>> a, List<List<String>> b) {
		if (a.size() != b.size()) {
			return false;
		}
		for (int i = 0; i < a.size(); i++) {
			List<String> a1 = a.get(i);
			List<String> b1 = b.get(i);
			a1.sort(null);
			b1.sort(null);
			if (!a1.equals(b1)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		for (int i = 0; i < testInputs.size(); i++) {
			try {
				List<List<String>> result = create_workflow_stages(testInputs.get(i));

				if (equalOutputs(result, testOutputs.get(i))) {
					System.out.println("Pass");
				} else {
					System.out.println("Fail");
				}
			} catch (Exception e) {
				System.out.println("Fail");
				System.out.println(e);
			}
		}
	}
}

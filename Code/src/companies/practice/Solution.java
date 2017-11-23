package companies.practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {
	public static void main(String[] args) throws ParseException {
		String start, end;
		Scanner sc = new Scanner(System.in);
		String firstLine = sc.nextLine();
		String[] dates = firstLine.trim().split(",");
		start = dates[0].trim();
		end = dates[1].trim();

		SimpleDateFormat sdf_in = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf_in.parse(start + "-01");
		Date endDate = sdf_in.parse(end + "-01");
		sc.nextLine();
		SimpleDateFormat sdf_out = new SimpleDateFormat("yyyy-MM");

		Map<Long, TreeMap<String, Integer>> map = new TreeMap<>(Collections.reverseOrder());
		// Map<Long, String> map = new TreeMap<Long,
		// String>(Collections.reverseOrder());

		while (sc.hasNextLine()) {
			String inputLine = sc.nextLine();
			if (inputLine.isEmpty())
				break;
			String[] parts = inputLine.trim().split(",");
			Date date = sdf_out.parse(parts[0].trim());
			if ((date.after(startDate) || date.equals(startDate)) && (date.before(endDate) || date.equals(endDate))) {

				String operation = parts[1].trim();
				int val = Integer.parseInt(parts[2].trim());
				date = sdf_out.parse(parts[0].trim());
				if (map.get(date.getTime()) == null) {
					TreeMap<String, Integer> opMap = new TreeMap<>();
					opMap.put(operation, val);
					map.put(date.getTime(), opMap);
				} else {
					TreeMap<String, Integer> opMap = map.get(date.getTime());
					if (opMap.containsKey(operation)) {
						opMap.put(operation, opMap.get(operation) + val);
					} else {
						opMap.put(operation, val);
					}
					map.put(date.getTime(), opMap);
				}
			}
		}
		for (long key : map.keySet()) {
			String represent = "";
			TreeMap<String, Integer> opMap = map.get(key);
			for (String op : opMap.keySet()) {
				represent += ", " + op + ", " + opMap.get(op);
			}
			System.out.println(sdf_out.format(new Date(key)) + represent);
		}
	}
}

package companies.practice;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Twitter {

	public static void email() {

		Scanner skanni = new Scanner(System.in);

		System.out.println("Sládu inn email adressuna thína.");
		System.out.println("Email: ");
		String mail = skanni.nextLine();

		Pattern p = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher m = p.matcher(mail);

		if (m.find()) {
			System.out.println("Valid");
		} else {
			System.out.println("Invalid.");
		}

	}

	public static void main(String args[]) throws Exception {

	}

}

package object.oriented.design;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Factory Method offers an interface for creating an instance of a class,
 * with its subclasses deciding which class to instantiate.
 * 
 * 
 * Advantage of Factory Design Pattern
 * 
 * Factory Method Pattern allows the sub-classes to choose the type of objects
 * to create.
 * 
 * It promotes the loose-coupling by eliminating the need to bind
 * application-specific classes into the code. That means the code interacts
 * solely with the resultant interface or abstract class, so that it will work
 * with any classes that implement that interface or that extends that abstract
 * class.
 * 
 * 
 * Usage of Factory Design Pattern
 * 
 * When a class doesn't know what sub-classes will be required to create
 * 
 * When aclass wants that its sub-classes specify the objects to be created.
 * 
 * When the parent classes choose the creation of objects to its sub-classes.
 * 
 * @author nikitakothari
 *
 */

abstract class Plan {
	protected double rate;

	abstract void getRate();

	public void calculateBill(int units) {
		System.out.println(units * rate);
	}
}

class DomesticPlan extends Plan {
	// @override
	public void getRate() {
		rate = 3.50;
	}
}

class CommercialPlan extends Plan {
	// @override
	public void getRate() {
		rate = 7.50;
	}
}

class InstitutionalPlan extends Plan {
	// @override
	public void getRate() {
		rate = 5.50;
	}
}

class GetPlanFactory {

	// use getPlan method to get object of type Plan
	public Plan getPlan(String planType) {
		if (planType == null) {
			return null;
		}
		if (planType.equalsIgnoreCase("DOMESTICPLAN")) {
			return new DomesticPlan();
		} else if (planType.equalsIgnoreCase("COMMERCIALPLAN")) {
			return new CommercialPlan();
		} else if (planType.equalsIgnoreCase("INSTITUTIONALPLAN")) {
			return new InstitutionalPlan();
		}
		return null;
	}
}

public class Factory {
	public static void main(String args[]) throws IOException {
		GetPlanFactory planFactory = new GetPlanFactory();

		System.out.print("Enter the name of plan for which the bill will be generated: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String planName = br.readLine();
		System.out.print("Enter the number of units for bill will be calculated: ");
		int units = Integer.parseInt(br.readLine());

		Plan p = planFactory.getPlan(planName);
		// call getRate() method and calculateBill()method of DomesticPaln.

		System.out.print("Bill amount for " + planName + " of  " + units + " units is: ");
		p.getRate();
		p.calculateBill(units);
	}
}

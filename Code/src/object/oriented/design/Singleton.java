package object.oriented.design;

/**
 * The Singleton pattern ensures that a class has only one instance and ensures
 * access to the instance through the application.
 * 
 * @author nikitakothari
 *
 */
public class Singleton {

	private static Singleton _instance = null;

	protected Singleton() {
	}

	public static Singleton getlnstance() {
		if (_instance == null) {
			_instance = new Singleton();
		}
		return _instance;
	}

}

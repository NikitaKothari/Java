package object.oriented.design;

/**
 * The Singleton pattern ensures that a class has only one instance and ensures
 * access to the instance through the application.
 * 
 * Singleton Pattern says that just"define a class that has only one instance
 * and provides a global point of access to it".
 * 
 * In other words, a class must ensure that only single instance should be
 * created and single object can be used by all other classes.
 * 
 * There are two forms of singleton design pattern
 * 
 * Early Instantiation: creation of instance at load time. Lazy Instantiation:
 * creation of instance when required
 * 
 * Advantage of Singleton design pattern
 * 
 * Saves memory because object is not created at each request. Only single
 * instance is reused again and again.
 * 
 * Usage of Singleton design pattern
 * 
 * Singleton pattern is mostly used in multi-threaded and database applications.
 * It is used in logging, caching, thread pools, configuration settings etc.
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

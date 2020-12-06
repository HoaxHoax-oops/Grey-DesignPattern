
public class ChocolateBoiler {
	private boolean empty;
	private boolean boiled;
	
	/*
	 * create Singleton eagerly if the application always need it
	 * in this case the JVM guarantees the instance will be created before thread access
	 * 		private static ChocolateBoiler uniqueChocolateBoiler = new ChocolateBoiler();
	 * 
	 * add keyword "volatile" to ensure multiple threads handle the uniqueChocolateBoiler correctly
	 * when it is being initialized to the Singleton instance
	 * 		private volatile static Chocolate Boiler uniqueChocolateBoiler;
	 */
	private static ChocolateBoiler uniqueChocolateBoiler;

	/*
	 * the private constructor this code is only started when the boiler is empty
	 */
	private ChocolateBoiler() {
		empty = true;
		boiled = false;
	}

	/*
	 * this method ensure one and just one instance of ChocolateBoiler
	 * 		add "synchronized" to ensure no more than one threads may enter the method at the same time
	 * 		synchronization is expensive, but the only time synchronization is relevant is the first time
	 * 
	 * 		ignore check uniqueChocolateBoiler is null if you create it eagerly
	 * 
	 * 		use double-check locking to reduce the use of synchronization
	 * 		add synchronized block to check again whether the uniqueChocolateBoiler is null
	 * 		if true, create an instance
	 * 			synchronized (ChocolateBoiler.class) {}
	 */
	public static ChocolateBoiler getInstance() {
		if (uniqueChocolateBoiler == null)
			uniqueChocolateBoiler = new ChocolateBoiler();
		return uniqueChocolateBoiler;
	}

	/*
	 * to fill the boiler it must be empty 
	 * once it's full we set
	 * 		empty 	false
	 * 		boiled 	false
	 */
	public void fill() {
		if (isEmpty()) {
			empty = false;
			boiled = false;
			// fill the boiler with a milk/chocolate mixture
		}
	}

	/*
	 * to drain the boiler it must be full and boiled
	 * once it's drained we set
	 * 		empty true
	 */
	public void drain() {
		if (!isEmpty() && isBoiled()) {
			// drain the boiled milk and chocolate
			empty = true;
		}
	}
	
	/*
	 * to boil the boiler it must be full and not already boiled
	 * once it's boiled we set
	 * 		boiled true
	 */
	public void boil() {
		if (!isEmpty() && !isBoiled()) {
			// bring the contents to a boil
			boiled = true;
		}
	}

	private boolean isBoiled() {
		return boiled;
	}

	private boolean isEmpty() {
		return empty;
	}

}

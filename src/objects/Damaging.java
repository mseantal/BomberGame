package objects;

/** Interface, das von allen Klassen implementiert wird, deren Instanzen Schaden verteilen koennen. */

public interface Damaging {
	
	/**
	 * Gets the causing damage.
	 *
	 * @return the causing damage
	 */
	int getCausingDamage();

}

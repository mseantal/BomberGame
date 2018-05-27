package objects;

/** Interface, das von allen Klassen implementiert wird, deren Instanzen Schaden erhalten koennen. */

public interface Damageable {
	
	/**
	 * Increase damage.
	 *
	 * @param damage the damage
	 */
	void increaseDamage(int damage);

}

package objects;

/** Interface, das von allen Klassen implementiert wird, deren Instanzen am Ende jedes Levels zur Erhoehung des erzielten Punktestands beitragen 
 * sollen. */

public interface Scoreable {
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	int getScore(); 

}

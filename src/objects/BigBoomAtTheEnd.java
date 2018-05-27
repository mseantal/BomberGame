package objects;

import game.GameSettings;
import io.ImageLoader;

/**
 * The Class BigBoomAtTheEnd.als der Spieler tot ist, wird eine Explosion gemalt
 */
public class BigBoomAtTheEnd extends GameObject {
	
	/**
	 * Instantiates a new big boom at the end.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public BigBoomAtTheEnd(int x, int y) {
		image = ImageLoader.getFinalExplosionImage(GameSettings.finalExplosionWidth, GameSettings.finalExplosionHeight);
		this.setdX(x);
		this.setdY(y);
		this.setX(0);
		this.setY(0);
	}

	/* (non-Javadoc)
	 * @see objects.GameObject#getX()
	 */
	//get x koord
	public double getX() {
		return this.x;
	}

	/* (non-Javadoc)
	 * @see objects.GameObject#getY()
	 */
	//get y koord
	public double getY() {
		return this.y;
	}

	/* (non-Javadoc)
	 * @see objects.GameObject#collide(objects.GameObject)
	 */
	@Override
	public boolean collide(GameObject gameObjectToCheck) {
		return false;
	}

	/* (non-Javadoc)
	 * @see objects.GameObject#flipPolygonHorizontally()
	 */
	@Override
	public void flipPolygonHorizontally() {		
	}

	/* (non-Javadoc)
	 * @see objects.GameObject#outOfView()
	 */
	@Override
	public boolean outOfView() {
		return false;
	}
}

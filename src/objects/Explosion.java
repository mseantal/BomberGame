package objects;

import game.GameSettings;
import io.ImageLoader;

/**
 * The Class Explosion.
 */
public class Explosion extends GameObject {

	/**
	 * Instantiates a new explosion.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public Explosion(int x, int y) {
		image = ImageLoader.getExplosionImage(GameSettings.smallExplosionWidth, GameSettings.smallExplosionHeight);
		this.setdX(x);
		this.setdY(y);
		this.setX(0);
		this.setY(0);
	}

	/** The frames until visible. */
	public int framesUntilVisible = 2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#getX()
	 */
	public double getX() {
		return this.x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#getY()
	 */
	public double getY() {
		return this.y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#generateExplosion()
	 */
	@Override
	public void generateExplosion() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#setSilent(boolean)
	 */
	@Override
	public void setSilent(boolean silent) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#collide(objects.GameObject)
	 */
	@Override
	public boolean collide(GameObject gameObjectToCheck) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#flipPolygonHorizontally()
	 */
	@Override
	public void flipPolygonHorizontally() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#outOfView()
	 */
	@Override
	public boolean outOfView() {
		return false;
	}

}

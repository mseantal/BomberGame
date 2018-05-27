package objects;

import game.GameSettings;
import io.ImageLoader;

/**
 * The Class BigExplosion.wenn ein gebaude verschwunden ist, es ist auch ein GameObjekt (die beinden sich spater in vector)
 */
public class BigExplosion extends GameObject {

	/**
	 * Instantiates a new big explosion.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public BigExplosion(int x, int y) {
		image = ImageLoader.getBigExplosionImage(GameSettings.bigExplosionWidth, GameSettings.bigExplosionHeight);
		this.setdX(x);
		this.setdY(y);
		this.setX(0);
		this.setY(0);
	}

	/** The frames until visible. */
	public int framesUntilVisible = 4;

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
		// TODO Auto-generated method stub

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

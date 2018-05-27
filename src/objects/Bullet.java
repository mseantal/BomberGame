package objects;

import java.awt.geom.Area;
import game.GameSettings;
import io.ImageLoader;
import io.ImageOutline;

/**
 * The Class Bullet. Sie erbt von GO
 */
public class Bullet extends GameObject implements Explosive {

	/**
	 * Instantiates a new bullet.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	public Bullet(double x, double y) {
		image = ImageLoader.getBulletImage(GameSettings.bulletSize, GameSettings.bulletSize);
		shape = ImageOutline.toShape(image);
		polygon = ImageOutline.toPolygon(shape);
		this.setdX(x);
		this.setdY(y);
		this.setX(0);
		this.setY(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	//bullets bewegen sich
	public void run() {
		while (true) {
			synchronized (this) {
				try {
					if (GameSettings.running) {
						this.setdY(this.getdY() + GameSettings.bulletMovingPerRepaint * -1);
						Thread.sleep(GameSettings.repaintIntervalInMS);
						// inaktiv setzen
						if (this.getY() <= 0) {
							this.setActive(false);
						}
					} else {
						Thread.sleep(GameSettings.repaintIntervalInMS);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see objects.GameObject#collide(objects.GameObject)
	 */
	// prueft ob die BUllets getroffen wurde, if bullet collided - explosion
	@Override
	public boolean collide(GameObject gameObjectToCheck) {
		Area a1 = new Area(this.getShape());
		Area a2 = new Area(gameObjectToCheck.getShape());
		a1.intersect(a2);

		return !a1.isEmpty();
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

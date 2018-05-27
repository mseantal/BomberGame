package objects;

import java.awt.geom.Area;

import game.GameSettings;
import io.ImageLoader;
import io.ImageOutline;

/**
 * The Class Bomb. Sie erbt von GO, spaeter befindet sich ein GO vector
 */
public class Bomb extends GameObject{
	
	/**
	 * Instantiates a new bomb.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Bomb(int x, int y){
		image = ImageLoader.getBombImage(GameSettings.bombSize, GameSettings.bombSize);
		shape = ImageOutline.toShape(image);
		polygon = ImageOutline.toPolygon(shape);
		this.setX(0);
		this.setY(0);
		this.setdX(x);
		this.setdY(y);
	}

	/* (non-Javadoc)
	 * @see objects.GameObject#collide(objects.GameObject)
	 */
	@Override
	public boolean collide(GameObject gameObjectToCheck) {
		Area a1 = new Area(this.getShape());
		//System.out.println("bomb x: " + a1.getBounds().x);
		//System.out.println("bomb y: " + a1.getBounds().y);
		Area a2 = new Area(gameObjectToCheck.getShape());
		a1.intersect(a2);
		
		return !a1.isEmpty();
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

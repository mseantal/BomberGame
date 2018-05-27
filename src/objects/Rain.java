package objects;

import game.GameSettings;
import io.ImageLoader;

/**
 * The Class Rain.Die Klasse ist verantwortlich dafuer, dass es regnet.
 */
public class Rain extends GameObject{
	
	/**
	 * Instantiates a new rain.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Rain(int x, int y){
		image = ImageLoader.getRainImage(GameSettings.rainWidth, GameSettings.rainHeight);
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
		// TODO Auto-generated method stub
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

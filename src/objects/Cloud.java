package objects;

import java.awt.Image;
import game.GameSettings;
import io.ImageLoader;

/**
 * The Class Cloud. Die Wolken stehen im Hintergrund auf dem GamePanel
 */
public class Cloud {
	
	/** The image. */
	private Image image;
	
	/** The y. */
	private int x, y;

	/**
	 * Instantiates a new cloud.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Cloud(int x, int y) {
		image = ImageLoader.getCloudImage(GameSettings.cloudWidth, GameSettings.cloudHeight);
		this.x = x;
		this.y = y;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public Image getImage() {
		return this.image;
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return this.y;
	}

}

package io;

import java.awt.Image;
//import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Klasse zum Laden und Skalieren von Bildern.
 */

public class ImageLoader {

	/** The Constant SCALE_SMOOTH. */
	private static final int SCALE_SMOOTH = 0;
	
	/** The player looking to right image. */
	private static Image playerLookingToRightImage;
	
	/** The player looking to left image. */
	private static Image playerLookingToLeftImage;
	
	/** The bomb image. */
	private static Image bombImage;
	
	/** The big explosion image. */
	private static Image bigExplosionImage;
	
	/** The explosion image. */
	private static Image explosionImage;
	
	/** The cloud image. */
	private static Image cloudImage;
	
	/** The blue house image. */
	private static Image blueHouseImage;
	
	///** The blue house polygon. */
	//private static Polygon blueHousePolygon;
	
	/** The red house image. */
	private static Image redHouseImage;
	
	/** The yellow house image. */
	private static Image yellowHouseImage;
	
	/** The church image. */
	private static Image churchImage;
	
	/** The bullet image. */
	private static Image bulletImage;
	
	/** The eagle image. */
	private static Image eagleImage;
	
	/** The final explosion. */
	private static Image finalExplosion;
	
	/** The rain. */
	private static Image rain;
	
	/**
	 * Gets the bullet image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the bullet image
	 */
	//emese
	public static Image getBulletImage(int width, int height) {
		if (bulletImage == null) {
			try {
				bulletImage = ImageIO.read(new File("img/bullet.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bulletImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	/**
	 * Gets the rain image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the rain image
	 */
	public static Image getRainImage(int width, int height) {
		if (rain == null) {
			try {
				rain= ImageIO.read(new File("img/rain.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return rain.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	/**
	 * Gets the cloud image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the cloud image
	 */
	//emese
	public static Image getCloudImage(int width, int height) {
		if (cloudImage == null) {
			try {
				cloudImage = ImageIO.read(new File("img/cloud.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cloudImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	/**
	 * Gets the eagle image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the eagle image
	 */
	//emese
	public static Image getEagleImage(int width, int height) {
		if (eagleImage == null) {
			try {
				eagleImage = ImageIO.read(new File("img/dragon.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return eagleImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	

	/**
	 * Gets the player looking to right image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the player looking to right image
	 */
	public static Image getPlayerLookingToRightImage(int width, int height) {
		if (playerLookingToRightImage == null) {
			try {
				playerLookingToRightImage = ImageIO.read(new File("img/planeLookingToRight.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return playerLookingToRightImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}

	/**
	 * Gets the player looking to left image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the player looking to left image
	 */
	public static Image getPlayerLookingToLeftImage(int width, int height) {
		if (playerLookingToLeftImage == null) {
			try {
				playerLookingToLeftImage = ImageIO.read(new File("img/planeLookingToLeft.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return playerLookingToLeftImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}

	/**
	 * Gets the bomb image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the bomb image
	 */
	public static Image getBombImage(int width, int height) {
		if (bombImage == null) {
			try {
				bombImage = ImageIO.read(new File("img/bomb.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bombImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}
	
	/**
	 * Gets the final explosion image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the final explosion image
	 */
	public static Image getFinalExplosionImage(int width, int height) {
		if (finalExplosion == null) {
			try {
				finalExplosion = ImageIO.read(new File("img/explosion2.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return finalExplosion.getScaledInstance(width, height, SCALE_SMOOTH);
	}


	/**
	 * Gets the big explosion image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the big explosion image
	 */
	public static Image getBigExplosionImage(int width, int height) {
		if (bigExplosionImage == null) {
			try {
				bigExplosionImage = ImageIO.read(new File("img/bigExplosion.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bigExplosionImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}

	/**
	 * Gets the explosion image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the explosion image
	 */
	public static Image getExplosionImage(int width, int height) {
		if (explosionImage == null) {
			try {
				explosionImage = ImageIO.read(new File("img/explosion3.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return explosionImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}

	/**
	 * Gets the blue house image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the blue house image
	 */
	public static Image getBlueHouseImage(int width, int height) {
		if (blueHouseImage == null) {
			try {
				BufferedImage img = ImageIO.read(new File("img/bluehouse.png")); // resource
																					// volt
																					// az
																					// image
																					// elott
				blueHouseImage = img;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return blueHouseImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}

	/**
	 * Gets the red house image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the red house image
	 */
	public static Image getRedHouseImage(int width, int height) {
		if (redHouseImage == null) {
			try {
				redHouseImage = ImageIO.read(new File("img/redHouse.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return redHouseImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}

	/**
	 * Gets the yellow house image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the yellow house image
	 */
	public static Image getYellowHouseImage(int width, int height) {
		if (yellowHouseImage == null) {
			try {
				yellowHouseImage = ImageIO.read(new File("img/yellowhouse.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return yellowHouseImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}

	/**
	 * Gets the church image.
	 *
	 * @param width the width
	 * @param height the height
	 * @return the church image
	 */
	public static Image getChurchImage(int width, int height) {
		if (churchImage == null) {
			try {
				churchImage = ImageIO.read(new File("img/church.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return churchImage.getScaledInstance(width, height, SCALE_SMOOTH);
	}

	

}
